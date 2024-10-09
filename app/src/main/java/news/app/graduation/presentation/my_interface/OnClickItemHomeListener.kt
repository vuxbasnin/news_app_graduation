package news.app.graduation.presentation.my_interface

interface OnClickItemHomeListener {
    fun callback(tag: TagClickItemHome, data: Any? = null)

    enum class TagClickItemHome {
        NONE,
        ON_CLICK_ITEM
    }
}