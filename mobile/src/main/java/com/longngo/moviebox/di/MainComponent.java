package com.longngo.moviebox.di;

import android.content.Context;

import com.longngo.moviebox.ui.nytimes.NYTimesActivity;
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

    NYTimesService nYTimesService();


    void inject(NYTimesActivity nyTimesActivity);

}
