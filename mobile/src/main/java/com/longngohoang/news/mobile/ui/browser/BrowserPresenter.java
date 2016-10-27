package com.longngohoang.news.mobile.ui.browser;


import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.longngohoang.news.appcore.common.coremvp.SimpleMVPPresenter;
import com.longngohoang.news.appcore.interactor.DefaultSubscriber;
import com.longngohoang.news.appcore.interactor.UseCase;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class BrowserPresenter extends SimpleMVPPresenter<BrowserView, BrowserPresentationModel> {
    private static final String TAG = "BrowserPresenter";
    private final UseCase getHomeTimeLine;

    @Inject
    BrowserPresenter(@Named("getHomeTimeLine")UseCase getHomeTimeLine) {
        this.getHomeTimeLine = getHomeTimeLine;
    }

    @Override
    public void attachView(BrowserView mvpView, BrowserPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        getHomeTimeLine.execute(new getHomeTimeline());
    }
    @RxLogSubscriber
    private final class getHomeTimeline extends DefaultSubscriber<List<Tweet>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);

        }

        @Override public void onNext(List<Tweet> docs) {
            Log.d(TAG, "onNext: "+docs.size());
            if (!docs.isEmpty()) {
                Log.d(TAG, "onSuccess: "+docs.size());

            } else {
                Log.d(TAG, "onSuccess: is empty");

            }

        }
    }

}
