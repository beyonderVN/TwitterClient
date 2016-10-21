package com.ngohoang.along.appcore.presentation.nytimes.presentor;

import android.util.Log;

import com.ngohoang.along.appcore.common.coremvp.SimpleMVPPresenter;
import com.ngohoang.along.appcore.common.schedulers.BaseSchedulerProvider;
import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.source.NYTimesRepository;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.BaseVM;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.mapper.Mapper;

import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 06/10/2016.
 */

public class NYTimesPresenter extends SimpleMVPPresenter<NYTimesView,NYTimesPresentationModel> implements NYTimesView {
    private static final String TAG = "NYTimesPresenter";
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    private NYTimesRepository nyTimesRepository;
    @Inject
    NYTimesPresenter(BaseSchedulerProvider baseSchedulerProvider, NYTimesRepository nyTimesRepository) {
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.nyTimesRepository = nyTimesRepository;
    }

    @Override
    public void attachView(NYTimesView mvpView, NYTimesPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    public void fetchRepositoryFirst(int column){

        showProcess();
        mSubscriptions.clear();
        getPresentationModel().reset(column);
        Subscription subscription = nyTimesRepository
                .getNews(getPresentationModel().getSearchRequest())
                .map(new Func1<List<Doc>, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(List<Doc> docList) {
                        return Mapper.tranToVM(docList);
                    }
                })
                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<BaseVM>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        if (e instanceof HttpException){
                            onErrorHttp400();
                        }
                    }

                    @Override
                    public void onNext(List<BaseVM> docs) {
                        Log.d(TAG, "onNext: "+docs.size());
                        if (!docs.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+docs.size());

                            getPresentationModel().addAndCollapse(docs);
                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                        getPresentationModel().stopLoadingMore();
                        updateView();

                    }
                });
        mSubscriptions.add(subscription);

    }
    public void fetchMore(){
        startLoadingMore();
        mSubscriptions.clear();
        Subscription subscription = nyTimesRepository
                .getNews(getPresentationModel().getSearchRequest())
                .map(new Func1<List<Doc>, List<BaseVM>>() {
                    @Override
                    public List<BaseVM> call(List<Doc> docList) {
                        return Mapper.tranToVM(docList);
                    }
                })

                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<BaseVM>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);

                    }

                    @Override
                    public void onNext(List<BaseVM> docs) {
                        Log.d(TAG, "onNext: "+docs.size());
                        stopLoadingMore();
                        if (!docs.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+docs.size());
                            getPresentationModel().addAndCollapse(docs);
                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }


                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void showProcess() {
        if(getMvpView()==null)return;
        getMvpView().showProcess();
    }

    @Override
    public void showContent() {
        if(getMvpView()==null)return;
        getMvpView().showContent();
    }

    @Override
    public void updateView() {
        if(getMvpView()==null)return;
        getMvpView().updateView();

    }

    @Override
    public void onErrorHttp400() {
        if(getMvpView()==null)return;
        getMvpView().onErrorHttp400();
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

}
