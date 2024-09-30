package news.app.graduation.presentation.feature.m01_home

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M01HomeFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01HomeFragmentBinding>(M01HomeFragmentBinding::inflate) {
    private val viewModel: M01HomeViewModel by viewModels()

    override fun initView() {

    }

    override fun initObserver() {
        viewModel.m01HomeState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CommonState.Loading -> {
                    Timber.d("NINVB => Loading")
                }

                is CommonState.Success -> {
                    Timber.d("NINVB => list data home ${state.data}")
                }

                is CommonState.Fail -> {
                    Timber.d("NINVB => Fail message ${state.msg}")
                }
            }
        }
    }

    override fun resetData() {

    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }
}