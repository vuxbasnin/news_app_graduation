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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import news.app.graduation.core.common.getMySerializable
import news.app.graduation.core.common.hide
import news.app.graduation.core.common.show
import news.app.graduation.core.utils.PreferenceHelper
import news.app.graduation.core.utils.PreferenceHelper.SAVE_READ_POST
import news.app.graduation.core.utils.Utility
import news.app.graduation.data.local.dao.NewsUrlDao
import news.app.graduation.data.local.database.AppLocalDatabase
import news.app.graduation.data.local.entity.NewsLocal
import news.app.graduation.data.model.response.rss.Item
import news.app.graduation.databinding.M05DetailFragmentBinding
import news.app.graduation.presentation.NavigationManager
import news.app.graduation.presentation.core.base.BaseFragment
import news.app.graduation.presentation.my_interface.OnClickBottomDetailNative
import timber.log.Timber

@AndroidEntryPoint
class M05DetailNewsFragment :
    BaseFragment<M05DetailFragmentBinding>(M05DetailFragmentBinding::inflate),
    OnClickBottomDetailNative {
    private var data: Item? = null
    private var newsUrlDao: NewsUrlDao? = null

    companion object {
        const val DATA = "DATA"

        fun newInstance(dataDetail: Item): M05DetailNewsFragment {
            val args = Bundle()
            args.putSerializable(DATA, dataDetail)
            val fragment = M05DetailNewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        data = getMySerializable(DATA, Item::class.java)
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
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                return false
            }
        }

        // Set WebChromeClient to handle JavaScript dialogs, titles, etc.
        bindingOrNull?.detailWebview?.webChromeClient = WebChromeClient()

        // Enable caching
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        bindingOrNull?.detailWebview?.settings?.domStorageEnabled =
            true  // Enable DOM storage for modern web apps

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
        newsUrlDao = AppLocalDatabase.getDatabase(requireContext()).newsUrlDao()
        binding.ctlBottomDetail.eventListener = this
        bindingOrNull?.detailWebview?.loadUrl(data?.link ?: "")
        if (data?.title.isNullOrEmpty()) {
            bindingOrNull?.ctlTopDetail.hide()
        } else {
            bindingOrNull?.ctlTopDetail.show()
            bindingOrNull?.txtZoneSapo?.text = data?.title
        }
        bindingOrNull?.btnDetailHome?.setOnClickListener {
            NavigationManager.getInstance().popToHome()
        }
        addNewsUrlToDatabase()
        lifecycleScope.launch {
            val isSaved = checkSaveNews() == true
            Timber.d("NINVB => check is saved bind $isSaved")
            bindingOrNull?.ctlBottomDetail?.setSaveNews(checkSaveNews() ?: false)
        }
        markReadLocal()
        saveNewsLocal()
    }

    private fun markReadLocal() {
        var readList = PreferenceHelper.get(SAVE_READ_POST, "")
        if (!readList.contains(data?.newsId.toString())) {
            readList = "$readList${data?.newsId};"
        }
        val arr = readList.split(";")
        if (arr.size > 100) {
            readList = readList.substring(readList.indexOf(";") + 1)
        }
        PreferenceHelper.setValue(SAVE_READ_POST, readList)
    }

    private fun saveNewsLocal() {
        bindingOrNull?.ctlBottomDetail?.binding?.imgDetailSaveNews?.setOnClickListener {
            lifecycleScope.launch {
                val isSaved = checkSaveNews() == true
                val newsLocal = createNewsLocal()

                Timber.d("NINVB => check is saved $isSaved")

                newsUrlDao?.let { dao ->
                    if (isSaved) {
                        val a = dao.updateNewsUrl(false)
                        updateSaveState(false)
                        Timber.d("NINVB => deleteNewsUrl local: a $a")
                    } else {
                        val b = dao.insertNewsUrl(newsLocal)
                        updateSaveState(true)
                        Timber.d("NINVB => insertNewsUrl local: b $b")
                    }
                }
            }
        }
    }

    private fun createNewsLocal(): NewsLocal {
        return NewsLocal(
            newsUrl = data?.link.orEmpty(),
            title = data?.title.orEmpty(),
            description = data?.descriptionParse?.textDescription.orEmpty(),
            imageUrl = data?.descriptionParse?.imageUrl.orEmpty(),
            isSave = true
        )
    }

    private fun updateSaveState(isSaved: Boolean) {
        bindingOrNull?.ctlBottomDetail?.setSaveNews(isSaved)
    }

    private fun addNewsUrlToDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val isSaved = checkSaveNews() == true
            if (isSaved) return@launch
            data?.run {
                newsUrlDao?.insertNewsUrl(
                    NewsLocal(
                        newsUrl = link,
                        title = title,
                        description = descriptionParse.textDescription,
                        imageUrl = descriptionParse.imageUrl
                    )
                )
            }
        }
    }

    private suspend fun checkSaveNews(): Boolean? = withContext(Dispatchers.IO) {
        val listUrlLocalSave = async {
            newsUrlDao?.getAllNewsSave()
        }.await()?.map { it.newsUrl }
        listUrlLocalSave?.contains(data?.link)
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
        Utility.shareInApp(requireContext(), data?.link)
    }
}