/**
 * File: MainActivity.java
 * Created at: February 6, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.viewmodels;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;

import com.luzi.play21.utils.AppManager;

import java.util.Objects;

public final class FullScreenHandler extends WebChromeClient {

    private final FullScreenContainer fullScreenContainer;

    public FullScreenHandler(FullScreenContainer fullScreenContainer) {
        this.fullScreenContainer = fullScreenContainer;
    }

    @Override
    public final void onHideCustomView() {
        FullScreenContainer fullScreenContainer = this.fullScreenContainer;
        View view = fullScreenContainer.fullScreenView;
        Objects.requireNonNull(fullScreenContainer);
        AppManager.getTopActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view.setSystemUiVisibility(fullScreenContainer.systemUiVisibility);
        Objects.requireNonNull(this.fullScreenContainer);
        AppManager.getTopActivity().getWindowManager().removeViewImmediate(this.fullScreenContainer.fullScreenView);
        this.fullScreenContainer.fullScreenView = null;
    }

    @Override
    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.fullScreenContainer.systemUiVisibility = view.getSystemUiVisibility();
        Objects.requireNonNull(this.fullScreenContainer);
        AppManager.getTopActivity().getWindowManager().addView(view, new WindowManager.LayoutParams(2));
        Objects.requireNonNull(this.fullScreenContainer);
        view.setSystemUiVisibility(5894);
        AppManager.getTopActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.fullScreenContainer.fullScreenView = view;
    }
}

