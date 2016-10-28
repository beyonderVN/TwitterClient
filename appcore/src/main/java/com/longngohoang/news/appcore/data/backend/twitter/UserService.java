package com.longngohoang.news.appcore.data.backend.twitter;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface UserService {
    @RxLogObservable
    @GET("/1.1/users/show.json")
    Call<User> show(@Query("user_id") long id);
}
