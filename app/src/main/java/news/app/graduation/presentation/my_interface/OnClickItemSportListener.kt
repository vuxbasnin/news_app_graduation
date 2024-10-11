package news.app.graduation.presentation.my_interface

interface OnClickItemSportListener {
    fun callback(tag: TagSport, data: Any? = null)

    enum class TagSport {
        NONE,
        ON_CLICK_ITEM
    }
}
