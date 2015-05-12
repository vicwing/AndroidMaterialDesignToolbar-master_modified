package com.tekinarslan.material.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.customui.SwipeRefreshLayoutLoad;

import java.util.ArrayList;

/**
 * Created by vic on 2015/5/12.
 */
public class SwipRefreshLoadFragment extends Fragment{
    private static final String ARG_POSITION = "position";

    private int position;

    private final static String TAG = "MainActivity";
    private SwipeRefreshLayoutLoad mSwipeRefreshLayoutLoad;
    private ListView mListView;
    private android.widget.ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> values;


    public static SwipRefreshLoadFragment newInstance(int position) {
        SwipRefreshLoadFragment f = new SwipRefreshLoadFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_swiperefresh_layout2, container, false);


        setRefreshList(rootView);


        return rootView;
    }

    private void setRefreshList(View layoutView) {
        mSwipeRefreshLayoutLoad = (SwipeRefreshLayoutLoad)layoutView.findViewById(R.id.swipe_container);
        mListView = (ListView) layoutView.findViewById(R.id.list);
        mSwipeRefreshLayoutLoad.setFooterView(getActivity(), mListView, R.layout.listview_footer);

        values = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            values.add("Item " + i);
        }
        mArrayAdapter = new android.widget.ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mArrayAdapter);

        mSwipeRefreshLayoutLoad.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        mSwipeRefreshLayoutLoad.setRefreshing(true);
        mSwipeRefreshLayoutLoad.setOnRefreshListener(new SwipeRefreshLayoutLoad.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        values.add(0, "Swipe Down to Refresh " + values.size());
                        mArrayAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayoutLoad.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        mSwipeRefreshLayoutLoad.setOnLoadListener(new SwipeRefreshLayoutLoad.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        values.add("Swipe Up to Load More " + values.size());
                        mArrayAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayoutLoad.setLoading(false);
                    }
                }, 2000);
            }
        });
    }


    static class ArrayAdapter extends RecyclerView.Adapter<ViewHolder>{

        private String[] mArray;
        private Context mContext;

        public ArrayAdapter(Context context, String[] array) {
            mContext = context;
            mArray = array;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.mTextView.setText(mArray[i]);
        }

        @Override
        public int getItemCount() {
            return mArray.length;
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
