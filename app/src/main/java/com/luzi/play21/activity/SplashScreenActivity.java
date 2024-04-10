package com.luzi.play21.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.luzi.play21.R;
import com.luzi.play21.activity.MainActivity;
import com.luzi.play21.helper.HomeUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class SplashScreenActivity extends AppCompatActivity {

    private final int waktuDelay = 2000;
    private HomeUrl url = new HomeUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchUrlFromWebsite();
            }
        }, waktuDelay);
    }

    private void fetchUrlFromWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://sites.google.com/view/score808link-1").get();
                    Log.d("geturl", "HTML: " + document.html()); // Tambahkan log untuk melihat HTML halaman
                    Element linkElement = document.selectFirst("div.link-page-gsites > div.content > div.dig-link > div.link-item > a");
                    if (linkElement != null) {
                        String urlTarget = linkElement.attr("href");
                        url.setUrl(urlTarget);
                        Log.d("geturl", "URL: " + urlTarget);
                    } else {
                        Log.d("geturl", "linkurl tidak ada");
                    }
                } catch (IOException e) {
                    Log.e("geturl", "IOException: " + e.getMessage());
                    e.printStackTrace();
                }


                // Setelah mendapatkan URL, lanjutkan ke aktivitas berikutnya
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}
