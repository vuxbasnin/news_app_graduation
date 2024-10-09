package news.app.graduation.data.model.response.rss

data class RssResponse(
    val channelTitle: String? = null,
    val items: MutableList<Item> = mutableListOf(),
)
