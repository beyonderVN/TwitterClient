package com.longngohoang.news.appcore.data.source.realm;

import android.util.Log;

import com.longngohoang.news.appcore.data.model.TweetDM;
import com.longngohoang.news.appcore.data.source.realm.realmobject.TweetEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Admin on 30/10/2016.
 */

public class TweetRealmDataSource {
    private static final String TAG = "TweetRealmDataSource";

    public void store(List<TweetDM> tweetDMs) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> {

//            realm1
//                    .where(TweetEntity.class)
//                    .findAll()
//                    .deleteAllFromRealm();
            for (TweetDM tweetDM : tweetDMs
                    ) {

                if (realm1.where(TweetEntity.class)
                        .equalTo("id", tweetDM.id).count()==0) {
                    Log.d(TAG, "store: "+tweetDM.id);
                    TweetEntity tweetEntity = realm1.createObject(TweetEntity.class);
                    tweetEntity.map(tweetDM, realm1);
                }
            }
        });
        Log.d(TAG, "store: " +realm
                .where(TweetEntity.class)
                .findAll().size());
        realm.close();
    }

    public Observable<List<TweetDM>> getHomeTimeLine(Long maxId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TweetEntity> tweetEntities;
        if (maxId == null) {
           tweetEntities = realm
                    .where(TweetEntity.class)
                    .findAll();
        }else{
            tweetEntities = realm
                    .where(TweetEntity.class)
                    .lessThan("id",maxId)
                    .findAll();
        }

        List<TweetDM> tweetDMs = new ArrayList<>();
        for (TweetEntity tweetEntity : tweetEntities
                ) {
            tweetDMs.add(new TweetDM(tweetEntity));
        }
        Log.d(TAG, "getHomeTimeLine: " + tweetDMs.size());
        realm.close();
        return Observable.create(new Observable.OnSubscribe<List<TweetDM>>() {
            @Override
            public void call(Subscriber<? super List<TweetDM>> subscriber) {
                subscriber.onNext(tweetDMs);
            }
        });
    }
}
