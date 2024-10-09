package news.app.graduation.presentation.feature.m01_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import news.app.graduation.data.network.Resource
import news.app.graduation.data.repository.HomeRepository
import news.app.graduation.presentation.core.base.BaseViewModel
import news.app.graduation.presentation.core.base.CommonState
import javax.inject.Inject

@HiltViewModel
class M01HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {
    private val _m01HomeState = MutableLiveData<CommonState<String>>()
    val m01HomeState get() = _m01HomeState

    init {
        refreshData()
    }

    fun refreshData() {
        //call api
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            homeRepository.getDataHome().map {
                when(it) {
                    is Resource.Error -> CommonState.Fail(it.message)
                    is Resource.Loading -> CommonState.Loading()
                    is Resource.Success -> CommonState.Success(it.data)
                }
            }.collect{
                _m01HomeState.value = it
            }
        }
    }
}