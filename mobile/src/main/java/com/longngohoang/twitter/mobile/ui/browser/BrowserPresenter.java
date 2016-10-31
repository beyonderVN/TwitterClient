package com.longngohoang.twitter.mobile.ui.browser;


import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.longngohoang.twitter.appcore.common.coremvp.SimpleMVPPresenter;
import com.longngohoang.twitter.appcore.data.model.UserDM;
import com.longngohoang.twitter.appcore.interactor.DefaultSubscriber;
import com.longngohoang.twitter.appcore.interactor.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class BrowserPresenter extends SimpleMVPPresenter<BrowserView, BrowserPresentationModel> {
    private static final String TAG = "BrowserPresenter";
    private final UseCase getUserProfileUseCase;
    private final UseCase sendTweet;

    @Inject
    BrowserPresenter(@Named("getUserProfile")UseCase getUserProfileUseCase,
                     @Named("sendTweet")UseCase sendTweet) {
        this.getUserProfileUseCase = getUserProfileUseCase;
        this.sendTweet = sendTweet;
    }

    @Override
    public void attachView(BrowserView mvpView, BrowserPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        getUserProfileUseCase.execute(new getUserProfileSubscriber());
    }

    public void sendTweet(String s) {
        sendTweet.execute(new sendTweetSubscriber(),s);
    }

    @RxLogSubscriber
    private final class getUserProfileSubscriber extends DefaultSubscriber<UserDM> {

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

    private class sendTweetSubscriber extends DefaultSubscriber<Boolean> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);

        }

        @Override public void onNext(Boolean success) {
            Log.d(TAG, "onNext: "+success);
        }
    }
}
