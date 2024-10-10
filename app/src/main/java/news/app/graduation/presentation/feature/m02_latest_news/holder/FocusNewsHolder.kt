package news.app.graduation.presentation.feature.m02_latest_news.holder

import androidx.viewpager.widget.ViewPager
import news.app.graduation.core.utils.DeviceUtil
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemFocusNewsBinding
import news.app.graduation.presentation.shared_holder.BaseNewsHolder

class FocusNewsHolder(private val binding: ItemFocusNewsBinding) : BaseNewsHolder(binding.root) {
    private var pagerAdapter: ViewPagerFocusAdapter? = null

    init {
        binding.layoutViewPager.clipToPadding = false
        binding.layoutViewPager.setPadding(0, 0, getPaddingItem, 0)
    }

    fun bind(data: MutableList<Item>) {
        val dataPager = mutableListOf<Item>()
        data.let { dataPager.addAll(it) }
        setUpViewPager(dataPager)

        setupViewPagerPadding(dataPager.size)
    }

    private fun setUpViewPager(data: MutableList<Item>, isSetBg: Boolean? = null) {
        pagerAdapter = ViewPagerFocusAdapter(
            binding.root.context,
            isSetBg
        )
        pagerAdapter?.setData(data)
        binding.layoutViewPager.adapter = pagerAdapter
        binding.layoutIndicator.setupWithViewPager(binding.layoutViewPager, true)
    }

    private fun setupViewPagerPadding(size: Int) {
        if (size == 1) {
            setNoPadding()
        } else {
            binding.layoutViewPager.setPadding(0, 0, getPaddingItem, 0)
            binding.layoutViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {

                }

                override fun onPageSelected(position: Int) {
                    setPadding(position, size)
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })
        }
    }

    private fun setNoPadding() {
        binding.layoutViewPager.setPadding(0, 0, 0, 0)
    }

    fun setPadding(position: Int, size: Int) {
        when (position) {
            0 -> {
                binding.layoutViewPager.setPadding(0, 0, getPaddingItem, 0)
            }

            size - 1 -> {
                binding.layoutViewPager.setPadding(getPaddingItem, 0, 0, 0)
            }

            else -> {
                binding.layoutViewPager.setPadding(getPaddingItem / 2, 0, getPaddingItem / 2, 0)
            }
        }
    }

    private val getPaddingItem: Int
        get() {
            val displayMetrics = DeviceUtil.getDisplayMetrics(context)
            val itemWidth = (displayMetrics?.widthPixels?.times(0.45))?.toInt()
            val padding = (itemWidth?.let { displayMetrics.widthPixels.minus(it) })?.div(2)
            return padding ?: 0
        }
}