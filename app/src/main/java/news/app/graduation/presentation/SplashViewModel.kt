package news.app.graduation.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import news.app.graduation.data.model.response.config.ConfigAppResponse
import news.app.graduation.data.network.Resource
import news.app.graduation.data.repository.ConfigRepository
import news.app.graduation.presentation.core.base.BaseViewModel
import news.app.graduation.presentation.core.base.CommonState
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(private val splashRepository: ConfigRepository) : BaseViewModel() {
    val splashState = MutableLiveData<CommonState<ConfigAppResponse>>()
    fun getConfigApp() {
        viewModelScope.launch {
            splashRepository.getConfigApp().map {
                when (it) {
                    is Resource.Error -> CommonState.Fail(it.message)
                    is Resource.Loading -> CommonState.Loading()
                    is Resource.Success -> CommonState.Success(it.data)
                }
            }.collect {
                splashState.value = it
            }
        }
    }
}