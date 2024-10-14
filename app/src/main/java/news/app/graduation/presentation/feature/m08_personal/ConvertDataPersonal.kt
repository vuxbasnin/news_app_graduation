package news.app.graduation.presentation.feature.m08_personal

import news.app.graduation.data.model.response.rss.Item

sealed class ConvertDataPersonal {
    data class ItemSave(val data: Item?): ConvertDataPersonal()
    data class ItemRead(val data: Item?): ConvertDataPersonal()
    data class Title(val name: String?): ConvertDataPersonal()
}