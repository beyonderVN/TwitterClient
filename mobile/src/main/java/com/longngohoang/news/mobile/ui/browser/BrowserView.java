package com.longngohoang.news.mobile.ui.browser;


import com.longngohoang.news.appcore.common.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface BrowserView extends MVPView {
    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();
}
