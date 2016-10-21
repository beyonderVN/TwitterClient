package com.ngohoang.along.appcore.data.nytimes.model;

import java.io.Serializable;

/**
 * Created by Long on 10/21/2016.
 */

public class SearchRequest implements Serializable {
    String q;
    int page=0;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    String beginDate;
    String sort;
    String fq;

    public SearchRequest() {
    }
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        if(beginDate.equals("")){
            this.beginDate=null;
            return;
        }
        this.beginDate = beginDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }
}
