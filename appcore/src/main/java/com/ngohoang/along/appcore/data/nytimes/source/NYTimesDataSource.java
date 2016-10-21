package com.ngohoang.along.appcore.data.nytimes.source;


import com.ngohoang.along.appcore.data.nytimes.model.Doc;
import com.ngohoang.along.appcore.data.nytimes.model.SearchRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 10/6/2016.
 */

public interface NYTimesDataSource {


    Observable<List<Doc>> getNews(SearchRequest searchRequest);

    void saveCompetition(Doc competition);
}
