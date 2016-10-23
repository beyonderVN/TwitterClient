package com.longngohoang.news.appcore.presentation.presentor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.longngohoang.news.appcore.common.coremvp.SimpleMVPPresenter;
import com.longngohoang.news.appcore.data.model.Doc;
import com.longngohoang.news.appcore.interactor.DefaultSubscriber;
import com.longngohoang.news.appcore.interactor.UseCase;
import com.longngohoang.news.appcore.presentation.exception.ErrorMessageFactory;
import com.longngohoang.news.appcore.presentation.exception.NetworkConnectionException;
import com.longngohoang.news.appcore.presentation.viewmodel.mapper.Mapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Admin on 06/10/2016.
 */

public class NYTimesPresenter extends SimpleMVPPresenter<NYTimesView,NYTimesPresentationModel> {
    private static final String TAG = "NYTimesPresenter";
    Context context;
    private UseCase searchArticleUseCase;
    @Inject
    NYTimesPresenter(@Named("articaleList")UseCase searchArticleUseCase) {
        this.searchArticleUseCase = searchArticleUseCase;
    }

    @Override
    public void attachView(NYTimesView mvpView, NYTimesPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        context = getMvpView().context();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchArticleUseCase.unsubscribe();
    }

    public void fetchRepositoryFirst(int column){
        if(isThereInternetConnection()){
            showProcess();
            getPresentationModel().reset(column);
            searchArticleUseCase.execute(new FirstArticaleSubscriber(),getPresentationModel().getSearchRequest());
        }else{
            showError(ErrorMessageFactory.create(context,new NetworkConnectionException()));

        }

    }
    public void fetchMore(){
        if(isThereInternetConnection()) {
            startLoadingMore();
            searchArticleUseCase.execute(new MoreArticaleSubscriber(), getPresentationModel().getSearchRequest());
        }else {
            showError(ErrorMessageFactory.create(context,new NetworkConnectionException()));

        }
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

    @RxLogSubscriber
    private final class FirstArticaleSubscriber extends DefaultSubscriber<List<Doc>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);
            showError(ErrorMessageFactory.create(context,(Exception) e));
        }

        @Override public void onNext(List<Doc> docs) {
            Log.d(TAG, "onNext: "+docs.size());
            if (!docs.isEmpty()) {
                Log.d(TAG, "onSuccess: "+docs.size());
                getPresentationModel().addAndCollapse(Mapper.tranToVM(docs));
                updateView();
            } else {
                Log.d(TAG, "onSuccess: is empty");
                showError("No Data Found");
            }

        }
    }

    @RxLogSubscriber
    private final class MoreArticaleSubscriber extends DefaultSubscriber<List<Doc>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "showError: ", e);
            showError(ErrorMessageFactory.create(context,(Exception) e));
        }

        @Override public void onNext(List<Doc> docs) {
            Log.d(TAG, "onNext: "+docs.size());
            stopLoadingMore();
            if (!docs.isEmpty()) {
                Log.d(TAG, "onSuccess: "+docs.size());
                getPresentationModel().addAndCollapse(Mapper.tranToVM(docs));
            } else {
                Log.d(TAG, "onSuccess: is empty");
                getPresentationModel().setNoMore(true);
            }
            updateView();

        }
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
