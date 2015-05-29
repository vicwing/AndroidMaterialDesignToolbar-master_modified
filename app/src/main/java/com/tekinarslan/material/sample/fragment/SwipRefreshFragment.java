package com.tekinarslan.material.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.tekinarslan.material.sample.R;

/**
 * Created by vic on 2015/5/12.
 */
public class SwipRefreshFragment extends Fragment{
    private static final String ARG_POSITION = "position";

    private int position;

    public static SwipRefreshFragment newInstance(int position) {
        SwipRefreshFragment f = new SwipRefreshFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    PullRefreshLayout pullRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_swiperefresh_layout, container, false);


        String[] array = new String[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = "string " + i;
        }

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new android.widget.ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array));

        pullRefreshLayout = (PullRefreshLayout)rootView. findViewById(R.id.swipeRefreshLayout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
//        pullRefreshLayout.setRefreshing(true);
        return rootView;
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
