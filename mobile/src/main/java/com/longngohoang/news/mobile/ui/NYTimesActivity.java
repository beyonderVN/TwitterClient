package com.longngohoang.news.mobile.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.longngohoang.news.appcore.common.DialogUtil;
import com.longngohoang.news.appcore.common.recyclerviewhelper.InfiniteScrollListener;
import com.longngohoang.news.appcore.data.model.SearchRequest;
import com.longngohoang.news.appcore.presentation.presentor.NYTimesPresentationModel;
import com.longngohoang.news.appcore.presentation.presentor.NYTimesPresenter;
import com.longngohoang.news.appcore.presentation.presentor.NYTimesView;
import com.longngohoang.news.mobile.MainApplication;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.base.BaseActivity;
import com.longngohoang.news.mobile.ui.adapter.BaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NYTimesActivity extends BaseActivity<NYTimesPresentationModel, NYTimesView, NYTimesPresenter>
        implements NYTimesView,DatePickerDialog.OnDateSetListener {
    private static final String TAG = "MainActivity";
    private static final int POSITION_CONTENT_VIEW = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;
    private static final int POSITION_ERROR = 2;

    @BindInt(R.integer.column_num_news)
    int columnNum;
    @BindView(R.id.rvMovieList)
    RecyclerView listRV;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.viewAnimator)
    ViewAnimator resultAnimator;
    @BindView(R.id.tvBeginDate)
    TextView tvBeginDate;
    @BindView(R.id.spnSort)
    Spinner spnSort;
    @BindView(R.id.cbArts)
    CheckBox cbArts;
    @BindView(R.id.cbFashion)
    CheckBox cbFashion;
    @BindView(R.id.cbSports)
    CheckBox cbSports;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    SearchView searchView;
    View softPanel;
    SearchRequest searchRequest;
    BaseAdapter baseAdapter;


    @OnClick(R.id.btSave)
    void saveFilter() {
        searchRequest.setBeginDate(tvBeginDate.getText().toString());
        searchRequest.setSort(spnSort.getSelectedItem().toString());
        searchRequest.getFq().clear();
        if(cbArts.isChecked())searchRequest.getFq().add(cbArts.getText().toString());
        if(cbFashion.isChecked())searchRequest.getFq().add(cbFashion.getText().toString());
        if(cbSports.isChecked())searchRequest.getFq().add(cbSports.getText().toString());
        softPanel.setVisibility(View.GONE);
    }


    @OnClick(R.id.tvBeginDate)
    void onTimePickerBtnClick() {
        DialogUtil.DatePickerFragment newFragment = new DialogUtil.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyt);
        ButterKnife.bind(this);
        setupUI();

//        if (Build.VERSION.SDK_INT >= 23) {
//
//            if (!Settings.canDrawOverlays(getApplicationContext()))
//                new AlertDialog.Builder(this)
//
//                        .setMessage("Starting from Android 6, " + getResources().getString(R.string.app_name) + " needs permission to display notifications. Click enable to proceed")
//                        .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                androidM();
//                            }
//                        })
//
//                        .show();
//
//        }


    }

    void setupUI() {
        setupRV();
        setupToolBar();
        setupSwipeRefreshLayout();
        setupStatusBar();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });

    }

    public void selectDrawerItem(MenuItem menuItem) {

    }

    private void setupStatusBar() {

    }

    private void setupSwipeRefreshLayout() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(() -> {
            listRV.setLayoutFrozen(true);
            swipeRefresh.setRefreshing(true);
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("Swipe", "Refreshing Number");
                    refresh();
                }
            }, 500);

        });
    }

    @OnClick(R.id.tvError)
    void refresh() {
        searchRequest.setPage(0);
        presenter.fetchRepositoryFirst(columnNum);
    }

    void setupToolBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Box");
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

    @Override
    protected void onStart() {
        super.onStart();
        searchRequest = presenter.getPresentationModel().getSearchRequest();
        tvBeginDate.setText(searchRequest.getBeginDate());
        presenter.fetchRepository(columnNum);

    }

    @Override
    protected void performFieldInjection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected NYTimesPresentationModel createPresentationModel() {
        return new NYTimesPresentationModel();
    }


    @Override
    public Context context() {
        return getApplicationContext();
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
        if (baseAdapter == null) {
            baseAdapter = new BaseAdapter(this, presenter.getPresentationModel());
            listRV.setAdapter(baseAdapter);
        } else {
            baseAdapter.notifyDataSetChanged();
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
        if (resultAnimator.getDisplayedChild() == POSITION_ERROR) return;
        resultAnimator.setDisplayedChild(POSITION_ERROR);
        TextView tvError = (TextView) findViewById(R.id.tvError);
        tvError.setText(s);
        swipeRefresh.setRefreshing(false);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        softPanel = findViewById(R.id.softPanel);
        if (softPanel == null) {
            Toast.makeText(this, "softPanel == null", Toast.LENGTH_SHORT).show();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRequest.setBeginDate(tvBeginDate.getText().toString());
                searchRequest.setSort(spnSort.getSelectedItem().toString());
                searchRequest.setQ(query);
                List<String> fq=new ArrayList<String>();
                if(cbArts.isChecked())fq.add(cbArts.getText().toString());
                if(cbFashion.isChecked())fq.add(cbFashion.getText().toString());
                if(cbSports.isChecked())fq.add(cbSports.getText().toString());
                searchRequest.setPage(0);
                searchRequest.setFq(fq);
                presenter.fetchRepositoryFirst(columnNum);
                searchView.clearFocus();
                softPanel.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
                searchView.requestFocus();

                return true;
            case R.id.action_sort:
                if (softPanel.getVisibility() == View.VISIBLE) {
                    softPanel.setVisibility(View.GONE);

                } else {
                    softPanel.setVisibility(View.VISIBLE);

                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    Calendar myCalendar = Calendar.getInstance();
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    }
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBeginDate.setText(sdf.format(myCalendar.getTime()));
    }
}
