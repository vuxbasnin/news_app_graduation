package news.app.graduation.presentation.feature.m09_read_or_save.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import news.app.graduation.data.local.entity.NewsLocal
import news.app.graduation.databinding.ItemBigNewsBinding
import news.app.graduation.presentation.my_interface.OnClickReadOrSave
import news.app.graduation.presentation.shared_holder.BigNewsViewHolder

class PersonalAdapter(val context: Context, private val onClickReadOrSave: OnClickReadOrSave? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val listData: MutableList<NewsLocal> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<NewsLocal>) {
        data.sortByDescending { newsLocal: NewsLocal -> newsLocal.uid }
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context)
        return BigNewsViewHolder(
            ItemBigNewsBinding.inflate(
                inflate,
                parent,
                false
            ),
            onClickReadOrSave
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listData.getOrNull(position)
        when (holder) {
            is BigNewsViewHolder -> holder.bind(item)
        }
    }
}