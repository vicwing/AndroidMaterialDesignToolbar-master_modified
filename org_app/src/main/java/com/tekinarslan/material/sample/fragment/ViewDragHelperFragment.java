package com.tekinarslan.material.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.base.MyApplication;
import com.tekinarslan.material.sample.common.volley.JsonObjectDefaultPostRequest;

import org.json.JSONObject;

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


        volleyGet();
        return rootView;
    }

    private void volleyGet() {
//        LogUtils.d("222222222222222222222222222222222");
        String url="http://172.16.1.189:8087/qfang-api/appapi/v3_6/enums/filters/room?dataSource=SHENZHEN&bizType=SALE";
        JsonObjectDefaultPostRequest req = new JsonObjectDefaultPostRequest(url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                LogUtils.d("response  "+ response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MyApplication.getInstance().addToRequestQueue(req);
    }


}