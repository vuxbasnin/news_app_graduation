package news.app.graduation.presentation.core.widget

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.math.abs

abstract class EndlessRecyclerViewScrollListener(val mLayoutManager: RecyclerView.LayoutManager?, var currentPage: Int = 1) :
    RecyclerView.OnScrollListener() {
    // The minimum amount of items to have bFelow your current scroll position
    // before loading more.
    private val visibleThreshold = 3

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // Sets the starting page index
    private val startingPageIndex = 1

    // lastVisibleItemPosition
    private var lastVisibleItemPosition = 0

    // firstVisibleItemPosition
    private var firstVisibleItemPosition = 0
    private val onScrollRecyclerview: OnScrollRecyclerview? = null

    var isFull = false
    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (mLayoutManager == null)
            return
        val totalItemCount = mLayoutManager.itemCount
        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }

            is GridLayoutManager -> {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                firstVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }

            is LinearLayoutManager -> {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()
            }
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        //   Log.d("EndlessScrollListener","Total item count is " + totalItemCount + " Previous Item count is " + previousTotalItemCount);
        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
//        LogUtils.d("EndlessRecyclerViewScrollListener 2222  totalItemCount   " + totalItemCount + "  previousTotalItemCount  " + previousTotalItemCount);
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        //Log.d("EndlessScrollListener", "loading is " + loading + " currentPage is " + currentPage + "lastVisible " + lastVisibleItemPosition + " total Item Count is " + totalItemCount);
        currentPosition(lastVisibleItemPosition)
        getDy(dy)
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            onLoadMore(currentPage, totalItemCount, view)
            currentPage++
            loading = true
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
        super.onScrollStateChanged(recyclerView, state)
        val lm = recyclerView.layoutManager as LinearLayoutManager
        val mid = if (lm.orientation == LinearLayoutManager.HORIZONTAL) recyclerView.width / 2 else recyclerView.height / 2
        var minDistance = Int.MAX_VALUE
        var resultView: View? = null
        var position = 0
        var result = 0
        for (i in lm.findFirstVisibleItemPosition()..lm.findLastVisibleItemPosition()) {
            val view = lm.findViewByPosition(i)
            val difference = getDiff(view, mid, lm.orientation == LinearLayoutManager.HORIZONTAL)
            val distance = abs(difference)
            if (distance < minDistance) {
                minDistance = distance
                resultView = view
                result = difference
                if (view == null) return
                position = lm.getPosition(view)
            }
        }
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            setScroll(recyclerView, resultView, lm.orientation == LinearLayoutManager.HORIZONTAL, position, result)
        } else {
            onScrollRecyclerview?.onStartScroll(position)
        }
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

    // Call this method whenever performing new searches
    fun resetState() {
        isFull = false
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true
    }

    private fun setScroll(rv: RecyclerView, resultView: View?, horizontal: Boolean, position: Int, result: Int) {
        val adapter = rv.adapter
        if (adapter is SnapCenterListener.CenterChangeListener) {
            (adapter as SnapCenterListener.CenterChangeListener).onCenterViewChange(resultView, position)
        }
        // to compensate rounding errors
        //        LogUtils.e(check + " - " + position);
        onScrollCenter(position)
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
    abstract fun currentPosition(position: Int)
    abstract fun getDy(dy: Int)

    open fun onScrollCenter(position: Int) {

    }

    companion object {
        private val TAG = EndlessRecyclerViewScrollListener::class.java.simpleName
    }
}