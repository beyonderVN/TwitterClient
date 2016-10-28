package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.view.View;

import com.longngohoang.news.appcore.presentation.viewmodel.HeaderTweetVM;

import butterknife.ButterKnife;

/**
 * Created by Long on 10/28/2016.
 */
public class HeaderTweetHolder extends BaseViewHolder<HeaderTweetVM>  {

    public HeaderTweetHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(HeaderTweetVM item) {

    }
}
