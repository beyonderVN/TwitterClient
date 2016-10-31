package com.longngohoang.twitter.mobile.ui.adapter.viewholder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 *
 */

public class NoMoreItemHolder extends BaseViewHolder{
    private static final String TAG = "NoMoreItemHolder";

    public NoMoreItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(Object item) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
    }
}
