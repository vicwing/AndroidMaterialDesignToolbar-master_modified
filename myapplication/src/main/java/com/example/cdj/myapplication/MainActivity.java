package com.example.cdj.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.cdj.myapplication.cusview.MyViewPager;
import com.example.cdj.myapplication.fragment.Frag;
import com.example.cdj.myapplication.fragment.FragmentChat;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @Bind(R.id.main_content)
    MyViewPager mPager;

    @Bind(R.id.tab_menu)
    RadioGroup mRadioGroup;

    private FragmentChat chat;
    private FragmentChat address;
    private FragmentChat find;
    private FragmentChat me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogroup_mainactivity);
        ButterKnife.bind(this);
//        initView();
        initViewPager();



//        initToolbar();
//        initListView();
    }

    public void initView() {
        chat = new FragmentChat();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, chat).commit();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.one:
                        chat = new FragmentChat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, chat).commit();
                        break;
                    case R.id.two:
                        if (address == null) {
                            address = new FragmentChat();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, address).commit();
                        break;
                    case R.id.three:
                        find = new FragmentChat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, find).commit();
                        break;
                    case R.id.four:
                        me = new FragmentChat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, me).commit();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void initViewPager() {
        mRadioGroup.setOnCheckedChangeListener(new CheckedChangeListener());
        mRadioGroup.check(R.id.one);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        Frag frag ;
        Bundle bundle;
        for (int i = 0; i <4 ; i++) {
            frag = new Frag();
            bundle = new Bundle();
            bundle.putString("key", "hello world " + i);
            frag.setArguments(bundle);
            fragmentList.add(frag);
        }
        //ViewPager设置适配器
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));

//        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPager.setOnPageChangeListener(new PageChangeListener());
        mPager.setOffscreenPageLimit(4);
    }

    private class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.one:
                    mPager.setCurrentItem(0,false);
                    break;
                case R.id.two:
                    mPager.setCurrentItem(1,false);
                    break;
                case R.id.three:
                    mPager.setCurrentItem(2,false);
                    break;
                case R.id.four:
                    mPager.setCurrentItem(3,false);
                    break;
            }
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mRadioGroup.check(R.id.one);
                    break;
                case 1:
                    mRadioGroup.check(R.id.two);
                    break;
                case 2:
                    mRadioGroup.check(R.id.three);
                    break;
                case 3:
                    mRadioGroup.check(R.id.four);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Frag frag = new Frag();
            Bundle bundle = new Bundle();
            bundle.putString("key", "hello world " + position);
            frag.setArguments(bundle);
            return frag;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


    /**
     * Created by Administrator on 2015/6/24.
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list){
            super(fm);
            this.list=list;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

//    @Bind(R.id.recycler_view)
//    RecyclerView mRecyclerView;

//    private ListView mListView;
//    private ListViewAdapter mAdapter;
//    private Context mContext = this;
//
//
//    private void initListView() {
//        mListView = (ListView) findViewById(R.id.listview);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getActionBar();
//            if (actionBar != null) {
//                actionBar.setTitle("ListView");
//            }
//        }
//        /**
//         * The following comment is the sample usage of ArraySwipeAdapter.
//         */
////        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
////                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
////                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
////                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};
////        mListView.setAdapter(new ArraySwipeAdapterSample<String>(this, R.layout.listview_item, R.id.position, adapterData));
//
//        mAdapter = new ListViewAdapter(this);
//        mListView.setAdapter(mAdapter);
//        mAdapter.setMode(Attributes.Mode.Single);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ((SwipeLayout)(mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
//            }
//        });
//        mListView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("ListView", "OnTouch");
//                return false;
//            }
//        });
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                Log.e("ListView", "onScrollStateChanged");
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });
//
//        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("ListView", "onItemSelected:" + position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("ListView", "onNothingSelected:");
//            }
//        });
//    }
//
//    private void initToolbar() {
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
////        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
////        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
////        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
