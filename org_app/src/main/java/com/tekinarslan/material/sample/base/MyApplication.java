package com.tekinarslan.material.sample.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.asha.nightowllib.NightOwl;
import com.tekinarslan.material.sample.base.handler.OwlCustomTable;
import com.tekinarslan.material.sample.base.handler.ToolbarHandler;

/**
 * Created by cdj on 2015/11/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(".......................");
        NightOwl.builder().defaultMode(0).create();

        NightOwl.owlRegisterHandler(ToolbarHandler.class, OwlCustomTable.OwlToolbar.class);
    }
}
