package com.longngo.moviebox.wear;


import android.support.annotation.NonNull;


import com.longngohoang.news.appcore.common.coremvp.MVPPresenter;
import com.longngohoang.news.appcore.common.coremvp.MVPView;
import com.longngohoang.news.appcore.common.coremvp.MVPWearActivity;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public abstract class BaseActivity<M extends Serializable, V extends MVPView, P extends MVPPresenter<V, M>>
extends MVPWearActivity<M,V,P> {
    @Inject protected P presenter;

    @NonNull
    @Override
    protected P createPresenter() {
        return presenter;
    }
}
