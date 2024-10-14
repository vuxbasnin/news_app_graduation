package news.app.graduation.presentation.my_interface

interface OnClickItemCategory {
    fun callback(tag: TagCategory, data: Any? = null)

    enum class TagCategory {
        NONE,
        ON_CLICK_ITEM
    }
}