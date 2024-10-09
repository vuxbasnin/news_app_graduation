package news.app.graduation.presentation.feature.m01_home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemHeaderNewsBinding
import news.app.graduation.databinding.ItemNewsHolderBinding
import news.app.graduation.presentation.feature.m01_home.holder.HeaderNewsHolder
import news.app.graduation.presentation.feature.m01_home.holder.NormalNewsHolder
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener

class HomeAdapter(val context: Context, private val onClickItemHomeListener: OnClickItemHomeListener) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        const val NEWS_HEADER = 1
        const val NEWS_NORMAL = 2
    }

    private val listData: MutableList<Item> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Item>) {
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

            else -> NormalNewsHolder(
                ItemNewsHolderBinding.inflate(inflate, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % 8) {
            0 -> NEWS_HEADER
            else -> NEWS_NORMAL
        }
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData.getOrNull(position)
        when(holder) {
            is HeaderNewsHolder -> {
                holder.bind(data, onClickItemHomeListener)
            }
            else -> {
                (holder as? NormalNewsHolder)?.bind(data, onClickItemHomeListener)
            }
        }
    }
}