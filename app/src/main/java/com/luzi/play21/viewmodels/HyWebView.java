/**
 * File: MainActivity.java
 * Created at: February 1, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.viewmodels;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.luzi.play21.helper.AppUtil;

public class HyWebView extends WebView {

    public HyWebView(Context context) {
        super(context);
        init();
    }

    public HyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        try {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        } catch (Throwable th) {
            th.printStackTrace();
        }

        WebSettings settings = getSettings();
        String userAgent = settings.getUserAgentString().replaceFirst("wv", "");
        settings.setUserAgentString(AppUtil.getUserAgent() + "/" + userAgent);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        settings.setAppCacheEnabled(true);
//        settings.setAppCachePath(AppContext.appContext.getDir("production_wv_cache", 0).getPath());
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
//        setBackgroundColor(0);
//        getBackground().setAlpha(0);

        // Configure WebViewClient and WebChromeClient
//        setWebViewClient(new BaseWebViewClient());
//        customWebChromeClient = new CustomWebChromeClient();
//        setWebChromeClient(customWebChromeClient);

        // Configure CookieManager
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(this, true);
    }

//    public CustomWebChromeClient getCustomWebChromeClient() {
//        return customWebChromeClient;
//    }
}
