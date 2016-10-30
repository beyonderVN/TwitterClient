package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.data.model.TweetDM;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */
public interface TweetRepository  {
    Observable<List<TweetDM>> getHomeTimeLine(Long maxId);
    Observable<Boolean> sendTweet(String tweetText);
}
