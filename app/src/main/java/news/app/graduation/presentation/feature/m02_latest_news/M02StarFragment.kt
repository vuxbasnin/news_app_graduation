package news.app.graduation.presentation.feature.m02_latest_news

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.parseRssFeed
import news.app.graduation.core.common.show
import news.app.graduation.databinding.M02LatestNewsFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import news.app.graduation.presentation.feature.m02_latest_news.adapter.StarAdapter
import news.app.graduation.presentation.feature.m02_latest_news.convert.DataStarConvert
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
        starAdapter = StarAdapter(requireActivity())
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
                    val listItemFocus = data?.items?.subList(1, 6)
                    val listItemOther = data?.items?.subList(6, data.items.size - 1)
                    val listDataAdapter = mutableListOf<DataStarConvert>()
                    listDataAdapter.add(DataStarConvert.ItemHeader(itemHeader))
                    listDataAdapter.add(DataStarConvert.ListItemFocus(listItemFocus))
                    listItemOther?.forEach {
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

    private fun hideLoading(){
        bindingOrNull?.loading?.container?.stopShimmer()
        bindingOrNull?.loading?.container.hide()
        bindingOrNull?.rcvMedia.show()
    }

    private fun showLoading(){
        bindingOrNull?.rcvMedia.hide()
        bindingOrNull?.loading?.container.show()
        bindingOrNull?.loading?.container?.startShimmer()
    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}