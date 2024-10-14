package news.app.graduation.presentation.feature.m04_menu.holder

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import news.app.graduation.R
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.show
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.menu.Zone
import news.app.graduation.databinding.ItemCategoryMenuBinding
import news.app.graduation.presentation.my_interface.OnClickMenuListener
import timber.log.Timber

class CategoryHolder(
    private val binding: ItemCategoryMenuBinding,
    val context: Context,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Zone?, onClickMenuListener: OnClickMenuListener) {
        runCatching {
            binding.tvTitle.text = data?.title
            if ((data?.categories?.size ?: 0) < 2) {
                binding.imgShowChild.hide()
            } else {
                if (binding.wrapElementChild.childCount != 0) {
                    binding.wrapElementChild.removeAllViews()
                }
                binding.imgShowChild.show()
                data?.categories?.forEachIndexed { index, categories ->
                    if (index != 0) {
                        val textView = TextView(context)
                        categories.name?.let { it1 -> textView.text = it1 }
                        textView.setTextAppearance(R.style.TextInter16SubTitleRegular400)
                        val param = ActionBar.LayoutParams(
                            ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.WRAP_CONTENT
                        )
                        param.setMargins(0, 0, 0, Utility.dpToPx(20F))
                        textView.layoutParams = param
                        binding.wrapElementChild.addView(textView)
                        textView.setOnClickListener {
                            onClickMenuListener.callback(OnClickMenuListener.TagMenu.ON_CLICK_ITEM,
                                OnClickMenuListener.CategorySelected(data, data, index)
                            )                    }
                    }
                }
            }
            binding.imgShowChild.setOnClickListener {
                if (data?.isShowCategoryChild != true) {
                    binding.wrapElementChild.show()
                    binding.imgShowChild.setImageResource(R.drawable.ic_arrow_up)
                    data?.apply { isShowCategoryChild = true }
                } else {
                    binding.wrapElementChild.hide()
                    binding.imgShowChild.setImageResource(R.drawable.ic_arrow_down)
                    data.apply { isShowCategoryChild = false }
                }
            }
            binding.root.setOnClickListener {
                onClickMenuListener.callback(OnClickMenuListener.TagMenu.ON_CLICK_ITEM,
                    OnClickMenuListener.CategorySelected(data, data)
                )
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}