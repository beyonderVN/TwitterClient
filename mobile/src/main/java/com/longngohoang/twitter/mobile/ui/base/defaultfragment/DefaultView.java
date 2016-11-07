package com.longngohoang.twitter.mobile.ui.base.defaultfragment;


import com.longngohoang.twitter.appcore.common.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface DefaultView extends MVPView {
    void showProcess();
    void showContent();
    void updateView();
    void showError(String s);
}
