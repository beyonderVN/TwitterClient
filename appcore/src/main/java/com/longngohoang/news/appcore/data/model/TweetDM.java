package com.longngohoang.news.appcore.data.model;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.io.Serializable;

/**
 * Created by Long on 10/28/2016.
 */

public class TweetDM implements Serializable {
    public long id;
    public String createdAt;
    public String text;
    public User user;

    public TweetDM(Tweet tweet) {
        this.id = tweet.id;
        this.createdAt = tweet.createdAt;
        this.text = tweet.text;
        this.user = tweet.user;
    }
}


