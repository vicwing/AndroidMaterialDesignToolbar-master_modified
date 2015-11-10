package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;

public class ViewDragHelperFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    public static final String TAG = ViewDragHelperFragment.class.getSimpleName();
    private int position;

    public static ViewDragHelperFragment newInstance(int position) {
        ViewDragHelperFragment f = new ViewDragHelperFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View rootView = inflater.inflate(R.layout.fragment_viewdraghelper, container, false);
        rootView.findViewById(R.id.go_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(),)
            }
        });
        return rootView;
    }


}