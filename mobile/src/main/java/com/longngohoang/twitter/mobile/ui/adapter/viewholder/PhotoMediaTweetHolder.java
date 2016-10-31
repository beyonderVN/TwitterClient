package com.longngohoang.twitter.mobile.ui.adapter.viewholder;

import android.view.View;

import com.longngohoang.twitter.appcore.common.DynamicHeightImageView;
import com.longngohoang.twitter.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.twitter.mobile.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/28/2016.
 */
public class PhotoMediaTweetHolder extends BaseViewHolder<PhotoMediaTweetVM> {
    @BindView(R.id.ivMedia)
    DynamicHeightImageView ivMedia;

    public PhotoMediaTweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(PhotoMediaTweetVM item) {
        if (item.media!=null&&item.media.url != null) {
            Picasso.with(itemView.getContext()).load(item.media.url).into(ivMedia);
        }

    }
}
