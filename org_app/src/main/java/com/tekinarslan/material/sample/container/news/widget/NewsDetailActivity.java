package com.tekinarslan.material.sample.container.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.NewsBean;
import com.tekinarslan.material.sample.container.news.presenter.NewsDetailPresenter;
import com.tekinarslan.material.sample.container.news.presenter.NewsDetailPresenterImpl;
import com.tekinarslan.material.sample.container.news.view.NewsDetailView;

import org.sufficientlysecure.htmltextview.HtmlTextView;


/**
 * Description : 新闻详情界面
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class NewsDetailActivity extends AppCompatActivity implements NewsDetailView {

    private NewsBean mNews;
    private HtmlTextView mTVNewsContent;
    private NewsDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;
//    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTVNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        mNews = (NewsBean) getIntent().getSerializableExtra("news");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getTitle());

//        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getImgsrc());
        Glide.with(this).load(mNews.getImgsrc()).into((ImageView) findViewById(R.id.ivImage));

        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(mNews.getDocid());
    }

    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        mTVNewsContent.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
