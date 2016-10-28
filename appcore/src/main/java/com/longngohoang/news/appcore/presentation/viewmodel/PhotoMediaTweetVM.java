package com.longngohoang.news.appcore.presentation.viewmodel;

/**
 * Created by Long on 10/28/2016.
 */
public class PhotoMediaTweetVM extends BaseVM {



    public PhotoMediaTweetVM() {

    }

    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
