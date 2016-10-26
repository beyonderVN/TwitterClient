package com.longngohoang.news.appcore.data.backend.twitter;


import com.longngohoang.news.appcore.data.backend.twitter.models.User;

import rx.Observable;

public interface TwitterService {


    Observable<User> getMyDetails();

}
