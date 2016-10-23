package com.longngohoang.news.mobile.di;

import android.content.Context;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;
import com.longngohoang.news.appcore.common.schedulers.SchedulerProvider;
import com.longngohoang.news.appcore.data.backend.NYTimesServiceApi;
import com.longngohoang.news.appcore.data.backend.NYTimesServiceFactory;
import com.longngohoang.news.appcore.data.source.ArticleRepositoryImpl;
import com.longngohoang.news.appcore.interactor.ArticleRepository;
import com.longngohoang.news.appcore.interactor.SearchArticleUseCase;
import com.longngohoang.news.appcore.interactor.UseCase;
import com.longngohoang.news.mobile.MainApplication;

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
    NYTimesServiceApi provideNYTimesServiceApi() {
        return NYTimesServiceFactory.makeService();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider(SchedulerProvider schedulerProvider) {
        return schedulerProvider;
    }

    @Provides @Singleton
    ArticleRepository provideUserRepository(ArticleRepositoryImpl articleRepository) {
        return articleRepository;
    }

    @Provides @Named("articaleList")
    UseCase provideSearchArticleUseCase(
            SearchArticleUseCase searchArticleUseCase) {
        return searchArticleUseCase;
    }


}

