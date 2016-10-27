package com.longngohoang.news.mobile.di;

import android.content.Context;

import com.longngohoang.news.appcore.data.backend.NYTimesService;
import com.longngohoang.news.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.news.mobile.ui.browser.BrowserActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    Context context();
    TwitterService twitterService();
    NYTimesService nYTimesService();
    void inject(BrowserActivity mainActivity);
}
