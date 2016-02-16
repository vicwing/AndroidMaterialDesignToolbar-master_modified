package com.tekinarslan.material.sample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.asha.nightowllib.NightOwl;
import com.tekinarslan.material.sample.customui.slidingtab_new.SlidingTabLayout;
import com.tekinarslan.material.sample.fragment.SampleFragment;
import com.tekinarslan.material.sample.main.presenter.MainPresenter;
import com.tekinarslan.material.sample.main.presenter.MainPresenterImpl;
import com.tekinarslan.material.sample.main.view.MainView;
import com.tekinarslan.material.sample.news.NewsFragment;
import com.tekinarslan.material.sample.uitls.NightModeHelper;
import com.tekinarslan.material.sample.uitls.SnackbarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 全新的activity 左侧用的是NavigationView,并且有一个FloatingActionButton
 */
public class SampleActivity extends AppCompatActivity implements MainView,View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


//    @Bind(R.id.viewpager)
//    ViewPager mViewPager;

//    @Bind(R.id.navdrawer)
//    ListView navdrawer;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.id_floatingactionbutton)
    android.support.design.widget.FloatingActionButton idFloatingactionbutton;

    private ActionBarDrawerToggle mDrawerToggle;


    private ListView mDrawerList;
    //    private String titles[] = new String[]{"Sample Tab 0", "Sample Tab 1", "Sample Tab 2", "Sample Tab 3", "Sample Tab 4"
//            , "Sample Tab 5", "Sample Tab 6", "Sample Tab 7"};
    private String titles[] = new String[]{"写字楼啦啦啦", "二手房", "租房", "新房", "查询房价"};

    SlidingTabLayout slidingTabLayout;
    private NightModeHelper mNightModeHelper;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        NightOwl.owlBeforeCreate(this);
        super.onCreate(savedInstanceState);
//        mNightModeHelper = new NightModeHelper(this, R.style.AppTheme_Light);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);
//        NightOwl.owlAfterCreate(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        setupDrawerContent(mNavigationView);
        mMainPresenter = new MainPresenterImpl(this);
        switch2News();
//        setDrawerLayout();
        idFloatingactionbutton.setOnClickListener(this);
    }


    /**
     * 抽屉
     */
    private void setDrawerLayout() {
//        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        String[] values = new String[]{
                "DEFAULT", "RED", "BLUE", "MATERIAL GREY", "夜间模式", "NightOwl:夜间模式切换"
        };
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
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.red));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.red));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.red));
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 2:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.blue));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        mDrawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case 3:
                        mDrawerList.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
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

    @Override
    protected void onResume() {
        super.onResume();
//        NightOwl.owlResume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
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
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private MainPresenter mMainPresenter;

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void switch2News() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2Images() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SampleFragment()).commit();
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SampleFragment()).commit();
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2About() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SampleFragment()).commit();
        mToolbar.setTitle(R.string.navigation_about);
    }

    @Override
    public void onClick(View v) {
        LogUtils.d("click~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        switch (v.getId()) {
            // FloatingActionButton的点击事件
            case R.id.id_floatingactionbutton:
                SnackbarUtil.show(v, getString(R.string.plusone), 0);
                break;
            default:
                break;
        }
    }
}
