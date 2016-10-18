package com.ngohoang.along.appcore.data.nytimes.backend;


import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.model.ResponseData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class NYTimesService {
    private static final String TAG = "NYTimesService";

    private final NYTimesServiceApi NYTimesServiceApi;

    @Inject
    public NYTimesService(NYTimesServiceApi footballServiceApi) {
        NYTimesServiceApi = footballServiceApi;
    }

    @RxLogObservable
    public Observable<List<Doc>> getNews() {
        return NYTimesServiceApi.getNews()
                .map(new Func1<ResponseData, List<Doc>>() {
                    @Override
                    public List<Doc> call(ResponseData responseData) {


                        List<Doc> docs = responseData.getResponse().getDocs();
                        return docs;
                    }
                });
    }




}