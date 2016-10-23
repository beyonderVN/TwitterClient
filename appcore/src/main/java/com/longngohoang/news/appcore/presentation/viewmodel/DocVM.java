package com.longngohoang.news.appcore.presentation.viewmodel;


import com.longngohoang.news.appcore.data.model.Doc;


/**
 * Created by Long on 10/5/2016.
 */

public class DocVM extends BaseVM {

    private Doc doc;

    public DocVM(Doc doc) {
        this.doc = doc;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setMovie(Doc doc) {
        this.doc = doc;
    }

    @Override
    public int getVMType(NYTimesMViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

    @Override
    public String toString() {
        return doc.toString();
    }
}
