package com.tekinarslan.material.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.fragment.CustomViewFragment;
import com.tekinarslan.material.sample.fragment.CustomViewFragment1;
import com.tekinarslan.material.sample.fragment.CustomViewGroupFragment;
import com.tekinarslan.material.sample.fragment.SampleFragment;
import com.tekinarslan.material.sample.fragment.SwipRefreshFragment;
import com.tekinarslan.material.sample.fragment.SwipRefreshLoadFragment2;
import com.tekinarslan.material.sample.fragment.SwipRefreshLoadFragment3;
import com.tekinarslan.material.sample.fragment.ViewDragHelperFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT =3;
    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return ViewDragHelperFragment.newInstance(position);
            case 1:
                return  CustomViewFragment.newInstance(position);
            case 2://
                return  CustomViewFragment1.newInstance(position);
            case 3:
                return SwipRefreshLoadFragment2.newInstance(position);
            case 4:
                return SwipRefreshFragment.newInstance(position);
            case 5:
                return CustomViewGroupFragment.newInstance(position);
            case 6:
                return SwipRefreshLoadFragment3.newInstance(position);
            case 7:
                return SampleFragment.newInstance(position);

        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

}