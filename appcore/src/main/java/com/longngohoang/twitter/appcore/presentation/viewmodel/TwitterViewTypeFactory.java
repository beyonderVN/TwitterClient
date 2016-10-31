package com.longngohoang.twitter.appcore.presentation.viewmodel;




/**
 * Created by Long on 10/5/2016.
 */

public interface TwitterViewTypeFactory {
    int getType(TweetVM tweetVM);
    int getType(LoadingMoreVM loadingMoreVM);
    int getType(NoMoreItemVM noMoreItemVM);

    int getType(HeaderTweetVM headerTweetVM);

    int getType(PhotoMediaTweetVM photoMediaTweetVM);
}
