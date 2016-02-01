//package com.example.cdj.myapplication;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.support.v7.widget.RecyclerView;
//import butterknife.ButterKnife;
//
///**
// * Created by cdj on 2016/1/7.
// */
//public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
//    private final LayoutInflater mLayoutInflater;
//    private final Context mContext;
//    private String[] mTitles;
//
//    public NormalRecyclerViewAdapter(Context context) {
//        mTitles = context.getResources().getStringArray(R.array.titles);
//        mContext = context;
//        mLayoutInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
//        holder.mTextView.setText(mTitles[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mTitles == null ? 0 : mTitles.length;
//    }
//
//    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
//        @InjectView(R.id.text_view)
//        TextView mTextView;
//
//        NormalTextViewHolder(View view) {
//            super(view);
//            ButterKnife.inject(this, view);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
//                }
//            });
//        }
//    }
//}