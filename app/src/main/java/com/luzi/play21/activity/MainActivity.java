/**
 * File: MainActivity.java
 * Created at: February 1, 2024
 * Author: muhammad harris fadilah
 * Description: MainActivity adalah kelas utama dalam aplikasi ini
   yang bertanggung jawab untuk menampilkan antarmuka pengguna utama.
   Aktivitas ini menampilkan tampilan awal aplikasi yang berisi komponen seperti WebView untuk menampilkan konten web,
   ProgressBar untuk menampilkan kemajuan proses, dan SwipeRefreshLayout untuk memungkinkan pengguna untuk menyegarkan konten web
   dengan menggesek layar. MainActivity juga mengelola timer untuk memperbarui konten web secara berkala. Selain itu,
   aktivitas ini menangani peristiwa tombol kembali, memastikan bahwa pengguna dapat kembali ke halaman sebelumnya
   dalam WebView atau keluar dari aplikasi jika tidak ada riwayat navigasi. MainActivity
   adalah titik masuk utama aplikasi dan memainkan peran kunci dalam memberikan pengalaman pengguna yang mulus dan responsif..
 */


package com.luzi.play21.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.luzi.play21.utils.BaseActivity;
import com.luzi.play21.helper.CustomWebViewClient;
import com.luzi.play21.helper.HomeUrl;
import com.luzi.play21.viewmodels.HyWebView;
import com.luzi.play21.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements BaseActivity.ExitConfirmationListener {
    String initialUrl;
    HyWebView hyWebView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hyWebView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        hyWebView.setWebViewClient(new CustomWebViewClient(hyWebView, progressBar, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(() -> hyWebView.reload());
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.hy_dark_blue));

        performInitialLoading();
        scheduleTimerTask();
    }

    @Override
    protected void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        pauseTimerTask();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeTimerTask();
    }

    private void performInitialLoading() {
        initialUrl = HomeUrl.getUrl();
        hyWebView.loadUrl(initialUrl);
        hyWebView.clearHistory();
    }

    private void scheduleTimerTask() {
        timer = new Timer();
        long delayMillis = 0L;
        long periodMillis = 30000L;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (initialUrl == null) {
                    initialUrl = HomeUrl.getUrl();
                }
            }
        }, delayMillis, periodMillis);
    }

    private void pauseTimerTask() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void resumeTimerTask() {
        pauseTimerTask();
        scheduleTimerTask();
    }

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
            hyWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            hyWebView.clearHistory();
            ViewGroup parentViewGroup = (ViewGroup) hyWebView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeView(hyWebView);
            }
            hyWebView.destroy();
            hyWebView = null;
        }
    }

    @Override
    public void onExitRequested() {

    }

    private void showExitConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseActivity.finishAll();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
