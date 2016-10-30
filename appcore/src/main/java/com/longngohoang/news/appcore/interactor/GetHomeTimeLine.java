package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */

public class GetHomeTimeLine extends UseCase {
    TweetRepository tweetRepository;
    @Inject
    public GetHomeTimeLine(BaseSchedulerProvider baseSchedulerProvider, TweetRepository tweetRepository) {
        super(baseSchedulerProvider);
        this.tweetRepository = tweetRepository;
    }
    @Override
    protected Observable buildUseCaseObservable(Object ...objects) {
        return tweetRepository.getHomeTimeLine((Long)objects[0]);
    }
}
