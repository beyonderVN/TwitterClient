package com.longngohoang.twitter.mobile.ui.adapter.viewholder;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

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
    private static final String TAG = "PhotoMediaTweetHolder";
    @BindView(R.id.ivMedia)
    DynamicHeightImageView ivMedia;
    @BindView(R.id.ivPlayBtn)
    ImageView ivPlayBtn;
    @BindView(R.id.vvTweetVideo)
    VideoView vvTweetVideo;

    public PhotoMediaTweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(PhotoMediaTweetVM item) {
        if (item.media!=null&&item.media.url != null) {
            Picasso.with(itemView.getContext()).load(item.media.url).into(ivMedia);
            switch (item.media.type){
                case "photo" : ivPlayBtn.setVisibility(View.GONE);
                    break;
                case "video" : ivPlayBtn.setVisibility(View.GONE);
                    ivMedia.setVisibility(View.GONE);
                    vvTweetVideo.setVisibility(View.VISIBLE);
                    Log.d(TAG, "item.media.url: "+item.media.url);
                    String link="https://pbs.twimg.com/tweet_video/Cwny-k4XAAI8ag_.mp4";
                    MediaController mediaController = new MediaController(itemView.getContext());
                    mediaController.setAnchorView(vvTweetVideo);
                    Uri video = Uri.parse(link);
                    vvTweetVideo.setMediaController(mediaController);
                    vvTweetVideo.setVideoURI(video);
                    vvTweetVideo.start();
                    break;
                default: break;
            }
        }

    }
}
