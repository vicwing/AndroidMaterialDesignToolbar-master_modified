package com.tekinarslan.material.sample.fragment_oschina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.fragment.CustomViewFragment;
import com.tekinarslan.material.sample.fragment.SampleFragment;
import com.tekinarslan.material.sample.fragment_oschina.adapter.ViewPageFragmentAdapter;


/**
 * 博客区ViewPager
 * 
 * @author kymjs(kymjs123@gmail.com)
 */
public class BlogViewPagerFragment extends BaseViewPagerFragment {
    private static final String ARG_POSITION = "position";
    @Override
    public void initView(View view) {}

    @Override
    public void initData() {}

    @Override
    public void onClick(View v) {}

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.quests_viewpage_arrays);
        // 最新博客
        adapter.addTab(title[0], "latest_blog", SampleFragment.class);
        // 推荐博客
        adapter.addTab(title[1], "recommend_blog", SampleFragment.class);
    }
    public static CustomViewFragment newInstance(int position) {
        CustomViewFragment f = new CustomViewFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sample);
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customui, container, false);
        return rootView;
    }
}
