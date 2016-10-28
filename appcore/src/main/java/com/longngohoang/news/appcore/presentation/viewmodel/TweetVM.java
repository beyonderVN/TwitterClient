package com.longngohoang.news.appcore.presentation.viewmodel;

import com.twitter.sdk.android.core.models.User;

/**
 * Created by Long on 10/28/2016.
 */
public class TweetVM extends BaseVM {

    public long id;
    public String createdAt;
    public String text;
    public User user;

    public TweetVM(long id, String createdAt, String text, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
        this.user = user;
    }

    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
