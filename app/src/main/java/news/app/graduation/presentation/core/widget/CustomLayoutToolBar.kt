package news.app.graduation.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import news.app.graduation.core.common.clickWithThrottle
import news.app.graduation.databinding.CustomLayoutToolbarBinding
import news.app.graduation.presentation.NavigationManager

class CustomLayoutToolBar(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {
    var binding: CustomLayoutToolbarBinding? = null

    init {
        bindView()
    }

    private fun bindView() {
        binding =
            CustomLayoutToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding?.imgBack?.clickWithThrottle {
            NavigationManager.getInstance().popBackStack()
        }
    }

    fun setTitle(mTitle: String?, isTextAllCaps: Boolean? = true) {
        mTitle?.let { binding?.txtNameZone?.text = it }
        if (isTextAllCaps != null) {
            binding?.txtNameZone?.isAllCaps = isTextAllCaps
        }
    }

    fun hideShowIconSearch(isShow: Boolean) {
        binding?.imgSearch?.visibility = if (isShow) VISIBLE else GONE
    }

    fun hideShowIconBack(isShow: Boolean) {
        binding?.imgBack?.visibility = if (isShow) VISIBLE else INVISIBLE
    }
}