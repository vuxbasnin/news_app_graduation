package news.app.graduation.presentation.feature.m03_hot_news

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.openDetail
import news.app.graduation.core.common.parseRssFeed
import news.app.graduation.core.common.show
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M03HotNewsFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import news.app.graduation.presentation.feature.m03_hot_news.adapter.SportAdapter
import news.app.graduation.presentation.my_interface.OnClickItemSportListener
import timber.log.Timber

@AndroidEntryPoint
class M03SportNewsFragment :
    BaseFragment<M03HotNewsFragmentBinding>(M03HotNewsFragmentBinding::inflate) {
    private val viewModel: M03SportNewsViewModel by viewModels()
    private var sportAdapter: SportAdapter? = null

    override fun initView() {
        setupRecyclerView()
        setOnClickListener()
        setupToolBar()
    }

    private fun setupRecyclerView() {
        sportAdapter = SportAdapter(requireActivity(), onClickItemSportListener)
        binding.rcvMedia.adapter = sportAdapter
    }

    private fun setupToolBar() {
        binding.toolBar.setTitle("Sport")
        bindingOrNull?.toolBar?.hideShowIconBack(false)
    }

    private fun setOnClickListener() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.refreshLayout.isRefreshing = true
        }

        bindingOrNull?.errorLayout?.onRetryClickListener = {
            viewModel.refreshData()
        }
    }

    override fun initObserver() {
        viewModel.m03SportNewsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommonState.Loading -> {
                    showLoading()
                }

                is CommonState.Success -> {
                    val data = state.data.parseRssFeed()
                    data?.items?.let { sportAdapter?.setData(it) }
                    hideLoading()
                    binding.refreshLayout.isRefreshing = false
                }

                is CommonState.Fail -> {

                }
            }
        }
    }

    private fun hideLoading() {
        bindingOrNull?.loading?.container?.stopShimmer()
        bindingOrNull?.loading?.container.hide()
        bindingOrNull?.rcvMedia.show()
    }

    private fun showLoading() {
        bindingOrNull?.rcvMedia.hide()
        bindingOrNull?.loading?.container.show()
        bindingOrNull?.loading?.container?.startShimmer()
    }

    private val onClickItemSportListener = object : OnClickItemSportListener {
        override fun callback(tag: OnClickItemSportListener.TagSport, data: Any?) {
            Timber.d("$TAG => callback in home: tag $tag")
            runCatching {
                when (tag) {
                    OnClickItemSportListener.TagSport.NONE -> {}
                    OnClickItemSportListener.TagSport.ON_CLICK_ITEM -> {
                        (data as? Item)?.openDetail()
                    }
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}