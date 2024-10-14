package news.app.graduation.presentation.my_interface

interface OnClickReadOrSave {
    fun callback(tag: TagReadOrSave, data: Any? = null)

    enum class TagReadOrSave {
        ON_CLICK_ITEM
    }
}