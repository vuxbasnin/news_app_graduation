package news.app.graduation.presentation.core.widget

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SnapCenterListener @JvmOverloads constructor(private val obj: Any? = null) : RecyclerView.OnScrollListener() {
    interface CenterChangeListener {
        fun onCenterViewChange(view: View?, position: Int)
    }

    private var onScrollRecyclerview: OnScrollRecyclerview? = null
    fun setOnScrollRecyclerview(onScrollRecyclerview: OnScrollRecyclerview?) {
        this.onScrollRecyclerview = onScrollRecyclerview
    }

    override fun onScrollStateChanged(rv: RecyclerView, state: Int) {
        super.onScrollStateChanged(rv, state)
        val lm = rv.layoutManager as LinearLayoutManager? ?: return
        val mid = if (lm.orientation == LinearLayoutManager.HORIZONTAL) rv.width / 2 else rv.height / 2
        var minDistance = Int.MAX_VALUE
        var resultView: View? = null
        var position = 0
        var result = 0
        for (i in lm.findFirstVisibleItemPosition()..lm.findLastVisibleItemPosition()) {
            val view = lm.findViewByPosition(i)
            val difference = getDiff(view, mid, lm.orientation == LinearLayoutManager.HORIZONTAL)
            val distance = Math.abs(difference)
            if (distance < minDistance) {
                minDistance = distance
                resultView = view
                result = difference
                if (view == null) return
                position = lm.getPosition(view)
            }
        }
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            setScroll(rv, resultView, lm.orientation == LinearLayoutManager.HORIZONTAL, position, result)
        } else {
            onScrollRecyclerview?.onStartScroll(position)
        }
    }

    private fun setScroll(rv: RecyclerView, resultView: View?, horizontal: Boolean, position: Int, result: Int) {
        var result = result
        val adapter = rv.adapter
        if (adapter is CenterChangeListener) {
            (adapter as CenterChangeListener).onCenterViewChange(resultView, position)
        }
        if (obj != null) {
            (obj as CenterChangeListener).onCenterViewChange(resultView, position)
        }
        // to compensate rounding errors
        if (position == 0 && result == -1) {
            result = 0
        }
        //        LogUtils.e(check + " - " + position);
        if (check) {
            if (horizontal) {
                rv.smoothScrollBy(result, 0)
            } else {
                rv.smoothScrollBy(0, result)
            }
        } else {
            onScrollRecyclerview?.onScrolled(position)
        }
    }

    private var check = false
    fun setSmoothScroll(iCheck: Boolean) {
        check = iCheck
    }

    private fun getDiff(v: View?, mid: Int, horizontal: Boolean): Int {
        var i = 0
        if (v == null) return i
        try {
            i = if (horizontal) v.left + (v.right - v.left) / 2 - mid else v.top + (v.bottom - v.top) / 2 - mid
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return i
    }

}