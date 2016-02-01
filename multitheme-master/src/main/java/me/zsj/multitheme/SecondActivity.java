package me.zsj.multitheme;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by zsj on 2015/7/2 0002.
 */
public class SecondActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
    }
}
