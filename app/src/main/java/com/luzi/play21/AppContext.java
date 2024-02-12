/**
 * File: MainActivity.java
 * Created at: February 1, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

public class AppContext extends MultiDexApplication {

    public static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Log.i("score808", "onCreate: ");
        Log.i("AppEnv", "****** AppEnvironment ******");
        Log.i("AppEnv", " APPLICATION_ID: com.score808.app");
        Log.i("AppEnv", " FLAVOR: production");
        Log.i("AppEnv", " BUILD_TYPE: release");
        Log.i("AppEnv", " isDebug: false");
        Log.i("AppEnv", " isProduction: true");
        Log.i("AppEnv", " VERSION_CODE: 8");
        Log.i("AppEnv", " VERSION_NAME: 1.0.8");
        Log.i("AppEnv", " DEV_VERSION: 10808");
        Log.i("AppEnv", " WEB_URL: https://www.score808.buzz/");
        Log.i("AppEnv", " KEY_ONLINE_PARAMS: PROD_ONLINE_PARAMS");
        Log.i("AppEnv", " DEF_DOH_DOMAINS: score808.com, score808.vip, score808.live, score808.co, livesportstv.cc");
        Log.i("AppEnv", " Device: " + AppUtil.getUserAgent());
        Log.i("AppEnv", "***************************");
    }
}

