package news.app.graduation.data.network.service

import news.app.graduation.data.model.response.config.ConfigAppResponse
import news.app.graduation.data.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsServiceRetrofit {
    @GET("http://mobile.ewings.vn/api_v2/menu/122.html")
    suspend fun getConfigApp(): Response<ConfigAppResponse>

    @GET(Endpoint.HOME)
    suspend fun getDataHome(): Response<String>

    @GET(Endpoint.STAR)
    suspend fun getDataStar(): Response<String>

    @GET(Endpoint.SPORT)
    suspend fun getDataSport(): Response<String>

    @GET
    suspend fun getChildCategory(@Url endPoint: String): Response<String>
}