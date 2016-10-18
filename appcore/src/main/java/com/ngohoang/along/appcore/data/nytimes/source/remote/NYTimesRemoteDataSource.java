package com.ngohoang.along.appcore.data.nytimes.source.remote;


import com.ngohoang.along.appcore.data.nytimes.backend.NYTimesService;
import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.source.NYTimesDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Long on 10/6/2016.
 */
@Singleton
public class NYTimesRemoteDataSource implements NYTimesDataSource {
    private static NYTimesRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    NYTimesService NYTimesService;
    // Prevent direct instantiation.
    @Inject
    public NYTimesRemoteDataSource(NYTimesService NYTimesService) {
        this.NYTimesService = NYTimesService;
    }

    @Override
    public Observable<List<Doc>> getNews() {
        return NYTimesService.getNews();
    }
    @Override
    public void saveCompetition(Doc doc) {

    }
}
