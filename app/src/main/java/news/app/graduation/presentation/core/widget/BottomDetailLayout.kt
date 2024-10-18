package news.app.graduation.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import news.app.graduation.R
import news.app.graduation.databinding.LayoutBottomDetailBinding
import news.app.graduation.presentation.my_interface.OnClickBottomDetailNative
import timber.log.Timber

class BottomDetailLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    var eventListener: OnClickBottomDetailNative? = null
    var binding: LayoutBottomDetailBinding? = null
    private var isShow = false

    init {
        bindView()
    }

    fun hideChangeText() {
         binding?.imgDetailChangeText?.visibility = INVISIBLE
    }

    fun setSaveNews(isAdd: Boolean) {
        if (isAdd) {
            Timber.d("NINVB => da luu")
            binding?.imgDetailSaveNews?.setImageResource(R.drawable.ic_detail_save_news_add)
        } else {
            Timber.d("NINVB => chua luu")
            binding?.imgDetailSaveNews?.setImageResource(R.drawable.ic_detail_save_news)
        }
    }

    fun hideClick() {
        binding?.imgDetailChangeText?.isClickable = false
        binding?.imgDetailSaveNews?.isClickable = false
        binding?.imgDetailShare?.isClickable = false
    }

    private fun bindView() {
        binding = LayoutBottomDetailBinding.inflate(LayoutInflater.from(context), this, true)
        val mClick = OnClickListener { p0 ->
            when (p0?.id) {
                R.id.img_detail_back -> {
                    changeDisplayOptions(true)
                    eventListener?.onClickClose()
                }
                R.id.img_detail_comment -> {
                    Toast.makeText(context, "Tính năng không khả dụng", Toast.LENGTH_SHORT).show()
                }

                R.id.img_detail_share -> {
                    changeDisplayOptions(true)
                    eventListener?.onClickShare()
                }

                R.id.img_detail_change_text -> {
                    changeDisplayOptions(false)
                }

                R.id.fl_shadow -> {
                    changeDisplayOptions(true)
                }

                R.id.img_detail_save_news -> {
                    changeDisplayOptions(true)
                    eventListener?.onCLickSaveNews()
                }
            }
        }
        binding?.run {
            imgDetailBack.setOnClickListener(mClick)
            imgDetailShare.setOnClickListener(mClick)
            imgDetailChangeText.setOnClickListener(mClick)
            imgDetailSaveNews.setOnClickListener(mClick)
            flShadow.setOnClickListener(mClick)
            imgSizeDown.setOnClickListener(mClick)
            imgDetailComment.setOnClickListener(mClick)
        }
    }

    private fun changeDisplayOptions(isClickOther: Boolean) {
        if (isClickOther) {
            isShow = false
            binding?.ctlChangeSize?.visibility = GONE
            binding?.flShadow?.visibility = GONE
            binding?.imgDetailChangeText?.setImageResource(R.drawable.ic_detail_change_text)
        } else {
            if (!isShow) {
                isShow = true
                binding?.ctlChangeSize?.visibility = VISIBLE
                binding?.flShadow?.visibility = VISIBLE
                binding?.imgDetailChangeText?.setImageResource(R.drawable.ic_detail_change_text_selected)
            } else {
                binding?.imgDetailChangeText?.setImageResource(R.drawable.ic_detail_change_text)
                isShow = false
                binding?.ctlChangeSize?.visibility = GONE
                binding?.flShadow?.visibility = GONE
            }
        }
    }
}