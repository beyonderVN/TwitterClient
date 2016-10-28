package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.longngohoang.news.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.news.appcore.presentation.viewmodel.HeaderTweetVM;
import com.longngohoang.news.appcore.presentation.viewmodel.PhotoMediaTweetVM;
import com.longngohoang.news.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngohoang.news.mobile.MainApplication.mContext;

/**
 * Created by Long on 10/28/2016.
 */
public class TweetHolder extends BaseViewHolder<TweetVM> {
    @BindView(R.id.rvTweetRight)
    RecyclerView rvTweetRight;
    public TweetHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
    @Override
    public void bind(TweetVM item) {
        List<BaseVM> baseVMs = new ArrayList<>();
        baseVMs.add(new HeaderTweetVM());
        baseVMs.add(new PhotoMediaTweetVM());
        BaseAdapter baseAdapter = new BaseAdapter(baseVMs);
        rvTweetRight.setNestedScrollingEnabled(false);
        rvTweetRight.setHasFixedSize(true);
        rvTweetRight.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTweetRight.setAdapter(baseAdapter);
    }

}
