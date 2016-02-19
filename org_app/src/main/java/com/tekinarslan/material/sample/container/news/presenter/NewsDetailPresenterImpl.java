package com.tekinarslan.material.sample.container.news.presenter;

import android.content.Context;

import com.tekinarslan.material.sample.bean.NewsDetailBean;
import com.tekinarslan.material.sample.container.news.model.NewsModel;
import com.tekinarslan.material.sample.container.news.model.NewsModelImpl;


/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/21
 */
public class NewsDetailPresenterImpl implements com.tekinarslan.material.sample.container.news.presenter.NewsDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {

    private Context mContent;
    private com.tekinarslan.material.sample.container.news.view.NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, com.tekinarslan.material.sample.container.news.view.NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }


    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if(newsDetailBean != null) {
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
