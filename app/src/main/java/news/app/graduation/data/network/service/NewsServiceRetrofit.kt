package news.app.graduation.data.network.service

import retrofit2.Response
import retrofit2.http.GET
import news.app.graduation.data.model.response.config.ConfigAppResponse

interface NewsServiceRetrofit {
    @GET("http://mobile.ewings.vn/api_v2/menu/122.html")
    suspend fun getConfigApp(): Response<ConfigAppResponse>
}