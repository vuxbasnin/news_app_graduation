package news.app.graduation.presentation.feature.m05_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.openDetail
import news.app.graduation.core.common.show
import news.app.graduation.core.utils.PreferenceHelper
import news.app.graduation.core.utils.PreferenceHelper.SAVE_READ_POST
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.model.response.rss.Image
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M05DetailFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.my_interface.OnClickBottomDetailNative
import timber.log.Timber

@AndroidEntryPoint
class M05DetailNewsFragment :
    BaseFragment<M05DetailFragmentBinding>(M05DetailFragmentBinding::inflate), OnClickBottomDetailNative {
    private var url: String? = null
    private var title: String? = null
    private var newsId: String? = null

    companion object {
        const val URL = "URL"
        const val TITLE = "TITLE"
        const val NEWS_ID = "NEWS_ID"

        fun newInstance(dataDetail: Item): M05DetailNewsFragment {
            val args = Bundle()
            args.putString(URL, dataDetail.link)
            args.putString(TITLE, dataDetail.title)
            args.putString(NEWS_ID, dataDetail.newsId)
            val fragment = M05DetailNewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        url = arguments?.getString(URL)
        title = arguments?.getString(TITLE)
        newsId = arguments?.getString(NEWS_ID)
    }

    override fun initView() {
        settingWebView()
        bindView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun settingWebView() {
        val webSettings: WebSettings? = bindingOrNull?.detailWebview?.settings
        webSettings?.javaScriptEnabled = true

        // Enable Zoom controls
        webSettings?.setSupportZoom(true)
        webSettings?.builtInZoomControls = true
        webSettings?.displayZoomControls = false  // Disable display of zoom buttons

        // Set WebViewClient to handle loading within WebView
        bindingOrNull?.detailWebview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        // Set WebChromeClient to handle JavaScript dialogs, titles, etc.
        bindingOrNull?.detailWebview?.webChromeClient = WebChromeClient()

        // Enable caching
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        bindingOrNull?.detailWebview?.settings?.domStorageEnabled = true  // Enable DOM storage for modern web apps

        // Enable cookies
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)

        // Enable file access within WebView
        webSettings?.allowFileAccess = true
        webSettings?.allowContentAccess = true

        // Handle mixed content (HTTP/HTTPS) if required
        webSettings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    private fun bindView() {
        binding.ctlBottomDetail.eventListener = this
        bindingOrNull?.detailWebview?.loadUrl(url ?: "")
        if (title.isNullOrEmpty()) {
            bindingOrNull?.ctlTopDetail.hide()
        } else {
            bindingOrNull?.ctlTopDetail.show()
            bindingOrNull?.txtZoneSapo?.text = title
        }
        markReadLocal()
    }

    private fun markReadLocal() {
        var readList = PreferenceHelper.get(SAVE_READ_POST, "")
        if (!readList.contains(newsId.toString())) {
            readList = "$readList$newsId;"
        }
        val arr = readList.split(";")
        if (arr.size > 100) {
            readList = readList.substring(readList.indexOf(";") + 1)
        }
        PreferenceHelper.setValue(SAVE_READ_POST, readList)
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingOrNull?.detailWebview?.destroy()
    }

    override fun onClickClose() {
        if (bindingOrNull?.detailWebview?.canGoBack() == true) {
            bindingOrNull?.detailWebview?.goBack()
        } else {
            NavigationManager.getInstance().popBackStack()
        }
    }

    override fun onCLickSaveNews() {

    }

    override fun onClickShare() {
        Utility.shareInApp(requireContext(), url)
    }
}