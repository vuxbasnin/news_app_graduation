package news.app.graduation.presentation.shared_holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item

abstract class BaseNewsHolder(view: View) : ViewHolder(view) {
    val context: Context = view.context
    fun markRead() {
        Utility.setMarkRead(itemView, true)
    }

    fun markIsRead(data: Any?) {
        when(data) {
            is Item -> {
                Utility.setMarkRead(itemView, data.newsId)
            }
        }
    }
}