package com.tekinarslan.material.sample;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.asha.nightowllib.NightOwl;
import com.socks.library.KLog;
import com.tekinarslan.material.sample.uitls.NightModeHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SampleActivity extends ActionBarActivity {
    private RequestQueue mRequestQueue;
    @Bind(R.id.toolbar)
    Toolbar toolbar1;

    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.navdrawer)
    ListView navdrawer;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private ListView mDrawerList;
    ViewPager pager;
    private String titles[] = new String[]{"Sample Tab 0", "Sample Tab 1", "Sample Tab 2", "Sample Tab 3", "Sample Tab 4"
            , "Sample Tab 5", "Sample Tab 6", "Sample Tab 7"};
    //    private String titles[] = new String[]{"写字楼", "二手房", "租房", "新房" };
    private Toolbar toolbar;

    SlidingTabLayout slidingTabLayout;
    private NightModeHelper mNightModeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mRequestQueue =  Volley.newRequestQueue(this);
        NightOwl.owlBeforeCreate(this);
        super.onCreate(savedInstanceState);
        mNightModeHelper = new NightModeHelper(this, R.style.AppTheme_Light);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        NightOwl.owlAfterCreate(this);
        KLog.init(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        String[] values = new String[]{
                "DEFAULT", "RED", "BLUE", "MATERIAL GREY", "夜间模式", "NightOwl:夜间模式切换"
        };
        setmDrawerList(values);
    }

    /**
     * 抽屉
     * @param values
     */
    private void setmDrawerList(String[] values) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.red));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.red));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.red));
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 2:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.blue));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 3:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 4:
                        mNightModeHelper.toggle();
                        break;
                    case 5:
                        //NightOwl
                        NightOwl.owlNewDress(SampleActivity.this);
                        LogUtils.d("NightOwl......");
                        break;
                }

            }
        });
    }

    private void setTheme() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        NightOwl.owlResume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
