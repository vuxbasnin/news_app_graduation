package news.app.graduation.presentation.feature.m01_home

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M01HomeFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01HomeFragmentBinding>(M01HomeFragmentBinding::inflate) {
    private val viewModel: M01HomeViewModel by viewModels()

    override fun initView() {

    }

    override fun initObserver() {
        viewModel.m01State.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CommonState.Loading -> {

                }

                is CommonState.Success -> {

                }

                is CommonState.Fail -> {

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