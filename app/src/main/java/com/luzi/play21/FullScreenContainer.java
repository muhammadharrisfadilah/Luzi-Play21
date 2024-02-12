/**
 * File: MainActivity.java
 * Created at: February 6, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21;

import android.view.View;
import android.webkit.WebView;

public final class FullScreenContainer {

    public View fullScreenView;
    public int systemUiVisibility;

    public FullScreenContainer(WebView webView) {
        webView.setWebChromeClient(new FullScreenHandler(this));
    }
}