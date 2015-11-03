package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.fragment.BaseFragment;
import com.tekinarslan.material.sample.customui.XCRoundImageViewByXfermode;

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
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_custom1, container, false);
        init(rootView);
        return rootView;
    }

    private XCRoundImageViewByXfermode circleImageView;//圆形图片
    private XCRoundImageViewByXfermode roundRectImageView;//圆角矩形图片
    private XCRoundImageViewByXfermode ovalImageView;//椭圆图片
    private void init(View rootView) {
        circleImageView = (XCRoundImageViewByXfermode)rootView.findViewById(R.id.cicleImageView);
        roundRectImageView = (XCRoundImageViewByXfermode)rootView.findViewById(R.id.roundRectImageView);
        ovalImageView = (XCRoundImageViewByXfermode)rootView.findViewById(R.id.ovalImageView);

        roundRectImageView.setType(XCRoundImageViewByXfermode.TYPE_ROUND);
        roundRectImageView.setRoundBorderRadius(100);

        ovalImageView.setType(XCRoundImageViewByXfermode.TYPE_OVAL);
        ovalImageView.setRoundBorderRadius(50);


    }

}