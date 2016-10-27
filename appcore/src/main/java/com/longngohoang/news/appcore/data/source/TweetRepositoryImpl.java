package com.longngohoang.news.appcore.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.longngohoang.news.appcore.data.model.Doc;
import com.longngohoang.news.appcore.interactor.TweetRepository;
import com.twitter.sdk.android.core.models.Tweet;

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
    Map<Integer, Doc> mCachedTweets;

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

    @Override
    public Observable<List<Tweet>> getHomeTimeLine() {
        return tweetDataSource.getHomeTimeLine();
    }
}
