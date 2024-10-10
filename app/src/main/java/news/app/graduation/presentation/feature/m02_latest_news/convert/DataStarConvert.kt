package news.app.graduation.presentation.feature.m02_latest_news.convert

import news.app.graduation.data.model.response.rss.Item

sealed class DataStarConvert {
    data class ItemHeader(val data: Item?): DataStarConvert()
    data class ListItemFocus(val data: MutableList<Item>?): DataStarConvert()
    data class ListItemOther(val data: Item?): DataStarConvert()
}