package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.adapterhelper.BaseAdapterHelper;
import com.tekinarslan.material.sample.base.adapterhelper.MultiItemTypeSupport;
import com.tekinarslan.material.sample.base.adapterhelper.QuickAdapter;
import com.tekinarslan.material.sample.bean.ChatMessage;
import com.tekinarslan.material.sample.customui.SwipeRefreshLayoutLoad2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by vic on 2015/5/12.
 */
public class SwipRefreshLoadFragment3 extends Fragment {
    private static final String ARG_POSITION = "position";

    private ListView mListView;
    private ArrayList<ChatMessage> mDatas = new ArrayList<ChatMessage>();

    private QuickAdapter<ChatMessage> mAdapter;

    private int position;

    private final static String TAG = "MainActivity";

    public static SwipRefreshLoadFragment3 newInstance(int position) {
        SwipRefreshLoadFragment3 f = new SwipRefreshLoadFragment3();
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
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.listview_lv);


        MultiItemTypeSupport<ChatMessage> multiItemTypeSupport = new MultiItemTypeSupport<ChatMessage>() {
            @Override
            public int getLayoutId(int position, ChatMessage msg) {
                if (msg.isComMeg()) {
                    return R.layout.main_chat_from_msg;
                }
                return R.layout.main_chat_send_msg;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, ChatMessage msg) {
                if (msg.isComMeg()) {
                    return ChatMessage.RECIEVE_MSG;
                }
                return ChatMessage.SEND_MSG;
            }
        };


        initDatas();


        mAdapter = new QuickAdapter<ChatMessage>(getActivity(), mDatas,
                multiItemTypeSupport) {
            @Override
            protected void convert(BaseAdapterHelper helper, ChatMessage item) {
                switch (helper.layoutId) {
                    case R.layout.main_chat_from_msg:
                        helper.setText(R.id.chat_from_content, item.getContent());
                        helper.setText(R.id.chat_from_name, item.getName());
                        helper.setImageResource(R.id.chat_from_icon, item.getIcon());
                        break;
                    case R.layout.main_chat_send_msg:
                        helper.setText(R.id.chat_send_content, item.getContent());
                        helper.setText(R.id.chat_send_name, item.getName());
                        helper.setImageResource(R.id.chat_send_icon, item.getIcon());
                        break;
                }

            }
        };
//		mAdapter.showIndeterminateProgress(true);
        // 设置适配器
        mListView.setAdapter(mAdapter);

    }

    private BaseAdapter adapter;


    private void initDatas() {
        ChatMessage msg = null;
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ChatMessage(R.drawable.xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
    }

    private void setRefreshList(View layoutView) {

        // 模拟一些数据
        final List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("item - " + i);
        }

        // 构造适配器
//        final BaseAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);


        // 获取listview实例
//        ListView listView = (ListView) layoutView.findViewById(R.id.listview_lv);
//        listView.setAdapter(adapter);

        // 获取RefreshLayout实例
        final SwipeRefreshLayoutLoad2 myRefreshListView = (SwipeRefreshLayoutLoad2)
                layoutView.findViewById(R.id.swipe_layout);

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

                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();

                myRefreshListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        datas.add(new Date().toGMTString());
                        adapter.notifyDataSetChanged();
                        // 加载完后调用该方法
                        myRefreshListView.setLoading(false);
                    }
                }, 1500);

            }
        });
    }

}



