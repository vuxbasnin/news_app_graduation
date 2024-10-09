package news.app.graduation.presentation.core.widget.webview;

import static news.app.graduation.core.common.Constants.Preference.USER_AGENT_WEBVIEW;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import java.util.HashMap;
import java.util.Map;


public class CustomWebview extends ObservableWebView {
    private boolean flagClearHistory;
    private final Context context;
    private Map<String, String> defaultHttpHeaders;


    public CustomWebview(Context context) {
        super(context);
        this.context = context;
        if (!isInEditMode())
            initSetting();
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            initSetting();
    }

    public CustomWebview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        if (!isInEditMode())
            initSetting();
    }

    @SuppressLint("SetJavaScriptEnabled")
    void initSetting() {
        defaultHttpHeaders = new HashMap<>();
        defaultHttpHeaders.put("NativeApp", "1");

        CookieManager.getInstance().setAcceptCookie(true);
        setInitialScale(1);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setSupportZoom(true);
        getSettings().setUserAgentString(USER_AGENT_WEBVIEW);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setDatabaseEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setAllowFileAccessFromFileURLs(true);
        getSettings().setAllowUniversalAccessFromFileURLs(true);
        getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);


        setWebChromeClient(new WebChromeClient() {

        });
    }

    @Override
    public void loadUrl(String url) {
        if (defaultHttpHeaders != null)
            super.loadUrl(url, defaultHttpHeaders);
        else
            super.loadUrl(url);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        additionalHttpHeaders.putAll(defaultHttpHeaders);
        super.loadUrl(url, additionalHttpHeaders);
    }

    public boolean isFlagClearHistory() {
        return flagClearHistory;
    }

    public void setFlagClearHistory(boolean flagClearHistory) {
        this.flagClearHistory = flagClearHistory;
    }

    public interface LoadingListener {
        void getPercentLoaded(int percent);

        void onWebLoadError(String description);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void setOverScrollMode(int mode) {
        try {
            super.setOverScrollMode(mode);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("Failed to load WebView provider: No WebView installed")) {
                e.printStackTrace();
            } else {
                throw e;
            }
        }
    }
}