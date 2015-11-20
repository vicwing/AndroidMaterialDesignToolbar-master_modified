package me.zsj.multitheme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by zsj on 2015/7/3 0003.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if ( getSharedPreferences("theme", Context.MODE_PRIVATE)
                .getBoolean("isDark", false)) {
            setTheme(R.style.AppTheme_Dark);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);

        Log.i("BaseActivity", "BaseActivity onCreate");
    }
}
