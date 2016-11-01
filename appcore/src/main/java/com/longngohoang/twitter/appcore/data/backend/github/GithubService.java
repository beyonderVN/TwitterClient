package com.longngohoang.twitter.appcore.data.backend.github;

import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.longngohoang.twitter.appcore.data.backend.github.model.UserGit;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Long on 11/1/2016.
 */
@Singleton
public class GithubService {
    private static final String TAG = "GithubService";
    private final GithubApi githubApi;
    @Inject
    public GithubService(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @RxLogObservable
    public Observable<UserGit> getUserGit(String user) {
        Log.d(TAG, "getUserGit: ");
        return githubApi.getUser(user);
    }
    @RxLogObservable
    public Observable<UserGit> getRepos(String user) {
        Log.d(TAG, "getUserGit: ");
        return githubApi.getRepos(user);
    }
}
