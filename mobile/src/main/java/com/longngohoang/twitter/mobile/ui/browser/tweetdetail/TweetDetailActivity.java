package com.longngohoang.twitter.mobile.ui.browser.tweetdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.longngohoang.twitter.appcore.interactor.DefaultSubscriber;
import com.longngohoang.twitter.appcore.interactor.SendTweetUseCase;
import com.longngohoang.twitter.appcore.presentation.viewmodel.BaseVM;
import com.longngohoang.twitter.appcore.presentation.viewmodel.TweetVM;
import com.longngohoang.twitter.mobile.MainApplication;
import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.ui.LoginActivity;
import com.longngohoang.twitter.mobile.ui.adapter.BaseAdapter;
import com.twitter.sdk.android.Twitter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweetDetailActivity extends AppCompatActivity {
    private static final String TAG = "TweetDetailActivity";
    private static final String TWEET = "TWEET";
    @BindView(R.id.rvTweetDeTail)
    RecyclerView rvTweetDeTail;
    @OnClick(R.id.fab)
    void showDailog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Tweet");

        final TextInputEditText input = new TextInputEditText(this);
        final TextInputLayout textInputLayout = new TextInputLayout(this);
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(140);
        textInputLayout.addView(input);
        builder.setView(textInputLayout);
        builder.setPositiveButton("OK", (dialog, which) -> {

            sendTweet.execute(new DefaultSubscriber<Boolean>(){
                @Override
                public void onNext(Boolean o) {
                    super.onNext(o);
                    if (o) {
                        Toast.makeText(TweetDetailActivity.this, "Successfull!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TweetDetailActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }


                }
            },input.getText().toString(),tweetVM.id);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    TweetVM tweetVM;

    SendTweetUseCase sendTweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendTweet=new SendTweetUseCase(MainApplication.getMainComponent().schedulerProvider(),MainApplication.getMainComponent().tweetRepository());

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){

            case R.id.action_logout :
                Twitter.logOut();
                startActivity(new Intent(this, LoginActivity.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        sendTweet.unsubscribe();
        super.onDestroy();
    }
}
