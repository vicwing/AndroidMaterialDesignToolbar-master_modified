package com.tekinarslan.material.sample;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.tekinarslan.material.sample.common.utils.LogUtils;
import com.tekinarslan.material.sample.fragment_oschina.BlogViewPagerFragment;

/**
 * Created by cdj on 2015/7/17.
 */
public class PagerActivity extends FragmentActivity {

    private static final String TAG = PagerActivity.class.getCanonicalName();

//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        setContentView(R.layout.activity_pager);
//        LogUtils.d(TAG,"2222222222222222222222222222222222222");
////        initFragment();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        LogUtils.d(TAG, "2222222222222222222222222222222222222");
    }

    private void initFragment() {
                //找到FragmentTransaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BlogViewPagerFragment fragment = new BlogViewPagerFragment();
        //加到Activity中
        fragmentTransaction.add(R.id.container,fragment);
        //加到后台堆栈中，有下一句代码的话，点击返回按钮是退到Activity界面，没有的话，直接退出Activity
        //后面的参数是此Fragment的Tag。相当于id
        fragmentTransaction.addToBackStack("fragment1");
        //记住提交
        fragmentTransaction.commit();
    }
}
