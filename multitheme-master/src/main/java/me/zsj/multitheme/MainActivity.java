package me.zsj.multitheme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecylerAdapter mAdapter;
    private List<String> mDatas;
    /**
     * 用来保存主题标识
     */
    private SharedPreferences mPreferences;
    /**
     * 是否是 Dark Theme(即是黑色主题) 主题
     */
    private boolean mIsDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyleview);

        mAdapter = new RecylerAdapter(MainActivity.this, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            mDatas.add(i + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.dark_theme:
                mIsDark = true;
                putData(mIsDark);
                break;
            case R.id.light_theme:
                mIsDark = false;
                putData(mIsDark);
                break;
            case R.id.anthor_activity:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void putData(boolean isDark) {

        mPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
        mPreferences.edit().putBoolean("isDark", isDark).commit();
        //recreate();
        finish();
        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
