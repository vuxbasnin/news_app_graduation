package news.app.graduation.presentation.feature.m03_hot_news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import news.app.graduation.data.network.Resource
import news.app.graduation.data.repository.AppRepository
import news.app.graduation.presentation.core.base.BaseViewModel
import news.app.graduation.presentation.core.base.CommonState
import javax.inject.Inject

@HiltViewModel
class M03SportNewsViewModel @Inject constructor(private val appRepository: AppRepository) :
    BaseViewModel() {
    private val _m03SportNewsState = MutableLiveData<CommonState<String>>()
    val m03SportNewsState get() = _m03SportNewsState

    init {
        refreshData()
    }

    fun refreshData() {
        //call api
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            appRepository.getDataSport().map {
                when (it) {
                    is Resource.Error -> CommonState.Fail(it.message)
                    is Resource.Loading -> CommonState.Loading()
                    is Resource.Success -> CommonState.Success(it.data)
                }
            }.collect { state ->
                _m03SportNewsState.value = state
            }
        }
    }
}