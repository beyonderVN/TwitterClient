package com.longngohoang.twitter.mobile;

import android.app.Application;
import android.content.Context;

import com.longngohoang.twitter.mobile.di.DaggerMainComponent;
import com.longngohoang.twitter.mobile.di.MainComponent;
import com.longngohoang.twitter.mobile.di.MainModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;


/**
 * Created by Long on 10/5/2016.
 */

public class MainApplication extends Application {

    public static final String TWITTER_KEY = "mz9TKFdy3QPqoIBECUVTMdaLN";
    public static final String TWITTER_SECRET = "VzbbFe3upBXR3H49WJowiyqWZCwzT92wO8podx6x3fTZiCrRJi";

    public static Context mContext;
    static MainComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        mContext = getApplicationContext();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setupGraph();
    }

    void setupGraph(){
        applicationComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();


    }

    public static MainComponent getMainComponent() {
        return applicationComponent;
    }

}
