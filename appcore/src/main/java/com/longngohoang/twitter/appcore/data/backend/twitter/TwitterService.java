package com.longngohoang.twitter.appcore.data.backend.twitter;


import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import rx.Observable;

public interface TwitterService {


    Observable<User> getUserProfile();
    Observable<List<Tweet>> getHomeTimeLine(Long maxId);
    Observable<Boolean> sendTweet(String tweetText);

    Observable<Boolean> sendTweet(String tweetText, Long statusId);
}
