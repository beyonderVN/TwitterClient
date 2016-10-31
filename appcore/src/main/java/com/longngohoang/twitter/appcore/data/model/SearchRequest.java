package com.longngohoang.twitter.appcore.data.model;

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
    int currentSort=0;

    public List<String> getSortList() {
        return sortList;
    }

    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }

    List<String> sortList = new ArrayList<>();

    List<Desk> fq = new ArrayList<>();

    public SearchRequest() {
        sortList.add("Oldest");
        sortList.add("Newest");

        fq.add(new Desk(0,"Arts",true));
        fq.add(new Desk(1,"Fashion & Style",false));
        fq.add(new Desk(2,"Sports",true));

        currentSort =1;
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

    public int getCurrentSort() {
        return currentSort;
    }

    public void setCurrentSort(int currentSort) {
        this.currentSort = currentSort;
    }

    public List<Desk> getFq() {
        return fq;
    }
    public String getStringFq() {
        if (fq == null||fq.size()==0) {
            return null;
        }
        String fqString = "news_desk:(";
        int count =0;
        for (int i = 0;i<fq.size();i++) {
            if(fq.get(i).isChecked()){
                fqString = fqString+"\""+fq.get(i).getDes()+"\"";
                fqString=fqString+"%20";
                count++;
            }

        }

        fqString=fqString+")";
        return count==0?null:fqString;
    }

    public void setFq(List<Desk> fq) {
        this.fq = fq;
    }
    public boolean getCheckedFq(int position){
        return fq.get(position).isChecked();
    }
    public String getNameFq(int position){
        return fq.get(position).getDes();
    }
}
