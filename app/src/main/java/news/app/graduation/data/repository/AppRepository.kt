package news.app.graduation.data.repository

import kotlinx.coroutines.Dispatchers
import news.app.graduation.data.network.data_source.ApiDataSource
import news.app.graduation.data.network.flowSafeApiCall
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiDataSource: ApiDataSource) {
    suspend fun getDataHome() = flowSafeApiCall(Dispatchers.IO) {
        apiDataSource.getDataHome()
    }

    suspend fun getDataStar() = flowSafeApiCall(Dispatchers.IO) {
        apiDataSource.getDataStar()
    }

    suspend fun getDataSport() = flowSafeApiCall(Dispatchers.IO) {
        apiDataSource.getDataSport()
    }

    suspend fun getChildCategory(endPoint: String) = flowSafeApiCall(Dispatchers.IO) {
        apiDataSource.getChildCategory(endPoint)
    }
}