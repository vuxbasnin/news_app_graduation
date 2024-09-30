package news.app.graduation.presentation.feature.m02_latest_news

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M02LatestNewsFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment

@AndroidEntryPoint
class M02LatestNewsFragment : BaseFragment<M02LatestNewsFragmentBinding>(M02LatestNewsFragmentBinding::inflate) {
    private val viewModel: M02LatestNewsViewModel by viewModels()

    override fun initView() {

    }


    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}