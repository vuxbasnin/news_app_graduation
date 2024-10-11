package news.app.graduation.presentation.feature.m04_menu

import android.view.View
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.Constants.DataZoneMenu
import news.app.graduation.core.common.addDivider
import news.app.graduation.data.model.response.menu.ListDataMenu
import news.app.graduation.data.model.response.menu.Zone
import news.app.graduation.databinding.M04MediaFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.feature.m04_menu.adapter.MenuAdapter
import news.app.graduation.presentation.feature.m06_category.M06CategoryFragment
import news.app.graduation.presentation.my_interface.OnClickMenuListener

@AndroidEntryPoint
class M04MenuFragment : BaseFragment<M04MediaFragmentBinding>(M04MediaFragmentBinding::inflate) {
    private val viewModel: M04MenuViewModel by viewModels()
    private var menuAdapter: MenuAdapter? = null
    private val listData = parseDataMenu()

    override fun initView() {
        setupToolBar()
        setupRecyclerView()
    }

    private fun setupToolBar() {
        binding.toolBar.setTitle("Menu")
        bindingOrNull?.toolBar?.hideShowIconBack(false)
    }

    private fun setupRecyclerView() {
        menuAdapter = MenuAdapter(requireActivity(), onClickMenuListener)
        binding.rcvMenu.adapter = menuAdapter
        binding.rcvMenu.addDivider()
        menuAdapter?.setData(listData.zones)
    }

    private val onClickMenuListener = object : OnClickMenuListener {
        override fun callback(tag: OnClickMenuListener.TagMenu, data: Any?) {
            when (tag) {
                OnClickMenuListener.TagMenu.NONE -> {

                }

                OnClickMenuListener.TagMenu.ON_CLICK_ITEM -> {
                    val mData = data as OnClickMenuListener.CategorySelected?
                    NavigationManager.getInstance().openFragment(
                        M06CategoryFragment.newInstance(mData?.parentCategory, mData?.selectedCategory)
                    )
                }

                OnClickMenuListener.TagMenu.ON_CLICK_ITEM_CHILD -> {
//                    NavigationManager.getInstance().openFragment(
//                        M06CategoryFragment.newInstance((data as? Zone))
//                    )
                }
            }
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }

    private fun parseDataMenu(): ListDataMenu {
        return Gson().fromJson(DataZoneMenu, ListDataMenu::class.java)
    }
}