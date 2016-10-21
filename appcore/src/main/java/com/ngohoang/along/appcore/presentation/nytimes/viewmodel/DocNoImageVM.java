package com.ngohoang.along.appcore.presentation.nytimes.viewmodel;


import com.ngohoang.along.appcore.data.nytimes.model.Doc;


/**
 * Created by Long on 10/5/2016.
 */

public class DocNoImageVM extends BaseVM {

    private Doc doc;

    public DocNoImageVM(Doc doc) {
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
