package news.app.graduation.presentation.feature.m01_home.holder

import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemHeaderNewsBinding
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener
import news.app.graduation.presentation.shared_holder.BaseNewsHolder
import timber.log.Timber


class HeaderNewsHolder(private val binding: ItemHeaderNewsBinding) : BaseNewsHolder(binding.root) {
    fun bind(data: Item?, onClickItemHomeListener: OnClickItemHomeListener) {
        runCatching {
            with(binding) {
                Utility.setImage(context, imgItem, data?.descriptionParse?.imageUrl)
                txtTitle.text = data?.title
                txtTime.text = data?.pubDate
                txtDescription.text = data?.descriptionParse?.textDescription
                root.setOnClickListener {
                    onClickItemHomeListener.callback(OnClickItemHomeListener.TagClickItemHome.ON_CLICK_ITEM, data?.link)
                }
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}