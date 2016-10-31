package com.longngohoang.twitter.appcore.data.source.remote;

import com.longngohoang.twitter.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.twitter.appcore.data.source.UserDataSource;
import com.twitter.sdk.android.core.models.User;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Long on 10/28/2016.
 */
public class UserRemoteDataSource implements UserDataSource {
    TwitterService twitterService;
    // Prevent direct instantiation.
    @Inject
    public UserRemoteDataSource(TwitterService twitterService) {
        this.twitterService = twitterService;
    }


    @Override
    public Observable<User> getUserProfile() {
        return twitterService.getUserProfile();
    }

}
