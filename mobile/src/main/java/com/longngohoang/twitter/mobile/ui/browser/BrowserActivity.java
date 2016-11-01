package com.longngohoang.twitter.mobile.ui.browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.longngohoang.twitter.mobile.MainApplication;
import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.ui.LoginActivity;
import com.longngohoang.twitter.mobile.ui.base.BaseActivity;
import com.longngohoang.twitter.mobile.ui.browser.homelinefragment.HomeLineFragment;
import com.twitter.sdk.android.Twitter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.longngohoang.twitter.mobile.R.id.toolbar;


public class BrowserActivity extends BaseActivity<BrowserPresentationModel, BrowserView, BrowserPresenter>
        implements NavigationView.OnNavigationItemSelectedListener,BrowserView {
    private static final String TAG = "BrowserActivity";

    @BindView(R.id.appbar)
    AppBarLayout mAppBar;
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Twitter.getSessionManager().getActiveSession().getAuthToken()==null) {
            Twitter.logOut();
            Intent intent   = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupFab();

        setupDrawable();

        setupViewPage();


    }


    private void setupFab() {

    }

    private void setupDrawable() {
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        setupToolbar(mDrawer);
    }
    ActionBarDrawerToggle toggle;
    private void setupToolbar(DrawerLayout drawer) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        frameLayout = (FrameLayout) findViewById(R.id.flCover);

        mAppBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            boolean showTitle = (mCollapsingToolbar.getHeight() + verticalOffset) <= (mToolbar.getHeight()*2) ;
            Log.d(TAG, "verticalOffset: "+verticalOffset);
            frameLayout.setPadding(-verticalOffset/10,-verticalOffset/10,-verticalOffset/10,-verticalOffset/10);
            if(showTitle){
                mCollapsingToolbar.setTitle("Twitter");
            }else {
                mCollapsingToolbar.setTitle("");
            }

        });
    }
    FrameLayout frameLayout;
    private void setupViewPage() {
        SimpleViewPagerAdapter mAdapter = new SimpleViewPagerAdapter(getSupportFragmentManager());


        HomeLineFragment tweetFragment = HomeLineFragment.newInstance();
        mAdapter.addFragment(tweetFragment, "Tweet");
        mAdapter.addFragment(HomeLineFragment.newInstance(), "phuong tien");
        mAdapter.addFragment(HomeLineFragment.newInstance(), "luot thich");
        mViewpager.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mViewpager);
    }


    class SimpleViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SimpleViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                Intent intent   = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //
    @Override
    protected void performFieldInjection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected BrowserPresentationModel createPresentationModel() {
        return new BrowserPresentationModel();
    }

    //BrowserView
    @Override
    public void showProgress() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
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
        builder.setPositiveButton("OK", (dialog, which) -> presenter.sendTweet(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
