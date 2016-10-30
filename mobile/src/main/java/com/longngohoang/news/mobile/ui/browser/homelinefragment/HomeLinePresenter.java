package com.longngohoang.news.mobile.ui.browser.homelinefragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.longngohoang.news.appcore.common.coremvp.SimpleMVPPresenter;
import com.longngohoang.news.appcore.data.model.TweetDM;
import com.longngohoang.news.appcore.interactor.DefaultSubscriber;
import com.longngohoang.news.appcore.interactor.UseCase;
import com.longngohoang.news.appcore.presentation.exception.ErrorMessageFactory;
import com.longngohoang.news.appcore.presentation.exception.NetworkConnectionException;
import com.longngohoang.news.appcore.presentation.viewmodel.mapper.Mapper;
import com.longngohoang.news.mobile.MainApplication;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class HomeLinePresenter extends SimpleMVPPresenter<HomeLineView, HomeLinePresentationModel> implements HomeLineView {

    private static final String TAG = "HomeLinePresenter";

    private final UseCase getHomeTimeLine;

    @Inject
    HomeLinePresenter(@Named("getHomeTimeLine")UseCase getHomeTimeLine) {
        this.getHomeTimeLine = getHomeTimeLine;
    }

    @Override
    public void attachView(HomeLineView mvpView, HomeLinePresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getHomeTimeLine.unsubscribe();
    }

    public void fetchRepositoryFirst(int column){
        if(isThereInternetConnection()){

        }else{
            showError(ErrorMessageFactory.create(MainApplication.getMainComponent().context(),new NetworkConnectionException()));

        }
        showProcess();
        getPresentationModel().refresh(column);
        getHomeTimeLine.execute(new HomeLinePresenter.getFirstHomeTimeline(),getPresentationModel().maxId);
    }
    public void fetchMore(){
        if(isThereInternetConnection()) {

        }else {
            showError(ErrorMessageFactory.create(MainApplication.getMainComponent().context(),new NetworkConnectionException()));

        }
        startLoadingMore();
        getHomeTimeLine.execute(new HomeLinePresenter.getMoreHomeTimeline(),getPresentationModel().maxId);
    }


    public void showProcess() {
        if(getMvpView()==null)return;
        getMvpView().showProcess();
    }


    public void showContent() {
        if(getMvpView()==null)return;
        getMvpView().showContent();
    }


    public void updateView() {
        if(getMvpView()==null)return;
        getMvpView().updateView();

    }


    public void showError(String s) {
        if(getMvpView()==null)return;
        getMvpView().showError(s);
    }

    private void startLoadingMore() {
        getPresentationModel().startLoadingMore();
        updateView();
    }
    private void  stopLoadingMore(){
        getPresentationModel().stopLoadingMore();
        updateView();
    }

    public void fixState(int column) {
        getPresentationModel().stopLoadingMore();
        getPresentationModel().fixLayout(column);
        updateView();
    }

    public void fetchRepository(int column) {
        if (!getPresentationModel().isShouldFetchRepositories()) {
            fixState(column);
        }else {
            fetchRepositoryFirst(column);
        }
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) MainApplication.getMainComponent().context().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    @RxLogSubscriber
    private final class getFirstHomeTimeline extends DefaultSubscriber<List<TweetDM>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);

        }

        @Override public void onNext(List<TweetDM> tweets) {
            Log.d(TAG, "onNext: "+tweets.size());
            if (!tweets.isEmpty()) {
                Log.d(TAG, "onSuccess: "+tweets.size());
                getPresentationModel().maxId=tweets.get(tweets.size()-1).id-1;
                getPresentationModel().addAndCollapse(Mapper.tranToTweetVMs(tweets));
                updateView();
            } else {
                Log.d(TAG, "onSuccess: is empty");

            }

        }

    }

    @RxLogSubscriber
    private final class getMoreHomeTimeline extends DefaultSubscriber<List<TweetDM>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);

        }

        @Override public void onNext(List<TweetDM> tweets) {
            Log.d(TAG, "onNext: "+tweets.size());
            stopLoadingMore();
            if (!tweets.isEmpty()) {
                Log.d(TAG, "onSuccess: "+tweets.size());
                getPresentationModel().maxId=tweets.get(tweets.size()-1).id;
                getPresentationModel().addAndCollapse(Mapper.tranToTweetVMs(tweets));
            } else {
                Log.d(TAG, "onSuccess: is empty");
                getPresentationModel().setNoMore(true);
            }
            updateView();

        }
    }




}
