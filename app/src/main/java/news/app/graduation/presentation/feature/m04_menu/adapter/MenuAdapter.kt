package news.app.graduation.presentation.feature.m04_menu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import news.app.graduation.data.model.response.menu.Zone
import news.app.graduation.databinding.ItemCategoryMenuBinding
import news.app.graduation.presentation.feature.m04_menu.holder.CategoryHolder
import news.app.graduation.presentation.my_interface.OnClickMenuListener

class MenuAdapter(val context: Context, private val onClickMenuListener: OnClickMenuListener) : RecyclerView.Adapter<ViewHolder>() {
    private val listData: MutableList<Zone> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Zone>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context)
        return CategoryHolder(
            ItemCategoryMenuBinding.inflate(inflate, parent, false),
            context
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData.getOrNull(position)
        when (holder) {
            is CategoryHolder -> holder.bind(
                item,
                onClickMenuListener
            )
        }
    }
}