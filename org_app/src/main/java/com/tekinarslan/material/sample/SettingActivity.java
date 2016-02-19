package com.tekinarslan.material.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.asha.nightowllib.NightOwl;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.d("setDisplayHomeAsUpEnabled........");
                onBackPressed();
            }
        });

        View v = findViewById(R.id.button);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NightOwl.owlNewDress(SettingActivity.this);
            }
        });

        if ( getSupportActionBar() != null )
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewGroup vp = (ViewGroup) findViewById(R.id.custom);
//        CustomView customView = new CustomView(this);
//        vp.addView(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // register
//        NightOwl.owlRegisterCustom(customView);
    }

    public static void launch(Context context){
        Intent i = new Intent(context,SettingActivity.class);
        context.startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: this.finish(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        NightOwl.owlResume(this);
    }
}
