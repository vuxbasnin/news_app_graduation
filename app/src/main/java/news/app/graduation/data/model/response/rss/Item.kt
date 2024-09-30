package news.app.graduation.data.model.response.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item(
    @field:Element(name = "title", data = true)
    var title: String = "",

    @field:Element(name = "link")
    var link: String = "",

    @field:Element(name = "description", data = true)
    var description: String = "",

    @field:Element(name = "pubDate")
    var pubDate: String = "",

    @field:Element(name = "guid")
    var guid: String = ""
)