package com.longngohoang.news.appcore.data.model;

import com.longngohoang.news.appcore.data.source.realm.realmobject.TweetEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.Serializable;

/**
 * Created by Long on 10/28/2016.
 */

public class TweetDM implements Serializable {
    public long id;
    public String createdAt;
    public String text;
    public UserDM user;
    public MediaDM media;

    public TweetDM(Tweet tweet)  {
        this.id = tweet.id;
        this.createdAt = tweet.createdAt;
        this.text = tweet.text;
        this.user = new UserDM(tweet.user);
        if (!(tweet.entities.media == null)) {
            this.media = new MediaDM(tweet.entities.media.get(0).type,tweet.entities.media.get(0).displayUrl,tweet.entities.media.get(0).altText);
        }

    }
    public TweetDM(TweetEntity tweet)  {
        this.id = tweet.id;
        this.createdAt = tweet.createdAt;
        this.text = tweet.text;
        this.user = new UserDM(tweet.user) ;
    }

}


