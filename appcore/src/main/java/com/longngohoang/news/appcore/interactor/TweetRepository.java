package com.longngohoang.news.appcore.interactor;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */
public interface TweetRepository  {
    Observable<List<Tweet>> getHomeTimeLine();
}
