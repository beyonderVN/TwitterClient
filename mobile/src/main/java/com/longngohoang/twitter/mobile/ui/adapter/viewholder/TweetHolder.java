package com.longngohoang.twitter.mobile.ui.adapter.viewholder;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.longngohoang.twitter.appcore.common.util.ParseRelativeDate;
import com.longngohoang.twitter.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.HeaderTweetVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.ui.adapter.BaseAdapter;
import com.longngohoang.twitter.mobile.ui.browser.tweetdetail.TweetDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngohoang.twitter.mobile.MainApplication.mContext;

/**
 * Created by Long on 10/28/2016.
 */
public class TweetHolder extends BaseViewHolder<TweetVM> {
    private static final String TAG = "TweetHolder";
    @BindView(R.id.flCover)
    ImageView ivAvatar;
    @BindView(R.id.rvTweetRight)
    RecyclerView rvTweetRight;
    public TweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
    @Override
    public void bind(TweetVM item) {
        List<BaseVM> baseVMs = new ArrayList<>();
        Picasso.with(itemView.getContext()).load(item.user.profileImageUrl).into(ivAvatar);
        baseVMs.add(new HeaderTweetVM(item.user.screenName,item.user.name, ParseRelativeDate.getRelativeTimeAgo(item.createdAt),item.text));
        if(item.media != null&&item.media.url != null){
            Log.d(TAG, "item.media: "+item.media.type);
            baseVMs.add(new PhotoMediaTweetVM(item.media));


        }
        BaseAdapter baseAdapter = new BaseAdapter(baseVMs);
        rvTweetRight.setNestedScrollingEnabled(false);
        rvTweetRight.setHasFixedSize(true);
        rvTweetRight.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTweetRight.setAdapter(baseAdapter);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),TweetDetailActivity.class);
            intent.putExtra("TWEET",item);
            v.getContext().startActivity(intent);
        });


    }

}
