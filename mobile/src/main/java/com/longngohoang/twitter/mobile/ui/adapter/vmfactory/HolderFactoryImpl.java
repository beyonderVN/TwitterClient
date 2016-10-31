package com.longngohoang.twitter.mobile.ui.adapter.vmfactory;

import android.view.View;

import com.longngohoang.twitter.appcore.presentation.viewmodel.HeaderTweetVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.LoadingMoreVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.NoMoreItemVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.BaseViewHolder;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.HeaderTweetHolder;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.LoadingMoreHolder;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.NoMoreItemHolder;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.PhotoMediaTweetHolder;
import com.longngohoang.twitter.mobile.ui.adapter.viewholder.TweetHolder;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {

    private static final int LOADING_MORE = R.layout.infinite_loading;
    private static final int NO_MORE = R.layout.infinite_no_more;
    private static final int TWEET_VM = R.layout.layout_item_tweet;
    private static final int HEADER_TWEET_VM = R.layout.layout_header_item_tweet;

    private static final int PHOTO_MEDIA_TWEET_VM = R.layout.layout_photo_media_item_tweet;

    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {

            case LOADING_MORE:
                return new LoadingMoreHolder(view);
            case NO_MORE:
                return new NoMoreItemHolder(view);
            case TWEET_VM:
                return new TweetHolder(view);
            case HEADER_TWEET_VM:
                return new HeaderTweetHolder(view);
            case PHOTO_MEDIA_TWEET_VM:
                return new PhotoMediaTweetHolder(view);
        }
        return null;
    }

    @Override
    public int getType(TweetVM tweetVM) {
        return TWEET_VM;
    }

    @Override
    public int getType(LoadingMoreVM loadingMoreVM) {
        return LOADING_MORE;
    }

    @Override
    public int getType(NoMoreItemVM noMoreItemVM) {
        return NO_MORE;
    }

    @Override
    public int getType(HeaderTweetVM headerTweetVM) {
        return HEADER_TWEET_VM;
    }

    @Override
    public int getType(PhotoMediaTweetVM photoMediaTweetVM) {
        return PHOTO_MEDIA_TWEET_VM;
    }


}
