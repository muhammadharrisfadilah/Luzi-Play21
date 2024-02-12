/**
 * File: MainActivity.java
 * Created at: February 4, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21;

import java.util.Timer;

public final class TimerWrapper {

    private Timer timer;
    public TimerWrapper() {
        this.timer = new Timer();
    }
    public Timer getTimer() {
        return timer;
    }
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
