package news.app.graduation.presentation.feature.m07_notification

import dagger.hilt.android.lifecycle.HiltViewModel
import news.app.graduation.data.repository.DemoRepository
import news.app.graduation.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class M07NotificationViewModel @Inject constructor(private val demoRepository: DemoRepository) :
    BaseViewModel() {

    init {
        refreshData()
    }

    fun refreshData() {
        //call api
        getData()
    }

    private fun getData() {

    }
}