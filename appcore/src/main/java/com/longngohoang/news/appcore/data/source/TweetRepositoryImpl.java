package com.longngohoang.news.appcore.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.longngohoang.news.appcore.data.model.TweetDM;
import com.longngohoang.news.appcore.interactor.TweetRepository;
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
    @NonNull
    private final TweetDataSource tweetDataSource;


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

    }
    @RxLogObservable
    @Override
    public Observable<List<TweetDM>> getHomeTimeLine() {
        return tweetDataSource.getHomeTimeLine()
                .map(tweets -> {
                    List<TweetDM> tweetDMs = new ArrayList<>();
                    for (Tweet tweet:tweets
                         ) {
                        tweetDMs.add(new TweetDM(tweet));
                    }
                    return tweetDMs;
                });
    }

    @Override
    public Observable<Boolean> sendTweet(String tweetText) {
        return tweetDataSource.sendTweet(tweetText);
    }
}
