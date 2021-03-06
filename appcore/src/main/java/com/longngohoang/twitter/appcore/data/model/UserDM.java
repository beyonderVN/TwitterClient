package com.longngohoang.twitter.appcore.data.model;

import com.longngohoang.twitter.appcore.data.source.realm.realmobject.UserEntity;
import com.twitter.sdk.android.core.models.User;

import java.io.Serializable;

/**
 * Created by Long on 10/28/2016.
 */

public class UserDM implements Serializable {
    public long id;
    public String name;
    public String screenName;
    public String profileBannerUrl;
    public String profileImageUrl;


    public UserDM(User user) {
        this.id = user.id;
        this.name = user.name;
        this.profileBannerUrl = user.profileBannerUrl;
        this.profileImageUrl = user.profileImageUrl;
        this.screenName = user.screenName;
    }
    public UserDM(UserEntity user) {
        this.id = user.id;
        this.name = user.name;
        this.profileBannerUrl = user.profileBannerUrl;
        this.profileImageUrl = user.profileImageUrl;
        this.screenName = user.screenName;
    }
}


