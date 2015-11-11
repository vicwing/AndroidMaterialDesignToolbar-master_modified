package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.adapterhelper.BaseAdapterHelper;
import com.tekinarslan.material.sample.base.adapterhelper.QuickAdapter;
import com.tekinarslan.material.sample.bean.Bean;
import com.tekinarslan.material.sample.customui.SwipeRefreshLayoutLoad2;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vic on 2015/5/12.
 */
public class SwipRefreshLoadFragment2 extends Fragment {
    private static final String ARG_POSITION = "position";
    private ListView mListView;
    private List<Bean> mDatas = new ArrayList<Bean>();

    private QuickAdapter<Bean> mAdapter;

    private int position;

    private final static String TAG = "MainActivity";

    public static SwipRefreshLoadFragment2 newInstance(int position) {
        SwipRefreshLoadFragment2 f = new SwipRefreshLoadFragment2();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_swiperefresh_load_layout2, container, false);
        setRefreshList(rootView);
        initDatas();
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.listview_lv);
        LogUtils.d("mDatas=="+mDatas.size());
        LogUtils.e("mDatas=="+mDatas.size());
        mAdapter = new QuickAdapter<Bean>(getActivity(), R.layout.item_list, mDatas) {

            @Override
            protected void convert(BaseAdapterHelper helper, Bean item) {
                LogUtils.d(" item.getTitle()  "+ item.getTitle());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_describe, item.getDesc());
                helper.setText(R.id.tv_phone, item.getPhone());
                helper.setText(R.id.tv_time, item.getTime());
                // // helper.getView(R.id.tv_title).setOnClickListener(l)
            }
        };
//		mAdapter.showIndeterminateProgress(true);
        // 设置适配器
        mListView.setAdapter(mAdapter);
    }

    private BaseAdapter adapter;


    private void initDatas()
    {
        Bean bean = null;
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        mDatas.add(bean);
    }


    private void setRefreshList(View layoutView) {

        // 模拟一些数据
//        final List<String> datas = new ArrayList<String>();
//        for (int i = 0; i < 20; i++) {
//            datas.add("item - " + i);
//        }

        // 构造适配器
//        final BaseAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);
//        adapter = new QuickAdapter<String>(getActivity(), R.layout.listview_item, datas) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, String item) {
//                helper.setText(R.id.id_item_tv, item);
//            }
//        };


        // 获取listview实例
//        ListView listView = (ListView) layoutView.findViewById(R.id.listview_lv);
//        listView.setAdapter(adapter);

        // 获取RefreshLayout实例
        final SwipeRefreshLayoutLoad2 myRefreshListView = (SwipeRefreshLayoutLoad2)
                layoutView.findViewById(R.id.swipe_layout);
        myRefreshListView.setEnabled(false);
        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        myRefreshListView.setColorScheme(R.color.blue,
                R.color.green, R.color.red,
                R.color.yellow);
        // 设置下拉刷新监听器
        myRefreshListView.setOnRefreshListener(new SwipeRefreshLayoutLoad2.OnRefreshListener() {

            @Override
            public void onRefresh() {

                Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 更新数据
//                        datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // 更新完后调用该方法结束刷新
                        myRefreshListView.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        // 加载监听器
        myRefreshListView.setOnLoadListener(new SwipeRefreshLayoutLoad2.OnLoadListener() {

            @Override
            public void onLoad() {

//                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();
//
//                myRefreshListView.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        datas.add(new Date().toGMTString());
//                        adapter.notifyDataSetChanged();
//                        // 加载完后调用该方法
//                        myRefreshListView.setLoading(false);
//                    }
//                }, 1500);

            }
        });
    }

}



