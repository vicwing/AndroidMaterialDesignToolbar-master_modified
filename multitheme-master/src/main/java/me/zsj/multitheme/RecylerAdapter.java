package me.zsj.multitheme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zsj on 2015/7/2 0002.
 */
public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public RecylerAdapter(Context context, List<String> data) {
        mInflater = LayoutInflater.from(context);
        mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
