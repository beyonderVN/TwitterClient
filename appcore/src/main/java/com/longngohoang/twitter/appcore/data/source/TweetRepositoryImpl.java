package com.longngohoang.twitter.appcore.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.longngohoang.twitter.appcore.data.model.TweetDM;
import com.longngohoang.twitter.appcore.data.source.realm.TweetRealmDataSource;
import com.longngohoang.twitter.appcore.interactor.TweetRepository;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */
@Singleton
public class TweetRepositoryImpl implements TweetRepository {
    private static final String TAG = "TweetRepositoryImpl";
    @NonNull
    private final TweetDataSource tweetDataSource;
    private final TweetRealmDataSource tweetRealmDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    @Nullable
    Map<Integer, Tweet> mCachedTweets;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    @Inject
    public TweetRepositoryImpl(TweetDataSource tweetRemoteDataSource) {
        tweetDataSource = tweetRemoteDataSource;
        this.tweetRealmDataSource = new TweetRealmDataSource();

    }
    @RxLogObservable
    @Override
    public Observable<List<TweetDM>> getHomeTimeLine(Long maxId) {
        Observable remoteTweetsDatasource =
        tweetDataSource.getHomeTimeLine(maxId)
                .map(tweets -> {
                    List<TweetDM> tweetDMs = new ArrayList<>();
                    for (Tweet tweet:tweets
                         ) {
                        tweetDMs.add(new TweetDM(tweet));
                    }
                    tweetRealmDataSource.store(tweetDMs);
                    return tweetDMs;
                });
        Observable realmTweetsDataSource =
       tweetRealmDataSource.getHomeTimeLine(maxId);
        return remoteTweetsDatasource
                .doOnError(throwable -> {
                    Log.e(TAG, "getHomeTimeLine: "+throwable);
                })
                .onErrorResumeNext(realmTweetsDataSource)
                ;
    }

    @Override
    public Observable<Boolean> sendTweet(String tweetText) {
        return tweetDataSource.sendTweet(tweetText);
    }

    @Override
    public Observable<Boolean> sendTweet(String tweetText, Long statusId) {
        return tweetDataSource.sendTweet(tweetText,statusId);
    }
}
