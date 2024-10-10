package news.app.graduation.presentation.feature.m01_home

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.openDetail
import news.app.graduation.core.common.parseRssFeed
import news.app.graduation.core.common.show
import news.app.graduation.core.common.visible
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M01HomeFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import news.app.graduation.presentation.feature.m01_home.adapter.HomeAdapter
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener.TagClickItemHome.*
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01HomeFragmentBinding>(M01HomeFragmentBinding::inflate) {
    private val viewModel: M01HomeViewModel by viewModels()
    private var homeAdapter: HomeAdapter? = null

    override fun initView() {
        setUpAdapter()
        setOnClickListener()
    }

    private fun setUpAdapter() {
        bindingOrNull?.rcvHome?.layoutManager = Utility.getLayoutVertical(context)
        homeAdapter = HomeAdapter(requireContext(), onClickItemHomeListener)
        bindingOrNull?.rcvHome?.adapter = homeAdapter
        bindingOrNull?.swRefresh?.setOnRefreshListener {
            resetData()
        }
    }

    override fun initObserver() {
        viewModel.m01HomeState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommonState.Loading -> {
                    Timber.d("M01HomeFragment => Loading")
                    showLoading()
                }

                is CommonState.Success -> {
                    Timber.d("M01HomeFragment => Success")
                    val data = state.data.parseRssFeed()
                    data?.items?.let { homeAdapter?.setData(it) }
                    hideLoading()
                    bindingOrNull?.errorLayout.visible(false)
                }

                is CommonState.Fail -> {
                    Timber.d("M01HomeFragment => Fail message ${state.msg}")
                    bindingOrNull?.errorLayout.visible(true)
                }
            }
        }
    }

    private val onClickItemHomeListener = object : OnClickItemHomeListener {
        override fun callback(tag: OnClickItemHomeListener.TagClickItemHome, data: Any?) {
            Timber.d("$TAG => callback in home: tag $tag")
            runCatching {
                when(tag) {
                    ON_CLICK_ITEM -> {
                        (data as? Item)?.openDetail()
                    }

                    NONE -> {

                    }
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun setOnClickListener() {
        bindingOrNull?.errorLayout?.onRetryClickListener = {
            resetData()
        }
    }

    private fun hideLoading() {
        bindingOrNull?.loadingHome?.container?.stopShimmer()
        bindingOrNull?.loadingHome?.container.hide()
        bindingOrNull?.rcvHome.show()
        bindingOrNull?.swRefresh?.isRefreshing = false
    }

    private fun showLoading() {
        bindingOrNull?.rcvHome.hide()
        bindingOrNull?.loadingHome?.container.show()
        bindingOrNull?.loadingHome?.container?.startShimmer()
        bindingOrNull?.swRefresh?.isRefreshing = true
    }

    override fun resetData() {
        homeAdapter?.setData(arrayListOf())
        viewModel.getData()
    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }
}