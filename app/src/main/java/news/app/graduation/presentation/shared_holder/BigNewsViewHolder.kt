package news.app.graduation.presentation.shared_holder

import news.app.graduation.core.common.clickWithThrottle
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.local.entity.NewsLocal
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemBigNewsBinding
import news.app.graduation.presentation.my_interface.OnClickReadOrSave

class BigNewsViewHolder(
    private val binding: ItemBigNewsBinding,
    private val onClickReadOrSave: OnClickReadOrSave?
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
                    onClickReadOrSave?.callback(
                        OnClickReadOrSave.TagReadOrSave.ON_CLICK_ITEM, Item(
                            title = newsData?.title ?: "",
                            link = newsData?.newsUrl ?: "",
                            description = newsData?.description ?: ""
                        )
                    )
                    markRead()
                }
                markIsRead(newsData)
            }
        }.onFailure { exception: Throwable ->
            exception.printStackTrace()
        }
    }
}