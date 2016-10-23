package com.longngohoang.news.appcore.interactor;

import com.longngohoang.news.appcore.common.schedulers.BaseSchedulerProvider;
import com.longngohoang.news.appcore.data.model.SearchRequest;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Admin on 22/10/2016.
 */

public class SearchArticleUseCase extends UseCase {

    private final ArticleRepository articleRepository;

    @Inject
    public SearchArticleUseCase(BaseSchedulerProvider baseSchedulerProvider, ArticleRepository articleRepository) {
        super(baseSchedulerProvider);
        this.articleRepository = articleRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Object ...objects) {
        return articleRepository.getNews((SearchRequest) objects[0]);
    }
}
