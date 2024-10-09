package news.app.graduation.presentation.feature.m01_home.holder

import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.ItemNewsHolderBinding
import news.app.graduation.presentation.my_interface.OnClickItemHomeListener
import news.app.graduation.presentation.shared_holder.BaseNewsHolder

class NormalNewsHolder(private val binding: ItemNewsHolderBinding): BaseNewsHolder(binding.root) {
    fun bind(data: Item?, onClickItemHomeListener: OnClickItemHomeListener) {
        runCatching {
            with(binding) {
                txtTitle.text = data?.title
                txtDescription.text = data?.descriptionParse?.textDescription
                Utility.setImage(context, imgImage, data?.descriptionParse?.imageUrl)
                root.setOnClickListener {
                    onClickItemHomeListener.callback(OnClickItemHomeListener.TagClickItemHome.ON_CLICK_ITEM, data?.link)
                }
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}