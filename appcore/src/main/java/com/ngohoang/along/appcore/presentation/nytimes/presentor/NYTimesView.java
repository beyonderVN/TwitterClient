package com.ngohoang.along.appcore.presentation.nytimes.presentor;


import com.ngohoang.along.appcore.common.coremvp.MVPView;

/**
 * Created by Admin on 06/10/2016.
 */

public interface NYTimesView extends MVPView {
    void showProcess();
    void showContent();
    void updateView();
}
