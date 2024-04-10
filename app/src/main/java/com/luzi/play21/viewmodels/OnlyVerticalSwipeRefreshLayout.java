package com.luzi.play21.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OnlyVerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    private int touchSlop;
    private float initialX;
    private boolean isScrolling;

    public OnlyVerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = ev.getX();
                isScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isScrolling) {
                    float deltaX = Math.abs(ev.getX() - initialX);
                    if (deltaX > touchSlop) {
                        isScrolling = true;
                        return false;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}

