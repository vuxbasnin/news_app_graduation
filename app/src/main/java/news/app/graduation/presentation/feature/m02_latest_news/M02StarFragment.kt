package news.app.graduation.presentation.feature.m02_latest_news

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.openDetail
import news.app.graduation.core.common.parseRssFeed
import news.app.graduation.core.common.show
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M02LatestNewsFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import news.app.graduation.presentation.feature.m02_latest_news.adapter.StarAdapter
import news.app.graduation.presentation.feature.m02_latest_news.convert.DataStarConvert
import news.app.graduation.presentation.my_interface.OnClickItemStarListener
import timber.log.Timber

@AndroidEntryPoint
class M02StarFragment :
    BaseFragment<M02LatestNewsFragmentBinding>(M02LatestNewsFragmentBinding::inflate) {
    private val viewModel: M02StarViewModel by viewModels()
    private var starAdapter: StarAdapter? = null

    override fun initView() {
        setupRecyclerView()
        setOnClickListener()
        setupToolBar()
    }

    private fun setupRecyclerView() {
        starAdapter = StarAdapter(requireActivity(), onClickItemStarListener)
        binding.rcvMedia.adapter = starAdapter
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

    private fun setupToolBar() {
        binding.toolBar.setTitle("Star")
        bindingOrNull?.toolBar?.hideShowIconBack(false)
    }

    override fun initObserver() {
        viewModel.m02StarState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommonState.Loading -> {
                    Timber.d("M02StarFragment => Loading")
                    showLoading()
                }

                is CommonState.Success -> {
                    Timber.d("M02StarFragment => Success")
                    val data = state.data.parseRssFeed()
                    val itemHeader = data?.items?.getOrNull(0)
                    val countItem = 10
                    val centerIndex = data?.items?.size?.div(2) ?: 0
                    val listItemFocus = data?.items?.subList(1, 11)
                    val listItemOther1 = data?.items?.subList(12, centerIndex - 1)
                    val listItemFocus2 =
                        data?.items?.subList(centerIndex, centerIndex + countItem - 1)
                    val listItemOther2 =
                        data?.items?.subList(centerIndex + countItem, data.items.size - 1)
                    val listDataAdapter = mutableListOf<DataStarConvert>()
                    listDataAdapter.add(DataStarConvert.ItemHeader(itemHeader))
                    listDataAdapter.add(DataStarConvert.ListItemFocus(listItemFocus))
                    listItemOther1?.forEach {
                        listDataAdapter.add(DataStarConvert.ListItemOther(it))
                    }
                    listDataAdapter.add(DataStarConvert.ListItemFocus(listItemFocus2))
                    listItemOther2?.forEach {
                        listDataAdapter.add(DataStarConvert.ListItemOther(it))
                    }
                    starAdapter?.setData(listDataAdapter)
                    binding.refreshLayout.isRefreshing = false
                    hideLoading()
                }

                is CommonState.Fail -> {
                    Timber.d("M02StarFragment => Fail message ${state.msg}")
                }
            }
        }
    }

    private val onClickItemStarListener = object : OnClickItemStarListener {
        override fun callback(tag: OnClickItemStarListener.TagStar, data: Any?) {
            Timber.d("$TAG => callback in home: tag $tag")
            runCatching {
                when (tag) {
                    OnClickItemStarListener.TagStar.NONE -> {}
                    OnClickItemStarListener.TagStar.ON_CLICK_ITEM -> {
                        (data as? Item)?.openDetail()
                    }
                }
            }.onFailure {
                it.printStackTrace()
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

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}