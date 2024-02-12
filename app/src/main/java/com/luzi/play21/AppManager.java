/**
 * File: MainActivity.java
 * Created at: February 2, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class AppManager {
    public static final List<InterfaceListener> interfaceListeners = new ArrayList<>();
    public static final Stack<Activity> activityStack = new Stack<>();
    public interface InterfaceListener {
        void onActivityPaused();
        void onActivityResumed();
    }
    public static Activity getTopActivity() {
        Stack<Activity> stack = activityStack;

        if (activityStack.isEmpty()) {
            return null;
        }
        return stack.lastElement();
    }

    public static void finishAllActivities() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finishAndRemoveTask();
            }
        }
        activityStack.clear();
    }
}
