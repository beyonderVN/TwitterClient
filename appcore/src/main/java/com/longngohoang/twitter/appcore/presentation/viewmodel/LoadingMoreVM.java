package com.longngohoang.twitter.appcore.presentation.viewmodel;


/**
 * Created by Long on 10/5/2016.
 */

public class LoadingMoreVM extends BaseVM {


    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
