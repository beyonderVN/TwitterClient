package com.longngohoang.twitter.appcore.data.source;

import com.longngohoang.twitter.appcore.data.model.UserDM;
import com.longngohoang.twitter.appcore.data.source.remote.UserRemoteDataSource;
import com.longngohoang.twitter.appcore.interactor.UserRepository;

import rx.Observable;

/**
 * Created by Long on 10/28/2016.
 */
public class UserRepositoryImpl implements UserRepository {
    UserDataSource userDataSource;
    public UserRepositoryImpl(UserRemoteDataSource userRemoteDataSource) {
        userDataSource=userRemoteDataSource;
    }

    @Override
    public Observable<UserDM> getUserProfile() {
        return userDataSource.getUserProfile()
                .map(user -> new UserDM(user));
    }
}
