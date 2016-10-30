package com.longngohoang.news.mobile.ui.browser.tweetdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.longngohoang.news.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.news.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetDetailActivity extends AppCompatActivity {
    private static final String TAG = "TweetDetailActivity";
    private static final String TWEET = "TWEET";
    @BindView(R.id.rvTweetDeTail)
    RecyclerView rvTweetDeTail;

    TweetVM tweetVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        ButterKnife.bind(this);
        setupRV();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    void setupRV() {
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        1, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();

        rvTweetDeTail.setLayoutManager(staggeredGridLayoutManagerVertical);
        rvTweetDeTail.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(3000);
        itemAnimator.setRemoveDuration(3000);
        rvTweetDeTail.setItemAnimator(itemAnimator);

        tweetVM = (TweetVM) getIntent().getSerializableExtra("TWEET");
        tweetVM.isMediaEnable=true;
        List<BaseVM> baseVMs = new ArrayList<>();
        baseVMs.add(tweetVM);
        BaseAdapter baseAdapter = new BaseAdapter(baseVMs);
        rvTweetDeTail.setAdapter(baseAdapter);
    }
}
