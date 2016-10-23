
package com.longngohoang.news.appcore.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Response implements Serializable {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("docs")
    @Expose
    private List<Doc> docs = new ArrayList<Doc>();

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The docs
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     * 
     * @param docs
     *     The docs
     */
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

}
