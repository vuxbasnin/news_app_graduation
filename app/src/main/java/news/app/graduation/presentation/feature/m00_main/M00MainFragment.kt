package news.app.graduation.presentation.feature.m00_main

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.R
import news.app.graduation.core.common.Constants.TabMain.TAB_0
import news.app.graduation.core.common.Constants.TabMain.TAB_1
import news.app.graduation.core.common.Constants.TabMain.TAB_2
import news.app.graduation.core.common.Constants.TabMain.TAB_3
import news.app.graduation.core.common.reduceDragSensitivity
import news.app.graduation.databinding.M00FragmentMainBinding
import news.app.graduation.presentation.core.base.BaseTabFragment
import news.app.graduation.presentation.core.base_adapter.MyViewPagerAdapter
import news.app.graduation.presentation.core.widget.CustomTab
import news.app.graduation.presentation.feature.m01_home.M01HomeFragment
import news.app.graduation.presentation.feature.m02_latest_news.M02StarFragment
import news.app.graduation.presentation.feature.m03_hot_news.M03SportNewsFragment
import news.app.graduation.presentation.feature.m04_menu.M04MenuFragment
import news.app.graduation.presentation.feature.m05_detail.M05DetailNewsFragment

@AndroidEntryPoint
class M00MainFragment : BaseTabFragment<M00FragmentMainBinding>(M00FragmentMainBinding::inflate) {
    data class MainTabData(
        @DrawableRes val icon: Int,
        @DrawableRes val iconSelected: Int,
        @StringRes val title: Int
    )

    private val listTab = listOf(
        MainTabData(R.drawable.ic_tab_home, R.drawable.ic_tab_home_selected, R.string.tab_1),
        MainTabData(R.drawable.ic_tab_star, R.drawable.ic_tab_star_selected, R.string.tab_2),
        MainTabData(
            R.drawable.ic_tab_sport,
            R.drawable.ic_tab_sport_selected,
            R.string.tab_3
        ),
        MainTabData(
            R.drawable.ic_tab_menu,
            R.drawable.ic_tab_menu_selected,
            R.string.tab_4
        ),
    )

    private var pagerAdapter: MyViewPagerAdapter<MainTabData, Fragment>? = null
    override fun getViewPager(): ViewPager2 = binding.pager

    override fun initView() {
        pagerAdapter = MyViewPagerAdapter(this, listTab) { _, position ->
            when (position) {
                TAB_0 -> M01HomeFragment()
                TAB_1 -> M02StarFragment()
                TAB_2 -> M03SportNewsFragment()
                TAB_3 -> M04MenuFragment()
                else -> M05DetailNewsFragment()
            }
        }
        binding.pager.adapter = pagerAdapter
        binding.pager.reduceDragSensitivity()
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            val customViewTab = getCustomViewTab(
                ContextCompat.getDrawable(requireContext(), listTab.get(position).icon),
                ContextCompat.getDrawable(requireContext(), listTab.get(position).iconSelected),
                getString(listTab[position].title)
            )
            tab.customView = customViewTab
        }.attach()
        binding.pager.offscreenPageLimit = listTab.size
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                (tab?.customView as CustomTab).isSelected = true
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                (tab?.customView as CustomTab?)?.isSelected = false
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabView = binding.tabLayout.getTabAt(i)?.view
            tabView?.setPadding(0, 0, 0, 0)
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }

    fun getCustomViewTab(image: Drawable?, imageSelected: Drawable?, text: String): CustomTab {
        val tab = CustomTab(requireContext())
        tab.setDrawable(image, imageSelected)
        tab.text.text = text
        return tab
    }
}