package com.longngohoang.news.mobile.ui.browser.homelinefragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.longngohoang.news.appcore.common.recyclerviewhelper.InfiniteScrollListener;
import com.longngohoang.news.mobile.MainApplication;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.base.BaseFragment;
import com.longngohoang.news.mobile.ui.browser.homelinefragment.adapter.TweetAdapter;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeLineFragment extends BaseFragment<HomeLinePresentationModel, HomeLineView, HomeLinePresenter>
        implements HomeLineView {
    private static final String TAG = "HomeLineFragment";
    private static final int POSITION_CONTENT_VIEW = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;

    private static final String ARG_POSITION = "position";
    View view;
    @BindInt(R.integer.column_num_news)
    int columnNum;
    @BindView(R.id.rvTweetList)
    RecyclerView listRV;
    @BindView(R.id.srRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.vaStateControl)
    ViewAnimator resultAnimator;
    private TweetAdapter tweetAdapter;


    public static HomeLineFragment newInstance(int position) {
        HomeLineFragment f = new HomeLineFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public static HomeLineFragment newInstance() {
        return new HomeLineFragment();
    }

    public HomeLineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tweet, container, false);
            ButterKnife.bind(this, view);
            setupUI();
        }

        return view;

    }
    void setupUI() {
        setupRV();
        setupSwipeRefreshLayout();
    }
    void setupRV() {
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();

        listRV.setLayoutManager(staggeredGridLayoutManagerVertical);
        listRV.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(3000);
        itemAnimator.setRemoveDuration(3000);
        listRV.setItemAnimator(itemAnimator);


        listRV.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                Log.d(TAG, "onLoadMore: ");
                try {
                    presenter.fetchMore();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public boolean isLoading() {
                return presenter.getPresentationModel().isLoadingMore();
            }

            @Override
            public boolean isNoMore() {
                return presenter.getPresentationModel().isNoMore();
            }
        });

    }
    private void setupSwipeRefreshLayout() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(() -> {
            listRV.setLayoutFrozen(true);
            swipeRefresh.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                Log.d("Swipe", "Refreshing Number");
                refresh();
            }, 500);

        });
    }
    void refresh() {
        presenter.fetchRepositoryFirst(columnNum);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        presenter.fetchRepository(columnNum);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected HomeLinePresentationModel createPresentationModel() {
        return new HomeLinePresentationModel();
    }

    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        super.onDestroyView();
    }


    @Override
    public void showProcess() {
        if (resultAnimator.getDisplayedChild() == POSITION_PROGRESS_VIEW) return;
        resultAnimator.setDisplayedChild(POSITION_PROGRESS_VIEW);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showContent() {
        if (resultAnimator.getDisplayedChild() == POSITION_CONTENT_VIEW) return;
        resultAnimator.setDisplayedChild(POSITION_CONTENT_VIEW);
    }

    @Override
    public void updateView() {

        if (tweetAdapter == null) {
            tweetAdapter = new TweetAdapter(getContext(), presenter.getPresentationModel());
            listRV.setAdapter(tweetAdapter);
        } else {
            tweetAdapter.notifyDataSetChanged();
        }

        listRV.setLayoutFrozen(false);
        swipeRefresh.setRefreshing(false);
        showContent();
//        AchievementUnlocked test=
//                new AchievementUnlocked(this)
//                        .setTitle("Lilac and Gooseberries")
//                        .setSubtitleColor(0x80ffffff)
//                        .setSubTitle("Find the sorceress")
//                        .setBackgroundColor(Color.parseColor("#C2185B"))
//                        .setTitleColor(0xffffffff)
//                        .setIcon(getDrawable(R.drawable.ic_android_white_24dp)).isLarge(false).build();
//        test.show();
    }

    @Override
    public void showError(String s) {
//        if (resultAnimator.getDisplayedChild() == POSITION_ERROR) return;
//        resultAnimator.setDisplayedChild(POSITION_ERROR);
//        TextView tvError = (TextView) findViewById(R.id.tvError);
//        tvError.setText(s);
//        swipeRefresh.setRefreshing(false);
        Toast.makeText(getContext(), "Error: "+s , Toast.LENGTH_SHORT).show();
    }


}
