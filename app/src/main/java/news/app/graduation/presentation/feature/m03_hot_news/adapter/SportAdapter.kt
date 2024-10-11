package news.app.graduation.presentation.feature.m03_hot_news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemNewsHolderBinding
import news.app.graduation.presentation.feature.m01_home.holder.NormalNewsHolder
import news.app.graduation.presentation.my_interface.OnClickItemSportListener

class SportAdapter(val context: Context, private val onClickItemSportListener: OnClickItemSportListener) : RecyclerView.Adapter<ViewHolder>() {
    private val listData: MutableList<Item> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Item>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context)
        return NormalNewsHolder(
            ItemNewsHolderBinding.inflate(inflate, parent, false)
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData.getOrNull(position)
        when (holder) {
            is NormalNewsHolder -> holder.bind(
                data = item,
                onClickItemSportListener = onClickItemSportListener
            )
        }
    }
}