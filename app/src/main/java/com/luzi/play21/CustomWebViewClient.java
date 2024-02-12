/**
 * File: MainActivity.java
 * Created at: February 1, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CustomWebViewClient extends WebViewClient {
    private final WebView webView;
    private final ProgressBar progressBar;
    private final SwipeRefreshLayout swipeRefreshLayout;

    public CustomWebViewClient(WebView webView, ProgressBar progressBar, SwipeRefreshLayout swipeRefreshLayout) {
        this.webView = webView;
        this.progressBar = progressBar;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }
}

