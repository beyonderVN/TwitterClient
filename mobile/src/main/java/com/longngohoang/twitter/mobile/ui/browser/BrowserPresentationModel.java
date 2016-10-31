package com.longngohoang.twitter.mobile.ui.browser;

import com.longngohoang.twitter.appcore.presentation.BasePresentationModel;
import com.longngohoang.twitter.appcore.presentation.viewmodel.BaseVM;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class BrowserPresentationModel extends BasePresentationModel<BaseVM> implements Serializable {
    @Override
    public boolean isShouldFetchRepositories() {
        return false;
    }

    @Inject
    public BrowserPresentationModel() {

    }
}
