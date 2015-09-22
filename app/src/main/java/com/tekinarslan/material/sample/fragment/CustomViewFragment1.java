package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.BaseFragment;

public class CustomViewFragment1 extends BaseFragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static CustomViewFragment1 newInstance(int position) {
        CustomViewFragment1 f = new CustomViewFragment1();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        LogUtils.d("CustomViewFragment....................");
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_custom1, container, false);
        init(rootView);
        return rootView;
    }
    private void init(View rootView) {

    }

}