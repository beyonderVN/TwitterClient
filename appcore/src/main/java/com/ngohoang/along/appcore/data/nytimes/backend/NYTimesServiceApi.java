package com.ngohoang.along.appcore.data.nytimes.backend;


import com.ngohoang.along.appcore.data.nytimes.model.ResponseData;

import retrofit2.http.GET;
import rx.Observable;

public interface NYTimesServiceApi {

    /**
     * Retrieve a list of competitions
     */


    @GET("articlesearch.json?begin_date=20160112&sort=oldest&fq=news_desk:(\"Education\"%20\"Health\")")
    Observable<ResponseData> getNews();







}
