package news.app.graduation.data.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import news.app.graduation.data.model.request.DefaultRequest
import news.app.graduation.data.network.Endpoint

interface DemoService {
    @GET(Endpoint.DEMO)
    suspend fun getDemo(@Body body: DefaultRequest): Response<Any>
}