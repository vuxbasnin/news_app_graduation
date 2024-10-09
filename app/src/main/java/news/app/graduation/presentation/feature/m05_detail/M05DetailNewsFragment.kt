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
import dagger.hilt.android.AndroidEntryPoint
import news.app.graduation.databinding.M05DetailFragmentBinding
import news.app.graduation.presentation.core.base.BaseFragment
import timber.log.Timber

@AndroidEntryPoint
class M05DetailNewsFragment :
    BaseFragment<M05DetailFragmentBinding>(M05DetailFragmentBinding::inflate) {
    private var url: String? = null

    companion object {
        const val URL = "URL"

        fun newInstance(url: String): M05DetailNewsFragment {
            val args = Bundle()
            args.putString(URL, url)
            val fragment = M05DetailNewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        url = arguments?.getString(URL)
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
                // Load URLs within the WebView
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
        Timber.d("NINVB => url $url")
        bindingOrNull?.detailWebview?.loadUrl(url ?: "")
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}