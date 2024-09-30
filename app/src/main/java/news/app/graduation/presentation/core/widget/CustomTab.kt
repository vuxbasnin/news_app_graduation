package news.app.graduation.presentation.core.widget

import android.content.Context
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import news.app.graduation.R

class CustomTab(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {
    private var drawableSelected: Drawable? = null
    private var drawableNormal: Drawable? = null
    var icon: ImageView
    var text: TextView

    init {
        inflate(getContext(), R.layout.custom_tab_viewpager, this)
        icon = findViewById(R.id.imgIconTab)
        text = findViewById(R.id.tvIconTab)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val color = R.color.black
        val style = if (selected) {
            BOLD
        } else {
            NORMAL
        }
        text.setTextColor(ContextCompat.getColor(context, color))
        text.setTypeface(null, style)
        icon.setImageDrawable(if (selected) drawableSelected else drawableNormal)
    }

    fun setDrawable(normal: Drawable?, selected: Drawable?) {
        drawableNormal = normal
        icon.setImageDrawable(drawableNormal)
        drawableSelected = selected
    }
}