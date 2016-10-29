package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Long on 10/27/2016.
 */

public class SendTweetUseCase extends UseCase {
    TweetRepository tweetRepository;
    @Inject
    public SendTweetUseCase(BaseSchedulerProvider baseSchedulerProvider, TweetRepository tweetRepository) {
        super(baseSchedulerProvider);
        this.tweetRepository = tweetRepository;
    }
    @Override
    protected Observable buildUseCaseObservable(Object ...objects) {
        return tweetRepository.sendTweet((String)objects[0]);
    }
}
