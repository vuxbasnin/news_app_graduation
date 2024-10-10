package news.app.graduation.data.network.data_source

import news.app.graduation.data.network.service.NewsServiceRetrofit
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val newsServiceRetrofit: NewsServiceRetrofit) {
    suspend fun getConfigApp() = newsServiceRetrofit.getConfigApp()

    suspend fun getDataHome() = newsServiceRetrofit.getDataHome()
    suspend fun getDataStar() = newsServiceRetrofit.getDataStar()
}