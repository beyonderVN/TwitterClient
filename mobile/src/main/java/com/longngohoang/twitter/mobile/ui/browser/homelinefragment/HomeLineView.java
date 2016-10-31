package com.longngohoang.twitter.mobile.ui.browser.homelinefragment;


import com.longngohoang.twitter.appcore.common.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface HomeLineView extends MVPView {
    void showProcess();
    void showContent();
    void updateView();
    void showError(String s);
}
