package news.app.graduation.presentation.my_interface

import news.app.graduation.data.model.response.menu.Zone

interface OnClickMenuListener {
    fun callback(tag: TagMenu, data: Any? = null)

    enum class TagMenu {
        NONE,
        ON_CLICK_ITEM
    }

    data class CategorySelected(val parentCategory: Zone?, val selectedCategory: Zone?, val positionSelected: Int? = null)
}