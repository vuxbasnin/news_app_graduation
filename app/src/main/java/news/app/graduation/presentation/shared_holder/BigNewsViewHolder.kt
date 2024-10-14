package news.app.graduation.presentation.shared_holder

import news.app.graduation.core.common.clickWithThrottle
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.local.entity.NewsLocal
import news.app.graduation.databinding.ItemBigNewsBinding

class BigNewsViewHolder(
    private val binding: ItemBigNewsBinding,
) :
    BaseNewsHolder(binding.root) {
    fun bind(newsData: NewsLocal?) {
        runCatching {
            with(binding) {
                newsData?.run {
                    Utility.setImage(binding.root.context, imgItem, imageUrl)
                    txtTitle.text = title
                    txtDescription.text = description
                }
                root.clickWithThrottle {

                    markRead()
                }
                markIsRead(newsData)
            }
        }.onFailure { exception: Throwable ->
            exception.printStackTrace()
        }
    }
}