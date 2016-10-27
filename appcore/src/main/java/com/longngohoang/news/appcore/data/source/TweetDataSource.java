package com.longngohoang.news.appcore.data.source;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import rx.Observable;


/**
 * Created by Long on 10/27/2016.
 */
public interface TweetDataSource {
    Observable<List<Tweet>> getHomeTimeLine();
}
