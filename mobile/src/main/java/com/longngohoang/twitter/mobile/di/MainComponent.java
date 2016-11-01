package com.longngohoang.twitter.mobile.di;

import android.content.Context;

import com.longngohoang.twitter.appcore.common.schedulers.SchedulerProvider;
import com.longngohoang.twitter.appcore.data.backend.github.GithubService;
import com.longngohoang.twitter.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.twitter.appcore.interactor.TweetRepository;
import com.longngohoang.twitter.mobile.ui.browser.BrowserActivity;
import com.longngohoang.twitter.mobile.ui.browser.homelinefragment.HomeLineFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    Context context();
    GithubService githubService();
    TwitterService twitterService();
    SchedulerProvider schedulerProvider();
    TweetRepository tweetRepository();
    void inject(BrowserActivity mainActivity);

    void inject(HomeLineFragment tweetFragment);

}
