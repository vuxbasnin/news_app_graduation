package news.app.graduation.presentation.core.widget.webview

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import java.util.Locale

open class CustomWebviewClient(
    var context: Context? = null,
    var isLoading: Boolean = true,
) : WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        isLoading = false
    }

    val checkTypeImage = { url: String ->
        val imageExtensions = arrayOf(".jpg", ".jpeg", ".png", ".gif")
        imageExtensions.any { extension ->
            url.lowercase(Locale.ROOT).endsWith(extension)
        }
    }
}
