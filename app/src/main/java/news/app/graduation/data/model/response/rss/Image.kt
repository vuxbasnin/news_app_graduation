package news.app.graduation.data.model.response.rss

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class Image(
    @field:Element(name = "url", data = true)
    var url: String = "",

    @field:Path("image")
    @field:Element(name = "title", data = true)
    var title: String = "",

    @field:Path("image")
    @field:Element(name = "link", data = true)
    var link: String = ""
)