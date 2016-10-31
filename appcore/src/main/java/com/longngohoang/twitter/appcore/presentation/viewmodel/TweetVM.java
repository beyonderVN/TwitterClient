package com.longngohoang.twitter.appcore.presentation.viewmodel;

import com.longngohoang.twitter.appcore.data.model.MediaDM;
import com.longngohoang.twitter.appcore.data.model.TweetDM;
import com.longngohoang.twitter.appcore.data.model.UserDM;

/**
 * Created by Long on 10/28/2016.
 */
public class TweetVM extends BaseVM {

    public long id;
    public String createdAt;
    public String text;
    public UserDM user;
    public MediaDM media;
    public boolean isMediaEnable = false;

    public TweetVM(TweetDM tweetDM) {
        this.id = tweetDM.id;
        this.createdAt = tweetDM.createdAt;
        this.text = tweetDM.text;
        this.user = tweetDM.user;
        this.media = tweetDM.media;
    }

    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
