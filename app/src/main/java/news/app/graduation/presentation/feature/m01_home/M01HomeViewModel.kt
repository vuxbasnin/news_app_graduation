package news.app.graduation.presentation.feature.m01_home

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import news.app.graduation.data.repository.DemoRepository
import news.app.graduation.presentation.core.base.BaseViewModel
import news.app.graduation.presentation.core.base.CommonState
import javax.inject.Inject

@HiltViewModel
class M01HomeViewModel @Inject constructor(private val demoRepository: DemoRepository) :
    BaseViewModel() {
    private val _m01State = MutableLiveData<CommonState<ArrayList<Any>>>()
    val m01State get() = _m01State

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