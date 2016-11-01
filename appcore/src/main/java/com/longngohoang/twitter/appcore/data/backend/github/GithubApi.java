package com.longngohoang.twitter.appcore.data.backend.github;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.longngohoang.twitter.appcore.data.backend.github.model.UserGit;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Long on 11/1/2016.
 */

public interface GithubApi {
    @RxLogObservable
    @GET("users/{user}")
    Observable<UserGit> getUser(@Path("user") String user);

    @RxLogObservable
    @GET("users/{user}/repos")
    Observable<UserGit> getRepos(@Path("user") String user);
}
