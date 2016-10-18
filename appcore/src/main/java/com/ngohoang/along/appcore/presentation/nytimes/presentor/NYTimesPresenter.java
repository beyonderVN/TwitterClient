package com.ngohoang.along.appcore.presentation.nytimes.presentor;

import android.util.Log;

import com.ngohoang.along.appcore.common.coremvp.SimpleMVPPresenter;
import com.ngohoang.along.appcore.common.schedulers.BaseSchedulerProvider;
import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.source.NYTimesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
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
                .getNews()
//                .map(new Func1<List<Doc>, List<BaseVM>>() {
//                    @Override
//                    public List<BaseVM> call(List<Doc> competitions) {
//                        return Mapper.tranToVM(competitions);
//                    }
//                })

                .subscribeOn( baseSchedulerProvider.computation())
                .observeOn( baseSchedulerProvider.ui())
                .subscribe(new Observer<List<Doc>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(List<Doc> docs) {
                        Log.d(TAG, "onNext: "+docs.size());
//                        if (!competitions.isEmpty()) {
//                            Log.d(TAG, "onSuccess: "+competitions.size());
//
//                            getPresentationModel().addAndCollapse(competitions);
//
//                            getPresentationModel().setCurrentPage(getPresentationModel().getCurrentPage()+1);
//                        } else {
//                            Log.d(TAG, "onSuccess: is empty");
//                        }
//                        getPresentationModel().stopLoadingMore();
//                        updateView();

                    }
                });
        mSubscriptions.add(subscription);

    }
    public void fetchMore(){
//        startLoadingMore();
//        mSubscriptions.clear();
//        Subscription subscription = nyTimesRepository
//                .getMovieList(getPresentationModel().getNextPage())
//                .map(new Func1<List<Movie>, List<BaseVM>>() {
//                    @Override
//                    public List<BaseVM> call(List<Movie> competitions) {
//                        return Mapper.tranToVM(competitions);
//                    }
//                })
//                .subscribeOn( baseSchedulerProvider.computation())
//                .observeOn( baseSchedulerProvider.ui())
//                .subscribe(new Observer<List<BaseVM>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted: ");
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError: ", e);
//                    }
//
//                    @Override
//                    public void onNext(List<BaseVM> competitions) {
//                        Log.d(TAG, "onNext: "+competitions.size());
//                        if (!competitions.isEmpty()) {
//                            Log.d(TAG, "onSuccess: "+competitions.size());
//                            stopLoadingMore();
//                            getPresentationModel().addAndCollapse(competitions);
//                            getPresentationModel().setCurrentPage(getPresentationModel().getCurrentPage()+1);
//                            updateView();
//                        } else {
//                            Log.d(TAG, "onSuccess: is empty");
//                        }
//                    }
//                });
//        mSubscriptions.add(subscription);
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

    private void startLoadingMore() {
        getPresentationModel().startLoadingMore();
        updateView();
    }
    private void stopLoadingMore() {
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
