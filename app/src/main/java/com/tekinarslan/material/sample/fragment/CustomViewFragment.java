package com.tekinarslan.material.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.PagerActivity;
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
        setListener(rootView);

//        initLrc(rootView);
        return rootView;
    }

//    private void initLrc(View rootView) {
////        lrc = (LinearLayout) rootView.findViewById(R.id.lyoutView);
//        lrcs = new SongLyric(singlrcpaht);
//        // 设置歌词播放总时间，假如该歌词要播放18W毫秒，即3分钟
//        lrcs.setMaxTime(300000);


//        vwLrc = new LyricView(rootView.getContext());
//        vwLrc.setBackgroundColor(Color.GREEN);
//        lrc.addView(vwLrc);
//
//        // 为歌词控件初始化，设置歌词
//        vwLrc.setLyric(lrcs);
//
//        System.out.println(lrcs.getArtist()+"==========1");
//        System.out.println(lrcs.getTitle()+"===========2");
//        new Thread() {
//            public void run() {
//                while (!end) {
//                    hand.sendMessage(hand.obtainMessage());
//                    try {
//                        sleep(100);
//                    } catch (Exception e) {
//                    }
//                }
//            };
//        }.start();
//    }

    private void setListener(View rootView) {
            rootView.findViewById(R.id.go_viewpager).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),PagerActivity.class);
                    startActivity(intent);
                }
            });
    }

//    private Handler hand = new Handler() {
//        public void handleMessage(Message msg) {
//            // System.out.println(time);
//            // 设置歌词当前播放时间
//            vwLrc.setTime(10000);
//            vwLrc.postInvalidate();
//            setTitle(SongLyric.longToString((int) time) + "/"
//                    + SongLyric.longToString((int) mp.getDuration()));
//        }
//    };

}