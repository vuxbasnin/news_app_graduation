package news.app.graduation.data.network.service

import news.app.graduation.data.model.response.config.ConfigAppResponse
import news.app.graduation.data.model.response.rss.RssFeed
import news.app.graduation.data.network.Endpoint
import retrofit2.Response
import retrofit2.http.GET

interface NewsServiceRetrofit {
    @GET("http://mobile.ewings.vn/api_v2/menu/122.html")
    suspend fun getConfigApp(): Response<ConfigAppResponse>

    @GET(Endpoint.HOME)
    suspend fun getDataHome(): Response<RssFeed>
}