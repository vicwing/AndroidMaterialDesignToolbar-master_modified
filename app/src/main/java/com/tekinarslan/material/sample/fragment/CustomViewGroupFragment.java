package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.fragment.BaseFragment;

public class CustomViewGroupFragment extends BaseFragment {

    private static final String ARG_POSITION = "position";
    public static final String TAG = CustomViewGroupFragment.class.getSimpleName();
    private int position;

    public static CustomViewGroupFragment newInstance(int position) {
        CustomViewGroupFragment f = new CustomViewGroupFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.viewgroup_container, container, false);
//        switchColor(progressBarCircular, fab);
        return rootView;
    }
}