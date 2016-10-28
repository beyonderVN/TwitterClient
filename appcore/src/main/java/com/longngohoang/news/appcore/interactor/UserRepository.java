package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.data.model.UserDM;

import rx.Observable;


/**
 * Created by Long on 10/28/2016.
 */
public interface UserRepository {
    Observable<UserDM> getUserProfile();
}
