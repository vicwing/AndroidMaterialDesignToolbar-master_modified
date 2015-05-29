package com.tekinarslan.material.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.common.utils.LogUtils;

/**
 * Created by cdj on 2015/5/28.
 */
public class BaseFragment  extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    protected Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        LogUtils.d(TAG,"basefragment....................");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
