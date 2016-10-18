package com.longngo.moviebox.di;

import android.content.Context;

import com.longngo.moviebox.ui.moviebox.activity.detail.DetailActivity;
import com.longngo.moviebox.ui.moviebox.activity.detail.DetailUsingOnlyRVActivity;
import com.longngo.moviebox.ui.moviebox.activity.main.MainActivity;
import com.longngo.moviebox.ui.nytimes.NYTimesActivity;
import com.ngohoang.along.appcore.data.moviebox.backend.MovieBoxService;
import com.ngohoang.along.appcore.data.nytimes.backend.NYTimesService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    Context context();
    MovieBoxService movieBoxService();
    NYTimesService nYTimesService();

//    ActivityModule plus(ActivityModule homeModule);
    void inject(MainActivity mainActivity);


    void inject(DetailActivity detailActivity);
    void inject(DetailUsingOnlyRVActivity detailUsingOnlyRVActivity);


    void inject(NYTimesActivity nyTimesActivity);

}
