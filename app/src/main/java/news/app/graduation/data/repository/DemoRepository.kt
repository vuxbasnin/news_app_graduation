package news.app.graduation.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import news.app.graduation.data.network.data_source.DemoDataSource
import javax.inject.Inject

class DemoRepository @Inject constructor(private val demoDataSource: DemoDataSource) {
    fun getDemo() = flow {
        emit(demoDataSource.getDemo())
    }.flowOn(Dispatchers.IO)
}