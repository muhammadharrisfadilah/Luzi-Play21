/**
 * File: MainActivity.java
 * Created at: February 6, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.viewmodels;

import android.view.View;
import android.webkit.WebView;

import com.luzi.play21.viewmodels.FullScreenHandler;

public final class FullScreenContainer {

    public View fullScreenView;
    public int systemUiVisibility;

    public FullScreenContainer(WebView webView) {
        webView.setWebChromeClient(new FullScreenHandler(this));
    }
}