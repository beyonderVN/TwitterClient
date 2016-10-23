package com.longngohoang.news.appcore.presentation.presentor;


import android.content.Context;

import com.longngohoang.news.appcore.common.coremvp.MVPView;

/**
 * Created by Admin on 06/10/2016.
 */

public interface NYTimesView extends MVPView {
    Context context();
    void showProcess();
    void showContent();
    void updateView();
    void showError(String s);
}
