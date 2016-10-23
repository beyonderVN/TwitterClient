package com.longngohoang.news.appcore.data.source.remote;


import com.longngohoang.news.appcore.data.backend.NYTimesService;
import com.longngohoang.news.appcore.data.model.Doc;
import com.longngohoang.news.appcore.data.model.SearchRequest;
import com.longngohoang.news.appcore.data.source.NYTimesDataSource;

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
    com.longngohoang.news.appcore.data.backend.NYTimesService NYTimesService;
    // Prevent direct instantiation.
    @Inject
    public NYTimesRemoteDataSource(NYTimesService NYTimesService) {
        this.NYTimesService = NYTimesService;
    }

    @Override
    public Observable<List<Doc>> getNews(SearchRequest searchRequest) {
        return NYTimesService.getNews(searchRequest);
    }
    @Override
    public void saveCompetition(Doc doc) {

    }
}
