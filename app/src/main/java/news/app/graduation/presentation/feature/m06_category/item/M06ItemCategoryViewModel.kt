package news.app.graduation.presentation.feature.m06_category.item

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
class M06ItemCategoryViewModel @Inject constructor(private val appRepository: AppRepository) :
    BaseViewModel() {
    private val _m06ItemCategoryState = MutableLiveData<CommonState<String>>()
    val m06ItemCategoryState get() = _m06ItemCategoryState

    fun getData(endPoint: String) {
        viewModelScope.launch {
            appRepository.getChildCategory(endPoint).map {
                when (it) {
                    is Resource.Error -> CommonState.Fail(it.message)
                    is Resource.Loading -> CommonState.Loading()
                    is Resource.Success -> CommonState.Success(it.data)
                }
            }.collect {
                _m06ItemCategoryState.value = it
            }
        }
    }
}