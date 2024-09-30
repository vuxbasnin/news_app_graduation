package news.app.graduation.presentation.feature.m03_hot_news

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M03HotNewsFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment

@AndroidEntryPoint
class M03HotNewsFragment : BaseFragment<M03HotNewsFragmentBinding>(M03HotNewsFragmentBinding::inflate){
    private val viewModel: M03HotNewsViewModel by viewModels()

    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}