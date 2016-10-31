package com.longngohoang.twitter.appcore.data.source.realm.realmobject;

import com.longngohoang.twitter.appcore.data.model.TweetDM;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Admin on 30/10/2016.
 */

public class TweetEntity extends RealmObject {
    public long id;
    public String createdAt;
    public String text;
    public UserEntity user;

    public TweetEntity() {
    }

    public void map(TweetDM tweet, Realm realm)  {
        this.id = tweet.id;
        this.createdAt = tweet.createdAt;
        this.text = tweet.text;
        this.user = realm.createObject(UserEntity.class);;
        this.user.map(tweet.user);
    }
}
