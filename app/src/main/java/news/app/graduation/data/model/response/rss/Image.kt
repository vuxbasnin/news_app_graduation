package news.app.graduation.data.model.response.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class Image(
    @field:Element(name = "url")
    var url: String = "",

    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "link")
    var link: String = ""
)