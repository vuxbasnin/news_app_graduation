package news.app.graduation.presentation.core.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import news.app.graduation.R


class SimpleDividerItemDecoration(
    val context: Context,
    @DrawableRes drawable: Int? = null,
    val recyclerView: RecyclerView? = null
) :
    RecyclerView.ItemDecoration() {
    private val mDivider: Drawable?
    private val listIgnorePosition: ArrayList<Int> = arrayListOf()

    init {
        mDivider = ContextCompat.getDrawable(context, drawable ?: R.drawable.line_padding_item)
    }

    fun addIgnorePosition(listPosIgnore: List<Int>) {
        listIgnorePosition.clear()
        listIgnorePosition.addAll(listPosIgnore)

    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)
            val pos = (recyclerView?.layoutManager as LinearLayoutManager?)?.getPosition(child)
            if (!listIgnorePosition.contains(pos)) {
                val params = child.layoutParams as RecyclerView.LayoutParams
                val dividerTop = child.bottom + params.bottomMargin
                val dividerBottom: Int = dividerTop + (mDivider?.intrinsicHeight ?: 0)
                mDivider?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                mDivider?.draw(canvas)
            }
        }
    }
}