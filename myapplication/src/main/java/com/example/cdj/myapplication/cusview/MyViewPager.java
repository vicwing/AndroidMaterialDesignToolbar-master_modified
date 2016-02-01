package com.example.cdj.myapplication.cusview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cdj on 2016/2/1.
 */
public class MyViewPager extends ViewPager {
    private float mLastMotionX;
    private float mLastMotionY;
    private float y = 20000;
    public MyViewPager(Context context) {
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // 1.禁掉viewpager左右滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
    //2.禁掉viewpager左右滑动事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}