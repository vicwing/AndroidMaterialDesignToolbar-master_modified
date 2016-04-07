package com.tekinarslan.material.sample;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.container.main.presenter.MainPresenter;
import com.tekinarslan.material.sample.container.main.presenter.MainPresenterImpl;
import com.tekinarslan.material.sample.container.main.view.MainView;
import com.tekinarslan.material.sample.container.news.widget.NewsFragment;
import com.tekinarslan.material.sample.customui.slidingtab_new.SlidingTabLayout;
import com.tekinarslan.material.sample.fragment.SampleFragment;
import com.tekinarslan.material.sample.uitls.NightModeHelper;
import com.tekinarslan.material.sample.uitls.SnackbarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 全新的activity 左侧用的是NavigationView,并且有一个FloatingActionButton
 */
public class MainActivity extends AppCompatActivity implements MainView,View.OnClickListener {

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
        setContentView(R.layout.activity_main);

//        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//        View parentView = contentFrameLayout.getChildAt(0);

//        getSystemStatusBarHeitht();
        setFitsSystemWindows();
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

//        Logger.init("YOUR_TAG")                 // default PRETTYLOGGER or use just init()
//                .methodCount(3)                 // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                .methodOffset(2)                // default 0
//                .logTool(new AndroidLogTool()); // custom log tool, optional
//        Logger.d("12333333333333112");
        Logger.init("vic").methodCount(1).hideThreadInfo();
        Logger.d("hello111111111111");
        LogUtils.d("hello   logutil");
    }

    private void getSystemStatusBarHeitht() {

        //状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        LogUtils.d("systemBarHeight  "+ statusBarHeight);

        int contentHeight = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentHeight - statusBarHeight;
        LogUtils.d("contentHeight  "+contentHeight +"  statusBarHeight "+ statusBarHeight+  " titleBarHeight "+titleBarHeight);
//        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面所求的状态栏的高度
//        int titleBarHeight = contentTop - statusBarHeight;



    }
    private void  setFitsSystemWindows(){
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            LogUtils.d("  setFitsSystemWindows(true)  "+"  parentView "+parentView.getHeight());
            parentView.setFitsSystemWindows(true);
        }
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
            case R.id.action_settings:
                SettingActivity.launch(this);
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

    @Override
    protected void onResume() {
        super.onResume();
//        NightOwl.owlResume(this);
    }
}
