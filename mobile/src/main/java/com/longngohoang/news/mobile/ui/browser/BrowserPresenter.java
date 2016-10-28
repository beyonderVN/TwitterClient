package com.longngohoang.news.mobile.ui.browser;


import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.longngohoang.news.appcore.common.coremvp.SimpleMVPPresenter;
import com.longngohoang.news.appcore.data.model.UserDM;
import com.longngohoang.news.appcore.interactor.DefaultSubscriber;
import com.longngohoang.news.appcore.interactor.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class BrowserPresenter extends SimpleMVPPresenter<BrowserView, BrowserPresentationModel> {
    private static final String TAG = "BrowserPresenter";
    private final UseCase getUserProfileUseCase;

    @Inject
    BrowserPresenter(@Named("getUserProfile")UseCase getUserProfileUseCase) {
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @Override
    public void attachView(BrowserView mvpView, BrowserPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        getUserProfileUseCase.execute(new getHomeTimeline());
    }
    @RxLogSubscriber
    private final class getHomeTimeline extends DefaultSubscriber<UserDM> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);

        }

        @Override public void onNext(UserDM user) {
            Log.d(TAG, "onNext: "+user.name);
        }
    }

}
