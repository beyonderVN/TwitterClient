package com.longngohoang.twitter.appcore.interactor;

import com.longngohoang.twitter.appcore.data.model.UserDM;

import rx.Observable;


/**
 * Created by Long on 10/28/2016.
 */
public interface UserRepository {
    Observable<UserDM> getUserProfile();
}
