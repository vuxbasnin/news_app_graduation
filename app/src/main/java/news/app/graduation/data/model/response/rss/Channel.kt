package news.app.graduation.data.model.response.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "link")
    var link: String = "",

    @field:Element(name = "description", data = true)
    var description: String = "",

    @field:Element(name = "copyright")
    var copyright: String = "",

    @field:Element(name = "generator")
    var generator: String = "",

    @field:Element(name = "pubDate")
    var pubDate: String = "",

    @field:Element(name = "language")
    var language: String = "",

    @field:Element(name = "lastBuildDate")
    var lastBuildDate: String = "",

    @field:Element(name = "ttl")
    var ttl: Int = 0,

    @field:Element(name = "image")
    var image: Image? = null,

    @field:ElementList(inline = true)
    var items: List<Item> = listOf()
)
