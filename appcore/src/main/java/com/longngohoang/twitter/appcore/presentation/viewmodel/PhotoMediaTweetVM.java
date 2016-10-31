package com.longngohoang.twitter.appcore.presentation.viewmodel;

import com.longngohoang.twitter.appcore.data.model.MediaDM;

/**
 * Created by Long on 10/28/2016.
 */
public class PhotoMediaTweetVM extends BaseVM {


    public final MediaDM media;
    public PhotoMediaTweetVM(MediaDM media) {
        this.media = media;
    }

    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
