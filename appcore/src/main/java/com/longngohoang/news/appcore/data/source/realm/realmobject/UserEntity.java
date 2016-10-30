package com.longngohoang.news.appcore.data.source.realm.realmobject;

import com.longngohoang.news.appcore.data.model.UserDM;

import io.realm.RealmObject;

/**
 * Created by Long on 10/28/2016.
 */

public class UserEntity extends RealmObject{
    public long id;
    public String name;
    public String screenName;
    public String profileBannerUrl;
    public String profileImageUrl;

    public UserEntity() {
    }

    public void map(UserDM user) {
        this.id = user.id;
        this.name = user.name;
        this.profileBannerUrl = user.profileBannerUrl;
        this.profileImageUrl = user.profileImageUrl;
        this.screenName = user.screenName;
    }
}


