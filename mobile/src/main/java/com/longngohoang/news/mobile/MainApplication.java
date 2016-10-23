package com.longngohoang.news.mobile;

import android.app.Application;
import android.content.Context;

import com.longngohoang.news.mobile.di.DaggerMainComponent;
import com.longngohoang.news.mobile.di.MainComponent;
import com.longngohoang.news.mobile.di.MainModule;


/**
 * Created by Long on 10/5/2016.
 */

public class MainApplication extends Application {

    public static Context mContext;
    static MainComponent applicationComponent;

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

    }

    public static MainComponent getMainComponent() {
        return applicationComponent;
    }
}
