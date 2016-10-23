package com.longngohoang.news.appcore.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    List<String> fq = new ArrayList<>();

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

    public List<String> getFq() {
        return fq;
    }
    public String getStringFq() {
        if (fq == null||fq.size()==0) {
            return null;
        }
        String fqString = "news_desk:(";
        for (int i = 0;i<fq.size();i++
                ) {
            fqString = fqString+"\""+fq.get(i)+"\"";
            if(i<fq.size()-1){
                fqString=fqString+"%20";
            }else {
                fqString=fqString+")";
            }
        }
        return fqString;
    }

    public void setFq(List<String> fq) {

        this.fq = fq;
    }
}
