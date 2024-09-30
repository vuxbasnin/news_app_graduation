package news.app.graduation.data.network.data_source

import android.content.Context
import news.app.graduation.data.model.request.DefaultRequest
import news.app.graduation.data.network.flowSafeApiCall
import news.app.graduation.data.network.service.DemoService
import javax.inject.Inject

class DemoDataSource @Inject constructor(
    private val demoService: DemoService,
    context: Context
): BaseDataSource(context) {
    suspend fun getDemo() = flowSafeApiCall{
        demoService.getDemo(DefaultRequest())
    }
}