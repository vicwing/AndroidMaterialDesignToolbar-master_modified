package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.BaseFragment;
import com.tekinarslan.material.sample.common.utils.LogUtils;

import java.io.File;

public class CustomViewFragment extends BaseFragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static CustomViewFragment newInstance(int position) {
        CustomViewFragment f = new CustomViewFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
//private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        LogUtils.d(TAG,"CustomViewFragment....................");
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_customui, container, false);
//       context =  getActivity();
        View customUi = rootView.findViewById(R.id.custom_ui);

        File externalCacheDir = context.getExternalCacheDir();
        LogUtils.d(TAG, "externalCacheDir==" + externalCacheDir);

        File fileDir = context.getFilesDir();
        LogUtils.d(TAG, "filesDir==" + fileDir);

        File ExternalFiles = context.getExternalFilesDir("123");
//        File[] files = context.getExternalFilesDirs("vvvv");
//        File[]  files = context.getExternalFilesDirs("vvvv");
//        for (int i = 0; i < files.length; i++) {
//            LogUtils.d(TAG, "files==" + files[i].toString());
//        }
        LogUtils.d(TAG, "externalCacheDir==" + ExternalFiles);
//        new String();
        return rootView;
    }

}