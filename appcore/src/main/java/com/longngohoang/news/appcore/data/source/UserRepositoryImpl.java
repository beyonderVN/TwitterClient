package com.longngohoang.news.appcore.data.source;

import com.longngohoang.news.appcore.data.model.UserDM;
import com.longngohoang.news.appcore.data.source.remote.UserRemoteDataSource;
import com.longngohoang.news.appcore.interactor.UserRepository;

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
