package news.app.graduation.presentation.feature.m06_category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.core.common.getMySerializable
import news.app.graduation.core.common.visible
import news.app.graduation.data.model.response.menu.Category
import news.app.graduation.data.model.response.menu.Zone
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M06CategoryFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.core.base_adapter.MyViewPagerAdapter
import news.app.graduation.presentation.core.widget.CustomTabCategory
import news.app.graduation.presentation.feature.m06_category.item.M06ItemCategoryFragment

@AndroidEntryPoint
class M06CategoryFragment :
    BaseFragment<M06CategoryFragmentBinding>(M06CategoryFragmentBinding::inflate) {
    private val viewModel: M06CategoryViewModel by viewModels()
    private var pagerAdapter: MyViewPagerAdapter<Category, M06ItemCategoryFragment>? = null
    private var dataZone: Zone? = null
    private var dataZoneSelected: Zone? = null
    private var positionSelected: Int? = null
    private var listCategoryData: ArrayList<Category> = arrayListOf()

    companion object {
        private const val ZONE = "ZONE"
        private const val ZONE_SELECTED = "ZONE_SELECTED"
        private const val POSITION_SELECTED = "POSITION_SELECTED"
        fun newInstance(data: Zone?, dataSelected: Zone?, positionSelected: Int? = null): M06CategoryFragment {
            val args = Bundle()
            args.putSerializable(ZONE, data)
            args.putSerializable(ZONE_SELECTED, dataSelected)
            args.putInt(POSITION_SELECTED, positionSelected ?: 0)
            val fragment = M06CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        dataZone = getMySerializable(ZONE, Zone::class.java)
        dataZoneSelected = getMySerializable(ZONE_SELECTED, Zone::class.java)
        positionSelected = arguments?.getInt(POSITION_SELECTED, 0)
        listCategoryData = dataZone?.categories ?: arrayListOf()
    }

    override fun initView() {
        bindView()
    }

    private fun bindView() {
        binding.rlTitle.tvTitleToolbarZone.text = dataZone?.title?: ""
        pagerAdapter = MyViewPagerAdapter(this, listCategoryData) { categories, _ ->
            M06ItemCategoryFragment.newInstance(categories.rss_url ?: "")
        }
        binding.tabCategoryViewPager.adapter = pagerAdapter
        binding.tabLayoutCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { position ->

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        binding.rlTitle.imgBackCustom.setOnClickListener(this)
        TabLayoutMediator(binding.tabLayoutCategory, binding.tabCategoryViewPager) { tab, position ->
            tab.customView = getCustomViewTab(listCategoryData[position])
        }.attach()
        binding.tabLayoutCategory.visible(listCategoryData.size > 1)
        if (positionSelected != 0) bindingOrNull?.tabCategoryViewPager?.setCurrentItem(positionSelected ?: 0, false)
        bindingOrNull?.rlTitle?.imgBackCustom?.setOnClickListener {
            NavigationManager.getInstance().popBackStack()
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }

    private fun getCustomViewTab(categoryBox: Category): View {
        val tab = CustomTabCategory(requireContext())
        tab.text.text = categoryBox.name
        return tab
    }
}