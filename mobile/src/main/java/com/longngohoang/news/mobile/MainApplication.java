package com.longngohoang.news.mobile;

import android.app.Application;
import android.content.Context;

import com.longngohoang.news.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.news.appcore.data.backend.twitter.TwitterServiceImpl;
import com.longngohoang.news.mobile.di.DaggerMainComponent;
import com.longngohoang.news.mobile.di.MainComponent;
import com.longngohoang.news.mobile.di.MainModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


/**
 * Created by Long on 10/5/2016.
 */

public class MainApplication extends Application {

    public static final String TWITTER_KEY = "mz9TKFdy3QPqoIBECUVTMdaLN";
    public static final String TWITTER_SECRET = "VzbbFe3upBXR3H49WJowiyqWZCwzT92wO8podx6x3fTZiCrRJi";

    public static Context mContext;
    static MainComponent applicationComponent;
    static TwitterService  twitterService;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setupGraph();
    }

    void setupGraph(){
        applicationComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        twitterService = new TwitterServiceImpl(Twitter.getSessionManager().getActiveSession());
    }

    public static MainComponent getMainComponent() {
        return applicationComponent;
    }
    public static TwitterService getTwitterService() {
        return twitterService;
    }
}
