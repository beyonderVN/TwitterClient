package com.longngohoang.news.appcore.data.backend.twitter;


import com.twitter.sdk.android.core.models.User;

import rx.Observable;

public interface TwitterService {


    Observable<User> getMyDetails();

}
