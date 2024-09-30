package news.app.graduation.data.repository

import kotlinx.coroutines.Dispatchers
import news.app.graduation.data.network.data_source.ApiDataSource
import news.app.graduation.data.network.flowSafeApiCall
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiDataSource: ApiDataSource) {
    suspend fun getDataHome() = flowSafeApiCall(Dispatchers.IO) {
        apiDataSource.getDataHome()
    }
}