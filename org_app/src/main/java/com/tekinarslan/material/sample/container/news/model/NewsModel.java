package com.tekinarslan.material.sample.container.news.model;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public interface NewsModel {

    void loadNews(String url, int type, com.tekinarslan.material.sample.container.news.model.NewsModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, com.tekinarslan.material.sample.container.news.model.NewsModelImpl.OnLoadNewsDetailListener listener);

}
