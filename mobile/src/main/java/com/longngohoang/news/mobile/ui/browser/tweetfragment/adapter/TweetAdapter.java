package com.longngohoang.news.mobile.ui.browser.tweetfragment.adapter;


import android.app.Activity;
import android.content.Context;

import com.longngohoang.news.appcore.presentation.BasePresentationModel;
import com.longngohoang.news.mobile.ui.adapter.BaseAdapter;

public class TweetAdapter extends BaseAdapter {


    public TweetAdapter(Activity activity, BasePresentationModel basePresentationModel) {
        super(activity, basePresentationModel);
    }
    public TweetAdapter(Context context, BasePresentationModel basePresentationModel) {
        super(context, basePresentationModel);
    }
}
