package com.longngohoang.twitter.appcore.presentation.viewmodel.mapper;


import com.longngohoang.twitter.appcore.data.model.TweetDM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.TweetVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 10/5/2016.
 */

public class Mapper {
    private static final String TAG = "Mapper";

    public static List<BaseVM> tranToTweetVMs(List<TweetDM> tweetList){
        List<BaseVM> list = new ArrayList<>();

        for (TweetDM tweet :tweetList) {
            list.add(tranToTweetVM(tweet));
        }
        return list;
    }

    private static BaseVM tranToTweetVM(TweetDM tweet) {
        return new TweetVM(tweet);
    }


}
