package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.longngohoang.news.appcore.common.util.ParseRelativeDate;
import com.longngohoang.news.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.news.appcore.presentation.viewmodel.HeaderTweetVM;
import com.longngohoang.news.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.news.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.adapter.BaseAdapter;
import com.longngohoang.news.mobile.ui.browser.tweetdetail.TweetDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngohoang.news.mobile.MainApplication.mContext;

/**
 * Created by Long on 10/28/2016.
 */
public class TweetHolder extends BaseViewHolder<TweetVM> {
    @BindView(R.id.ivAvatar)
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
        if(item.isMediaEnable){
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
