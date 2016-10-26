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
import rx.Subscriber;

@Singleton
public class TwitterServiceImpl extends TwitterApiClient implements TwitterService {

    private static final String TAG = TwitterServiceImpl.class.getSimpleName();


    public TwitterServiceImpl(TwitterSession session) {
        super(session);
    }


    public Observable<User > getMyDetails() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                Callback<User> callback = new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        Log.i(TAG, "Got your details, pal!");
                        subscriber.onNext(result.data);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.e(TAG, e.getMessage(), e);
                        subscriber.onError(e);
                    }
                };

                TwitterServiceImpl.this.getService(UserService.class).show(Twitter.getSessionManager().getActiveSession().getUserId()).enqueue(callback);
            }
        });
    }


}
