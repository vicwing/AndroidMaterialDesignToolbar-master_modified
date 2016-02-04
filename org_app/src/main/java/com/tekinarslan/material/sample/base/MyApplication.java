package com.tekinarslan.material.sample.base;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by cdj on 2015/11/16.
 */
public class MyApplication extends Application {
    private String TAG = MyApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static MyApplication mInstance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        NightOwl.builder().defaultMode(0).create();
//        NightOwl.owlRegisterHandler(ToolbarHandler.class, OwlCustomTable.OwlToolbar.class);
        mRequestQueue =  Volley.newRequestQueue(this);

        XLog.init(LogLevel.ALL);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }


    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified then
     * it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, Object tag) {
        // set the default tag if tag is empty
        req.setTag(tag == null ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
