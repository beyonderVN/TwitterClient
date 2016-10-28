package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Long on 10/28/2016.
 */
public class GetUserProfileUseCase extends UseCase {
    UserRepository userRepository;

    @Inject
    public GetUserProfileUseCase(BaseSchedulerProvider baseSchedulerProvider, UserRepository userRepository) {
        super(baseSchedulerProvider);
        this.userRepository = userRepository;
    }
    @Override
    protected Observable buildUseCaseObservable(Object ...objects) {
        return userRepository.getUserProfile();
    }
}
