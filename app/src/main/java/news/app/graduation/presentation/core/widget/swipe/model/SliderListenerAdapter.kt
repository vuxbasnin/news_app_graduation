package com.news.thanhnien.presentation.core.widget.swipe.model

class SliderListenerAdapter : SliderListener {
    override fun onSlideStateChanged(state: Int) {}
    override fun onSlideChange(percent: Float) {}
    override fun onSlideOpened() {}
    override fun onSlideClosed(): Boolean {
        return false
    }
}
