package news.app.graduation.presentation.feature.m01_home.holder

import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemHeaderNewsBinding
import news.app.graduation.presentation.my_interface.OnClickItemCategory
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener
import news.app.graduation.presentation.my_interface.OnClickItemStarListener
import news.app.graduation.presentation.shared_holder.BaseNewsHolder


class HeaderNewsHolder(private val binding: ItemHeaderNewsBinding) : BaseNewsHolder(binding.root) {
    fun bind(
        data: Item?,
        onClickItemStarListener: OnClickItemStarListener? = null,
        onClickItemHomeListener: OnClickItemHomeListener? = null,
        onClickItemCategory: OnClickItemCategory? = null
    ) {
        runCatching {
            with(binding) {
                Utility.setImage(context, imgItem, data?.descriptionParse?.imageUrl)
                txtTitle.text = data?.title
                txtTime.text = data?.pubDate
                txtDescription.text = data?.descriptionParse?.textDescription
                root.setOnClickListener {
                    onClickItemHomeListener?.callback(
                        OnClickItemHomeListener.TagClickItemHome.ON_CLICK_ITEM,
                        data
                    )

                    onClickItemStarListener?.callback(
                        OnClickItemStarListener.TagStar.ON_CLICK_ITEM,
                        data
                    )

                    onClickItemCategory?.callback(
                        OnClickItemCategory.TagCategory.ON_CLICK_ITEM,
                        data
                    )
                    markRead()
                }
                markIsRead(data)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}