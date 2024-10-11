package news.app.graduation.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import news.app.graduation.R

class CustomTabCategory(context: Context, val isBold: Boolean = false, attributeSet: AttributeSet? = null) : LinearLayout(context, attributeSet) {

    var text: TextView

    init {
        inflate(getContext(), R.layout.custom_tab_viewpager_category, this)
        text = findViewById(R.id.tv_item_category_name)
        if (isBold) {
            text.setTextAppearance(R.style.TextInter16TitleBold700)
            text.isAllCaps = true
        } else {
            text.setTextAppearance(R.style.TextInter14TitleSemiBold600)
            text.isAllCaps = false
        }
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val color = if (selected) {
            R.color.primary
        } else {
            R.color.color_black_45

        }
        text.setTextColor(ContextCompat.getColor(context, color))
    }
}