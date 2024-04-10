package com.luzi.play21.helper;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.OkHttpClient;

public class CustomWebViewClient extends WebViewClient {
    private final ProgressBar progressBar;
    private final SwipeRefreshLayout swipeRefreshLayout;
    private final OkHttpClient okHttpClient;

    public CustomWebViewClient(ProgressBar progressBar, SwipeRefreshLayout swipeRefreshLayout) {
        this.progressBar = progressBar;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.okHttpClient = new OkHttpClient();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        super.shouldOverrideUrlLoading(view,request);
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
