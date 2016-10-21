package com.longngo.moviebox.ui.nytimes.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.longngo.moviebox.R;
import com.ngohoang.along.appcore.common.DynamicHeightImageView;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.DocVM;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngo.moviebox.R.id.wrap;

/**
 * Created by Long on 10/5/2016.
 *
 */

public class DocHolder extends BaseViewHolder<DocVM> {
    private static final String TAG = "DocHolder";
    @BindView(wrap)
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
    public  void bind(DocVM item) {

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




    }
}
