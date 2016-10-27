package com.longngohoang.news.appcore.data.backend;


import com.longngohoang.news.appcore.data.model.ResponseData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NYTimesServiceApi {

    @GET("articlesearch.json")
    Observable<ResponseData> getNews(@Query("q") String q,
                                     @Query("begin_date") String beginDate,
                                     @Query("sort") String sort,
                                     @Query("fq") String fq,
                                     @Query("page") int page);


}
