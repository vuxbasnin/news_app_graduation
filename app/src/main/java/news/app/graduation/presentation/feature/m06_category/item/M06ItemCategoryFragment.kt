package news.app.graduation.presentation.feature.m06_category.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.parseRssFeed
import news.app.graduation.core.utils.Utility
import news.app.graduation.databinding.M06FragmentItemCategoryBinding
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base.CommonState
import news.app.graduation.presentation.feature.m06_category.adapter.M06ItemCategoryAdapter

@AndroidEntryPoint
class M06ItemCategoryFragment :
    BaseFragment<M06FragmentItemCategoryBinding>(M06FragmentItemCategoryBinding::inflate) {
    private val viewModel: M06ItemCategoryViewModel by viewModels()
    private var m06ItemCategoryAdapter: M06ItemCategoryAdapter? = null
    private var endPoint: String? = null

    companion object {
        private const val URL = "URL"
        fun newInstance(urlRequest: String): M06ItemCategoryFragment {
            val args = Bundle()
            args.putString(URL, urlRequest)
            val fragment = M06ItemCategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        endPoint = arguments?.getString(URL)
    }

    override fun initView() {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        bindingOrNull?.rcvListNewsCategory?.layoutManager = Utility.getLayoutVertical(context)
        m06ItemCategoryAdapter = M06ItemCategoryAdapter(requireContext())
        bindingOrNull?.rcvListNewsCategory?.adapter = m06ItemCategoryAdapter
        bindingOrNull?.swipeRefresh?.setOnRefreshListener {
            resetData()
        }
    }

    override fun initObserver() {
        viewModel.m06ItemCategoryState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommonState.Loading -> {

                }

                is CommonState.Success -> {
                    state.data.parseRssFeed()?.items?.let { m06ItemCategoryAdapter?.setData(it) }
                }

                is CommonState.Fail -> {

                }
            }
        }
    }

    override fun getData() {
        viewModel.getData(endPoint ?: "")
    }

    override fun resetData() {
        m06ItemCategoryAdapter?.setData(arrayListOf())
        viewModel.getData(endPoint ?: "")
    }

    override fun onClick(v: View?) {

    }
}