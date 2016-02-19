package com.tekinarslan.material.sample.container.news.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.apkfuns.logutils.LogUtils;
import com.tekinarslan.material.sample.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cdj on 2016/2/17.
 */
public class NewsDetailSimpleActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{


    @Bind(R.id.id_toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_tablayout)
    TabLayout mTablayout;
    @Bind(R.id.id_appbarlayout)
    AppBarLayout idAppbarlayout;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.htNewsContent)
    HtmlTextView htNewsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_simple);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.RED);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        mViewPager.setOffscreenPageLimit(3);bu
//        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.top));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.nba));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.cars));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.jokes));


//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//        mNews = (NewsBean) getIntent().getSerializableExtra("news");
//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("MaterialDesign New View");
//        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getImgsrc());

//        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
//        mNewsDetailPresenter.loadNewsDetail(mNews.getDocid());
        idAppbarlayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        LogUtils.d("onOffsetChanged    "+verticalOffset);
    }
}
