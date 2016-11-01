package com.longngo.moviebox.wear.di;

import android.content.Context;

import com.longngo.moviebox.wear.WearApplication;
import com.longngohoang.twitter.appcore.common.schedulers.BaseSchedulerProvider;
import com.longngohoang.twitter.appcore.common.schedulers.SchedulerProvider;
import com.longngohoang.twitter.appcore.data.moviebox.backend.MovieBoxServiceApi;
import com.longngohoang.twitter.appcore.data.moviebox.backend.MovieBoxServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private Context context;
    private final WearApplication application;

    public MainModule(WearApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    MovieBoxServiceApi provideMovieBoxServiceApi() {
        return MovieBoxServiceFactory.makeService();
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider(SchedulerProvider schedulerProvider) {
        return schedulerProvider;
    }

//    @Provides
//    @Singleton
//    HolderFactory provideHolderFactory(HolderFactoryImpl holderFactory) {
//        return holderFactory;
//    }
//    @Provides
//    @Singleton
//    NYTimesRemoteDataSource provideMoviesRemoteDataSource(NYTimesRemoteDataSource competitionsDataSource) {
//        return competitionsDataSource;
//    }
////    @Provides
//    @Singleton
//    MoviesLocalDataSource provideMoviesLocalDataSource(MoviesLocalDataSource moviesLocalDataSource) {
//        return moviesLocalDataSource;
//    }
////    @Provides
////    @Singleton
////    NYTimesRepository provideMoviesRepository(NYTimesRepository moviesRepository) {
////        return moviesRepository;
////    }
//
//    @Provides
//    @Singleton
//    NYTimesService provideMovieBoxService(NYTimesService movieBoxService) {
//        return movieBoxService;
//    }





}

