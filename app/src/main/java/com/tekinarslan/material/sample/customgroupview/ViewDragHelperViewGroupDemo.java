package com.tekinarslan.material.sample.customgroupview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by cdj on 2015/10/20.
 */
public class ViewDragHelperViewGroupDemo extends LinearLayout {
    private  ViewDragHelper mDragHelper;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    public ViewDragHelperViewGroupDemo(Context context) {
        super(context);
    }

    public ViewDragHelperViewGroupDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public ViewDragHelperViewGroupDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mDragHelper.cancel();
//            return false;
//        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        LogUtils.d("ontouch");
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEdgeTrackerView = getChildAt(2);
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            LogUtils.d("tryCaptureView..."+"childView="+child+"  pointerId"+pointerId);
            return child == mDragView || child == mAutoBackView;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Toast.makeText(getContext(), "edgeTouched", Toast.LENGTH_SHORT).show();
            LogUtils.d("onEdgeTouched......" + " edgeFlags=" + edgeFlags + "  pointerId=" + pointerId);
            mDragHelper.captureChildView(mEdgeTrackerView, pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx)
        {
            return left;
//            final int leftBound = getPaddingLeft();
//            final int rightBound = getWidth() - mDragView.getWidth() - leftBound;
//            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
//            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            LogUtils.d("childView="+child+"  top="+top+"  dy="+dy);
            return top;
        }
    }
}
