package com.longngohoang.twitter.appcore.interactor;

import com.longngohoang.twitter.appcore.common.schedulers.BaseSchedulerProvider;

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
        if (objects.length < 2) {
            return tweetRepository.sendTweet((String)objects[0]);
        }else {

        }return tweetRepository.sendTweet((String)objects[0],(Long)objects[1]);

    }
}
