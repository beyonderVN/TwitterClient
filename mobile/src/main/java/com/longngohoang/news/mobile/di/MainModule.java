package com.longngohoang.news.mobile.di;

import android.content.Context;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;
import com.longngohoang.news.appcore.common.schedulers.SchedulerProvider;
import com.longngohoang.news.appcore.data.backend.twitter.TwitterService;
import com.longngohoang.news.appcore.data.backend.twitter.TwitterServiceImpl;
import com.longngohoang.news.appcore.data.source.TweetRepositoryImpl;
import com.longngohoang.news.appcore.data.source.UserRepositoryImpl;
import com.longngohoang.news.appcore.data.source.remote.TweetRemoteDataSource;
import com.longngohoang.news.appcore.data.source.remote.UserRemoteDataSource;
import com.longngohoang.news.appcore.interactor.GetHomeTimeLine;
import com.longngohoang.news.appcore.interactor.GetUserProfileUseCase;
import com.longngohoang.news.appcore.interactor.TweetRepository;
import com.longngohoang.news.appcore.interactor.UseCase;
import com.longngohoang.news.appcore.interactor.UserRepository;
import com.longngohoang.news.mobile.MainApplication;
import com.twitter.sdk.android.Twitter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private Context context;
    private final MainApplication application;

    public MainModule(MainApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider(SchedulerProvider schedulerProvider) {
        return schedulerProvider;
    }
//  provide BackendServices
    @Provides
    @Singleton
    TwitterService provideTwitterService() {
        return new TwitterServiceImpl(Twitter.getSessionManager().getActiveSession());
    }
//  provide Repositories
    @Provides @Singleton
    TweetRepository provideTweetRepository(TweetRemoteDataSource tweetRepository) {
        return new TweetRepositoryImpl(tweetRepository);
    }
    @Provides @Singleton
    UserRepository provideUserRepository(UserRemoteDataSource userRemoteDataSource) {
        return new UserRepositoryImpl(userRemoteDataSource);
    }
//  Provice UseCases
    @Provides @Named("getHomeTimeLine")
    UseCase provideGetHomeTimeLine(
            GetHomeTimeLine getHomeTimeLine) {
        return getHomeTimeLine;
    }

    @Provides @Named("getUserProfile")
    UseCase provideGetUserProfileUseCase(
            GetUserProfileUseCase getUserProfileUseCase) {
        return getUserProfileUseCase;
    }


}

