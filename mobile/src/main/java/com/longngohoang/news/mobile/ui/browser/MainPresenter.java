package com.longngohoang.news.mobile.ui.browser;


import com.longngohoang.news.appcore.common.coremvp.SimpleMVPPresenter;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class MainPresenter extends SimpleMVPPresenter<MainView, MainPresentationModel> {
    private static final String TAG = "MainPresenter";


    @Inject
    public MainPresenter() {


    }


    @Override
    public void attachView(MainView mvpView, MainPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
    }


}
