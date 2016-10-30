package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.longngohoang.news.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.news.mobile.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/28/2016.
 */
public class PhotoMediaTweetHolder extends BaseViewHolder<PhotoMediaTweetVM> {
    @BindView(R.id.ivMedia)
    ImageView ivMedia;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    public PhotoMediaTweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(PhotoMediaTweetVM item) {
        if (item.media!=null&&item.media.url != null) {
            Picasso.with(itemView.getContext()).load(item.media.url).into(ivMedia);
        }

        tvInfo.setText(item.media.info);
    }
}
