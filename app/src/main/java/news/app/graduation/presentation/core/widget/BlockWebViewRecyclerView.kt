package news.app.graduation.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView

class BlockWebViewRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    // Khi loadview load content, ViewGroup có thể scroll như ScrollView hay RecyclerView sẽ request focus và scrol đến vị trí đó
    // trong trường hợp này mình sẽ ignore forcus của webview đi nếu không muốn scrol đến vị trí khi webview load new content
    override fun requestChildFocus(child: View?, focused: View?) {
        if (focused is WebView)
            return
        super.requestChildFocus(child, focused)
    }
}