package com.luzi.play21.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.luzi.play21.helper.CustomWebViewClient;
import com.luzi.play21.helper.HomeUrl;
import com.luzi.play21.utils.BaseActivity;
import com.luzi.play21.R;

public class MainActivity extends BaseActivity {
    private String initialUrl;
    HomeUrl url = new HomeUrl();
    private WebView hyWebView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Boolean canRefresh = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hyWebView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        // Kosongkan cache aplikasi
        clearAppCache();

        initializeWebView();

        performInitialLoading();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initializeWebView() {
        // Atur WebViewClient dan JavaScript yang diaktifkan
        hyWebView.setWebViewClient(new CustomWebViewClient(progressBar, swipeRefreshLayout));
        WebSettings webSettings = hyWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Atur listener untuk SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> hyWebView.reload());
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.hy_dark_blue));

        // Atur listener untuk mendeteksi perubahan scroll
        hyWebView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            canRefresh = scrollY == 0;
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        swipeRefreshLayout.setEnabled(canRefresh);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    private void performInitialLoading() {
//        initialUrl = "https://play23.tigoals32.com/";
        hyWebView.loadUrl(url.getUrl());
        hyWebView.clearHistory();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (hyWebView.canGoBack()) {
            hyWebView.goBack();
        } else {
            showExitConfirmation();
        }
    }

    private void destroyWebView() {
        if (hyWebView != null) {
            hyWebView.clearCache(true);
            hyWebView.clearHistory();
            hyWebView.removeAllViews();
            ViewGroup parentViewGroup = (ViewGroup) hyWebView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeView(hyWebView);
            }
            hyWebView.destroy();
            hyWebView = null;
        }
    }

    private void showExitConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> finish());
        builder.setNegativeButton("No", null);
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void clearAppCache() {
        if (hyWebView != null) {
            hyWebView.clearCache(true);
            hyWebView.clearFormData();
            hyWebView.clearHistory();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        hyWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hyWebView.restoreState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hyWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hyWebView.onPause();
    }
}
