package news.app.graduation.data.model.response.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
    @field:Path("channel")
    @field:Element(name = "title", data = true)
    var title: String = "",

    @field:Path("channel")
    @field:Element(name = "link", data = true)
    var link: String = "",

    @field:Path("channel")
    @field:Element(name = "description", data = true)
    var description: String = "",

    @field:Element(name = "copyright", data = true)
    var copyright: String = "",

    @field:Element(name = "generator", data = true)
    var generator: String = "",

    @field:Element(name = "pubDate", data = true)
    var pubDate: String = "",

    @field:Element(name = "language", data = true)
    var language: String = "",

    @field:Element(name = "lastBuildDate", data = true)
    var lastBuildDate: String = "",

    @field:Element(name = "ttl", data = true)
    var ttl: Int = 0,

    @field:Element(name = "image", data = true)
    var image: Image? = null,

    @field:ElementList(inline = true)
    var items: MutableList<Item> = mutableListOf()
)
