/**
 * File: MainActivity.java
 * Created at: February 2, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.helper;

import android.os.Build;

import java.net.URL;
import java.util.Locale;
import java.util.TimeZone;

public final class AppUtil {
    public static String getUserAgent() {
        String androidVersion = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        String cpuAbi = Build.CPU_ABI;

        StringBuilder userAgentBuilder = new StringBuilder("Score808App/");
        userAgentBuilder.append(Locale.getDefault().getLanguage());
        userAgentBuilder.append("/");
        userAgentBuilder.append(TimeZone.getDefault().getID().replaceFirst("/", "_"));
        userAgentBuilder.append("/");
        userAgentBuilder.append(8);
        userAgentBuilder.append("_");
        userAgentBuilder.append("1.0.8");
        userAgentBuilder.append("/Android_");
        userAgentBuilder.append(sdkVersion);
        userAgentBuilder.append("_");
        userAgentBuilder.append(androidVersion);
        userAgentBuilder.append("/");
        userAgentBuilder.append(Build.BRAND);
        userAgentBuilder.append("_");
        userAgentBuilder.append(Build.MODEL);
        userAgentBuilder.append("/");
        userAgentBuilder.append(cpuAbi);

        return userAgentBuilder.toString();
    }

    public static String getMainHost(String url) {
        try {
            if (Strings.containsNullOrEmpty(url)) {
                return url;
            }

            String[] split = new URL(url).getHost().split("\\.");
            return split[split.length - 2] + "." + split[split.length - 1];
        } catch (Throwable th) {
            android.util.Log.e("getMainHost", "Error occurred: " + th.toString(), th);
            return null;
        }
    }

    public static boolean hasSameMainHost(String url1, String url2) {
        String mainHost1 = getMainHost(url1);
        String mainHost2 = getMainHost(url2);

        if (Strings.containsNullOrEmpty(mainHost1, mainHost2)) {
            return false;
        }

        return mainHost1.equals(mainHost2);
    }
}

