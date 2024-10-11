package news.app.graduation.presentation.my_interface

interface OnClickItemStarListener {
    fun callback(tag: TagStar, data: Any? = null)

    enum class TagStar {
        NONE,
        ON_CLICK_ITEM
    }
}
