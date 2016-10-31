package com.longngohoang.twitter.appcore.data.source;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import rx.Observable;


/**
 * Created by Long on 10/27/2016.
 */
public interface TweetDataSource {
    Observable<List<Tweet>> getHomeTimeLine(Long maxId);
    Observable<Boolean> sendTweet(String tweetText);

    Observable<Boolean> sendTweet(String tweetText, Long statusId);
}
