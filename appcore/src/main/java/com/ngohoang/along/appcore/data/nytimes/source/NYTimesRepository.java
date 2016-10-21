package com.ngohoang.along.appcore.data.nytimes.source;

/**
 * Created by Long on 10/6/2016.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.model.SearchRequest;
import com.ngohoang.along.appcore.data.nytimes.source.remote.NYTimesRemoteDataSource;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Concrete implementation to load data from the data sources into a cache.
 * <p/>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
// TODO: 12/10/2016 add flow to cache data to memmory and sqlite
@Singleton
public class NYTimesRepository implements NYTimesDataSource {
    private static final String TAG = "NYTimesRepository";

    @NonNull
    private final NYTimesDataSource mNYTimesRemoteDataSource;


    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    Map<Integer, Doc> mCachedCompetitions;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    @Inject
    public NYTimesRepository(NYTimesRemoteDataSource NYTimesRemoteDataSource) {
        mNYTimesRemoteDataSource = NYTimesRemoteDataSource;

    }





    @Override
    public Observable<List<Doc>> getNews(SearchRequest searchRequest) {


        Observable<List<Doc>> listObservable = getAndSaveRemoteNews(searchRequest);
        return listObservable;

    }

    @Override
    public void saveCompetition(Doc doc) {

    }



        private Observable<List<Doc>> getAndSaveRemoteNews(SearchRequest searchRequest) {
            return mNYTimesRemoteDataSource

                    .getNews(searchRequest)
                    .flatMap(new Func1<List<Doc>, Observable<List<Doc>>>() {
                        @Override
                        public Observable<List<Doc>> call(List<Doc> movieList) {
                            return Observable.from(movieList).doOnNext(new Action1<Doc>() {
                                @Override
                                public void call(Doc doc) {
                                    Log.d(TAG, "getAndSaveRemoteNews: "+doc.toString());
                                }
                            }).toList();
                        }
                    })
                    .doOnError(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.d(TAG, "call: "+throwable);
                        }
                    })
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {
                            mCacheIsDirty = false;
                        }
                    })
                    ;

        }

}
