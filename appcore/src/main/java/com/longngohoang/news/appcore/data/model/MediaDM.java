package com.longngohoang.news.appcore.data.model;

import java.io.Serializable;

/**
 * Created by Admin on 31/10/2016.
 */
public class MediaDM implements Serializable{
    public String type;
    public String url;
    public String info;


    public MediaDM(String type, String url,String info) {
        this.type = type;
        this.url = url;
        this.info = info;
    }
}
