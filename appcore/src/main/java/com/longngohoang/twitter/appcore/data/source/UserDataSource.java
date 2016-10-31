package com.longngohoang.twitter.appcore.data.source;

import com.twitter.sdk.android.core.models.User;

import rx.Observable;

/**
 * Created by Long on 10/28/2016.
 */
public interface UserDataSource {
    Observable<User> getUserProfile();

}
