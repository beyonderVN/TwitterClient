package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.view.View;

import com.longngohoang.news.appcore.presentation.viewmodel.PhotoMediaTweetVM;

import butterknife.ButterKnife;

/**
 * Created by Long on 10/28/2016.
 */
public class PhotoMediaTweetHolder extends BaseViewHolder<PhotoMediaTweetVM> {
    public PhotoMediaTweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(PhotoMediaTweetVM item) {

    }
}
