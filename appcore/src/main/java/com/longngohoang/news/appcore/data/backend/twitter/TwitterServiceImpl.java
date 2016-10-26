package com.longngohoang.news.appcore.data.backend.twitter;

import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class TwitterServiceImpl extends TwitterApiClient implements TwitterService {

    private static final String TAG = TwitterServiceImpl.class.getSimpleName();


    public TwitterServiceImpl(TwitterSession session) {
        super(session);
    }


    public Observable<com.longngohoang.news.appcore.data.backend.twitter.models.User > getMyDetails() {
        return Observable.create(subscriber -> {
            Callback<User> callback = new Callback<User>() {
                @Override
                public void success(Result<User> result) {
                    Log.i(TAG, "Got your details, pal!");
                    subscriber.onNext(new com.longngohoang.news.appcore.data.backend.twitter.models.User (result.data.name, result.data.screenName, result.data.profileImageUrl));
                }

                @Override
                public void failure(TwitterException e) {
                    Log.e(TAG, e.getMessage(), e);
                    subscriber.onError(e);
                }
            };

            getService(UserService.class).show(Twitter.getSessionManager().getActiveSession().getUserId()).enqueue(callback);
        });
    }


}
