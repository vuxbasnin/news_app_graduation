package news.app.graduation.data.model.response.rss

import news.app.graduation.core.common.extractDataFromDescription
import news.app.graduation.core.common.parseRssDescription
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item(
    @field:Path("item")
    @field:Element(name = "title", data = true)
    var title: String = "",

    @field:Path("item")
    @field:Element(name = "link", data = true)
    var link: String = "",

    @field:Path("item")
    @field:Element(name = "description", data = true)
    var description: String = "",

    @field:Element(name = "pubDate", data = true)
    var pubDate: String = "",

    @field:Element(name = "guid", data = true)
    var guid: String = ""
) {
    val indexLast = link.lastIndexOf("-")
    val indexLastDot = link.lastIndexOf(".")
    val newsId = link.substring(indexLast + 1, indexLastDot)

    val descriptionParse = description.extractDataFromDescription()
}

data class ParsedDescription(
    val link: String,
    val imageUrl: String,
    val textDescription: String
)