package news.app.graduation.presentation.feature.m02_latest_news.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import news.app.graduation.R
import news.app.graduation.core.common.setTextCustomEllipsize
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.presentation.my_interface.OnClickItemStarListener

class ViewPagerFocusAdapter(
    private val context: Context,
    private val isSetBg: Boolean? = null,
    private val onClickItemStarListener: OnClickItemStarListener? = null,
) : PagerAdapter() {
    private var listData: MutableList<Item>? = null
    private var layoutInflater: LayoutInflater? = null

    fun setData(data: MutableList<Item>?) {
        this.listData?.clear()
        this.listData = data
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listData?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater?
        val view: View? = layoutInflater?.inflate(R.layout.item_focus, null)
        val imageView = view?.findViewById<ImageView>(R.id.imgAvatar)
        val imgTypeNews = view?.findViewById<ImageView>(R.id.imgType)
        val title = view?.findViewById<TextView>(R.id.tvTitle)

        imageView?.let {
            Utility.setImage(
                context,
                it,
                listData?.getOrNull(position)?.descriptionParse?.imageUrl
            )
        }
        title?.setTextCustomEllipsize(
            listData?.getOrNull(position)?.title
        )

        if (isSetBg == true) {
            view?.setBackgroundColor(context.resources.getColor(R.color.color_f9))
        }

        view?.let { markIsRead(it, listData?.getOrNull(position)) }

        view?.setOnClickListener {
            onClickItemStarListener?.callback(OnClickItemStarListener.TagStar.ON_CLICK_ITEM, listData?.getOrNull(position))
        }

        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view ?: View(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    fun markIsRead(itemView: View, data: Any?) {
        when (data) {
            is Item -> {
                Utility.setMarkRead(itemView, data.newsId)
            }
        }
    }
}