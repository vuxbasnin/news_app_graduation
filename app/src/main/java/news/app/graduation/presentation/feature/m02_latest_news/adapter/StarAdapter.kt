package news.app.graduation.presentation.feature.m02_latest_news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemFocusNewsBinding
import news.app.graduation.databinding.ItemHeaderNewsBinding
import news.app.graduation.databinding.ItemNewsHolderBinding
import news.app.graduation.presentation.feature.m01_home.holder.HeaderNewsHolder
import news.app.graduation.presentation.feature.m01_home.holder.NormalNewsHolder
import news.app.graduation.presentation.feature.m02_latest_news.convert.DataStarConvert
import news.app.graduation.presentation.feature.m02_latest_news.holder.FocusNewsHolder

class StarAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val listData: MutableList<DataStarConvert> = arrayListOf()

    companion object {
        const val NEWS_HEADER = 1
        const val NEWS_FOCUS = 2
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<DataStarConvert>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context)
        return when (viewType) {
            NEWS_HEADER -> HeaderNewsHolder(
                ItemHeaderNewsBinding.inflate(inflate, parent, false)
            )

            NEWS_FOCUS -> FocusNewsHolder(
                ItemFocusNewsBinding.inflate(inflate, parent, false)
            )

            else -> NormalNewsHolder(
                ItemNewsHolderBinding.inflate(inflate, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = listData.getOrNull(position)
        return when(item) {
            is DataStarConvert.ItemHeader -> NEWS_HEADER
            is DataStarConvert.ListItemFocus -> NEWS_FOCUS
            else -> super.getItemViewType(position)
        }
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData.getOrNull(position)
        when(holder) {
            is HeaderNewsHolder -> holder.bind((item as? DataStarConvert.ItemHeader)?.data)
            is FocusNewsHolder -> (item as? DataStarConvert.ListItemFocus)?.data?.let {
                holder.bind(
                    it
                )
            }
            is NormalNewsHolder -> (item as? DataStarConvert.ListItemOther)?.data?.let {
                holder.bind(
                    (item as? DataStarConvert.ListItemOther)?.data
                )
            }
        }
    }
}