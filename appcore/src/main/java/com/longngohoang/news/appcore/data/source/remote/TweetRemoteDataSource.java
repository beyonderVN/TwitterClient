package com.longngohoang.news.appcore.data.source.remote;

import com.longngohoang.news.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.news.appcore.data.source.TweetDataSource;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */
@Singleton
public class TweetRemoteDataSource implements TweetDataSource {
    TwitterService twitterService;
    // Prevent direct instantiation.
    @Inject
    public TweetRemoteDataSource(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Override
    public Observable<List<Tweet>> getHomeTimeLine() {
        return twitterService.getHomeTimeLine();
    }

    @Override
    public Observable<Boolean> sendTweet(String tweetText) {
        return twitterService.sendTweet(tweetText);
    }
}
