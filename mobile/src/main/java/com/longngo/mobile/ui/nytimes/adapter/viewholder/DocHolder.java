package com.longngo.mobile.ui.nytimes.adapter.viewholder;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.longngohoang.news.appcore.common.DynamicHeightImageView;
import com.longngohoang.news.appcore.presentation.nytimes.viewmodel.DocVM;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 *
 */

public class DocHolder extends BaseViewHolder<DocVM> {
    private static final String TAG = "DocHolder";
    @BindView(com.longngo.moviebox.R.id.wrap)
    CardView cardView;
    @BindView(com.longngo.moviebox.R.id.ivBackground)
    DynamicHeightImageView imageView;
    @BindView(com.longngo.moviebox.R.id.tvTitle)
    TextView des;
    public DocHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(final DocVM item) {

        des.setText(item.getDoc().getSnippet());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

            imageView.setRatio((double)item.getDoc().getMultimedia().get(0).getHeight()
                    /(double)item.getDoc().getMultimedia().get(0).getWidth());
//        Glide.with(itemView.getContext())
//                .load("https://image.tmdb.org/t/p/w342"+item.getMovie().getPosterPath())
//                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
//                .into(imageView);
            Picasso.with(itemView.getContext()).load("https://static01.nyt.com/"+item.getDoc().getMultimedia().get(0).getUrl())
                    .into(imageView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =item.getDoc().getWebUrl();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.addDefaultShareMenuItem();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl((Activity) itemView.getContext(), Uri.parse(url));
            }
        });



    }
}
