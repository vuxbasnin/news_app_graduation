package news.app.graduation.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import news.app.graduation.core.utils.InternetUtil

/**
 * getting state of network call
 */
sealed interface Resource<T> {
    class Success<T>(val data: T) : Resource<T>
    class Loading<T> : Resource<T>
    class Error<T>(val message: String, val code: Int, val data: T? = null) : Resource<T>
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    request: (suspend () -> Response<T>),
): Resource<T> {
    return withContext(dispatcher) {
        try {
            if (!InternetUtil.isNetworkAvailable()) {
                return@withContext Resource.Error("No Internet", 0)
            }
            val response = request.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@withContext Resource.Success(body)
                }
            }
            return@withContext Resource.Error(
                response.errorBody()?.string() ?: response.message(),
                response.code()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message ?: "Some error", 0)
        }
    }
}

fun <T> flowSafeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    request: (suspend () -> Response<T>),
): Flow<Resource<T>> {
    return flow<Resource<T>> {
        emit(Resource.Loading())
        if (!InternetUtil.isNetworkAvailable()) {
            emit(Resource.Error("No Internet", 0))
            return@flow
        }
        val response = request.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Resource.Success(body))
                return@flow
            }
        }
        emit(Resource.Error(response.errorBody()?.string() ?: response.message(), response.code()))
    }.catch {
        Timber.e(it.message)
        emit(Resource.Error(it.message ?: "Lỗi", 0))
    }.flowOn(dispatcher)
}