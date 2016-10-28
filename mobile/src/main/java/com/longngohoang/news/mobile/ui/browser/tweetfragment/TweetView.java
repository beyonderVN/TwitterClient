package com.longngohoang.news.mobile.ui.browser.tweetfragment;


import com.longngohoang.news.appcore.common.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface TweetView extends MVPView {
    void showProcess();
    void showContent();
    void updateView();
    void showError(String s);
}
