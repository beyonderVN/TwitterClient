package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longngohoang.news.appcore.common.DynamicHeightImageView;
import com.longngohoang.news.appcore.common.recyclerviewhelper.PlaceHolderDrawableHelper;
import com.longngohoang.news.appcore.presentation.viewmodel.DocVM;
import com.longngohoang.news.mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 */

public class DocHolder extends BaseViewHolder<DocVM> {
    private static final String TAG = "DocHolder";
    @BindView(R.id.wrap)
    CardView cardView;
    @BindView(R.id.ivBackground)
    DynamicHeightImageView imageView;
    @BindView(R.id.tvTitle)
    TextView des;

    public DocHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final DocVM item) {

        des.setText(item.getDoc().getSnippet());

        imageView.setRatio((double) item.getDoc().getMultimedia().get(0).getHeight()
                / (double) item.getDoc().getMultimedia().get(0).getWidth());
        Glide.with(itemView.getContext())
                .load("https://static01.nyt.com/" + item.getDoc().getMultimedia().get(0).getUrl())
                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
                .into(imageView);
        itemView.setOnClickListener(v -> {
            String url = item.getDoc().getWebUrl();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.addDefaultShareMenuItem();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl((Activity) v.getContext(), Uri.parse(url));
        });


    }
}
