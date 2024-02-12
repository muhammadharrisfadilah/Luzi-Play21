/**
 * File: MainActivity.java
 * Created at: February 3, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.utils;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class BaseActivity extends AppCompatActivity {
    private static Stack<BaseActivity> activityStack = new Stack<>();
    private ExitConfirmationListener exitConfirmationListener;

    public void setExitConfirmationListener(ExitConfirmationListener listener) {
        this.exitConfirmationListener = listener;
    }

    public interface ExitConfirmationListener {
        void onExitRequested();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityStack.push(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityStack.remove(this);
    }

    @Override
    public void onBackPressed() {
        if (activityStack.size() > 1) {
            exitConfirmationListener.onExitRequested();
        } else {
            super.onBackPressed();
        }
    }

    public static void finishAll() {
        for (BaseActivity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }
}
