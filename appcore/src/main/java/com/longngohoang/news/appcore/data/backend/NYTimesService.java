package com.longngohoang.news.appcore.data.backend;


import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.longngohoang.news.appcore.data.model.Doc;
import com.longngohoang.news.appcore.data.model.SearchRequest;
import com.longngohoang.news.appcore.data.model.ResponseData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Observable<List<Doc>> getNews(SearchRequest searchRequest) {
        Log.d(TAG, "getNews: ");
        return NYTimesServiceApi.getNews(
                searchRequest.getQ(),
                tranDateForREquest(searchRequest.getBeginDate()),
                searchRequest.getSortList().get(searchRequest.getCurrentSort()),
                searchRequest.getStringFq(),
                searchRequest.getPage())
                .map(new Func1<ResponseData, List<Doc>>() {
                    @Override
                    public List<Doc> call(ResponseData responseData) {
                        List<Doc> docs = responseData.getResponse().getDocs();
                        return docs;
                    }
                });
    }
    String tranDateForREquest(String s  ){
        if (s == null) {
            return null;
        }

        try {

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            date = (Date)formatter.parse(s);
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMdd");
            String finalString = newFormat.format(date);
            return finalString;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }




}