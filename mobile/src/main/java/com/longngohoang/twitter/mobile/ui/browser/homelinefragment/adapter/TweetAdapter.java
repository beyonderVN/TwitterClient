package com.longngohoang.twitter.mobile.ui.browser.homelinefragment.adapter;


import android.app.Activity;
import android.content.Context;

import com.longngohoang.twitter.appcore.presentation.BasePresentationModel;
import com.longngohoang.twitter.mobile.ui.adapter.BaseAdapter;

public class TweetAdapter extends BaseAdapter {


    public TweetAdapter(Activity activity, BasePresentationModel basePresentationModel) {
        super(activity, basePresentationModel);
    }
    public TweetAdapter(Context context, BasePresentationModel basePresentationModel) {
        super(context, basePresentationModel);
    }
}
