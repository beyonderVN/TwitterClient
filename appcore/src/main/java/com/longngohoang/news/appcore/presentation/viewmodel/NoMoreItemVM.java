package com.longngohoang.news.appcore.presentation.viewmodel;


/**
 * Created by Long on 10/5/2016.
 */

public class NoMoreItemVM extends BaseVM {


    @Override
    public int getVMType(NYTimesMViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

}
