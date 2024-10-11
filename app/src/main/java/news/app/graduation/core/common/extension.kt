package news.app.graduation.core.common

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import news.app.graduation.core.utils.CommonUtils
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.data.model.response.rss.ParsedDescription
import news.app.graduation.data.model.response.rss.RssResponse
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.widget.SimpleDividerItemDecoration
import news.app.graduation.presentation.feature.m05_detail.M05DetailNewsFragment
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.Serializable
import java.io.StringReader
import kotlin.math.ceil

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun Any?.toJson(): String = if (this == null) "null" else Gson().toJson(this)

fun ViewPager2.reduceDragSensitivity(f: Int = 3) {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView
    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * f)       // "8" was obtained experimentally
}

fun RecyclerView.addDivider(@DrawableRes drawable: Int? = null) {
    addItemDecoration(SimpleDividerItemDecoration(context, drawable, this))
}

// Truyền 1 list Position , giữa ở cuối item nào mà không muốn Có line thì thêm vào list
fun RecyclerView.addAddIgnoreDivider(listPosIgnore: List<Int>) {
    val listCount = itemDecorationCount
    if (listCount > 0) {
        for (i in 0..listCount) {
            val item = getItemDecorationAt(i)
            if (item is SimpleDividerItemDecoration) {
                item.addIgnorePosition(listPosIgnore)
                return
            }
        }
    }
}

fun View.markRead(isRead: Boolean) {
    if (this is ViewGroup) {
        this.children.forEach {
            it.markRead(isRead)
        }
    } else if (this !is ImageView)
        if (isRead) {
            this.alpha = 0.7f
        } else {
            this.alpha = 1f
        }
}

fun View?.visible(isVisible: Boolean?) {
    this?.visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

fun String.parseRssFeed(): RssResponse? {
    val factory = XmlPullParserFactory.newInstance()
    factory.isNamespaceAware = true
    val parser = factory.newPullParser()
    parser.setInput(StringReader(this))

    var eventType = parser.eventType
    var tagName: String?
    var title: String? = null
    var link: String? = null
    var description: String? = null
    var pubDate: String? = null
    var channelTitle: String? = null
    val items = mutableListOf<Item>()

    while (eventType != XmlPullParser.END_DOCUMENT) {
        tagName = parser.name
        when (eventType) {
            XmlPullParser.START_TAG -> {
                when (tagName) {
                    "title" -> if (channelTitle == null) {
                        channelTitle = parser.nextText()
                    } else {
                        title = parser.nextText()
                    }

                    "link" -> link = parser.nextText()
                    "description" -> description = parser.nextText()
                    "pubDate" -> pubDate = parser.nextText()
                }
            }

            XmlPullParser.END_TAG -> {
                if (tagName == "item" && title != null && link != null && description != null && pubDate != null) {
                    val item = Item(title, link, description, pubDate)
                    items.add(item)
                    title = null
                    link = null
                    description = null
                    pubDate = null
                }
            }
        }
        eventType = parser.next()
    }

    return if (channelTitle != null) RssResponse(channelTitle, items) else null
}

fun String.parseRssDescription(): String? {
    val factory = XmlPullParserFactory.newInstance()
    factory.isNamespaceAware = true
    val parser = factory.newPullParser()

    parser.setInput(StringReader(this))

    var eventType = parser.eventType
    var tagName: String?
    var description: String? = null

    while (eventType != XmlPullParser.END_DOCUMENT) {
        tagName = parser.name
        when (eventType) {
            XmlPullParser.START_TAG -> {
                if (tagName == "description") {
                    // Lấy nội dung thô từ <description>
                    description = parser.nextText()  // Đây là dữ liệu CDATA chứa HTML
                }
            }
        }
        eventType = parser.next()
    }

    return description  // Trả về nội dung thô của thẻ <description>
}

// Hàm xử lý nội dung trong thẻ <description> để tách các giá trị
fun String.extractDataFromDescription(): ParsedDescription {
    val doc: Document = Jsoup.parse(this) // Parse nội dung HTML bên trong CDATA

    // Lấy giá trị của thẻ <a>
    val linkElement: Element? = doc.selectFirst("a")
    val imgElement: Element? = doc.selectFirst("img")
    val textDescription: String = doc.text() // Phần mô tả văn bản

    // Lấy thuộc tính href của thẻ <a> và src của thẻ <img>
    val link = linkElement?.attr("href") ?: ""
    val imageUrl = imgElement?.attr("src") ?: ""

    return ParsedDescription(link, imageUrl, textDescription)
}

fun View.clickWithThrottle(throttleTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun Item.openDetail() {
    NavigationManager.getInstance().openFragment(M05DetailNewsFragment.newInstance(this))
}

@SuppressLint("SetTextI18n")
fun TextView.setTextCustomEllipsize(string: String?) {
    val dot = "..."
    fun getTextWidth(text: String): Int {
        val bounds = Rect()
        val paint = paint
        paint.getTextBounds(text, 0, text.length, bounds)
        return ceil(bounds.width().toDouble()).toInt()
    }
    if (text.endsWith(dot) && string?.contains(text.toString().substring(0, text.length)) == true)
        return
    ellipsize = null
    text = string
    post {
        if (lineCount > maxLines) {
            findViewTreeLifecycleOwner()?.lifecycleScope?.launch(Dispatchers.Main) {
                val layout = layout ?: return@launch
                val lineEndStartIndex = layout.getLineStart(maxLines - 1)
                val lineEndEndIndex = layout.getLineEnd(maxLines - 1)
                if (lineEndEndIndex < lineEndStartIndex)
                    return@launch
                val prevText = text.substring(0, lineEndStartIndex)
                val textLastLine = text.substring(lineEndStartIndex, lineEndEndIndex)
                val displayedWidth = width - paddingLeft - paddingRight

                var newText = textLastLine
                var textWidthDotWidth = getTextWidth(textLastLine + dot)
                if (textWidthDotWidth < displayedWidth) {
                    text = prevText + newText.trim() + dot
                    return@launch
                }
                if (CommonUtils.isNullOrEmpty(newText)) {
                    return@launch
                }
                while (textWidthDotWidth > displayedWidth && newText.isNotEmpty()) {
                    newText = newText.substring(0, newText.length - 1).trim()
                    textWidthDotWidth = getTextWidth(newText + dot)
                }
                text = prevText + newText.subLast().trim() + dot
            }
        }
    }
}

fun String.subLast(): String {
    val lastIndex = lastIndexOf(" ", length)
    return if (lastIndex == -1)
        this
    else
        substring(0, lastIndex)
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable?> Fragment.getMySerializable(key: String?, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getSerializable(key, clazz)
    } else
        arguments?.getSerializable(key) as? T?
}