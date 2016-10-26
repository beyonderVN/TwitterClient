package com.longngohoang.news.mobile.ui.browser;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.longngohoang.news.appcore.common.viewpager.InkPageIndicator;
import com.longngohoang.news.appcore.common.viewpager.ModelPagerAdapter;
import com.longngohoang.news.appcore.common.viewpager.PagerModelManager;
import com.longngohoang.news.appcore.common.viewpager.ScrollerViewPager;
import com.longngohoang.news.mobile.MainApplication;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.longngohoang.news.mobile.R.id.toolbar;


public class MainActivity extends BaseActivity<MainPresentationModel, MainView, MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener,MainView {
    private static final String TAG = "MainActivity";
    //bind
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
    @BindView(R.id.view_pager)
    ScrollerViewPager viewPager;
//    AllFragment fragmentMain;
//    AllFragment2 fragmentMain2;
//    CatalogueFragment fragmentSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        //hideContent();
        setupFab();


        setupDrawable();

        setupViewPage();

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();


        InkPageIndicator springIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        // just set viewPager
        springIndicator.setViewPager(viewPager);
    }
    private List<String> getTitles(){
        ArrayList<String> list = new ArrayList<String>();
        return list;
    }

    private List<String> getBgRes(){
        ArrayList<String> list = new ArrayList<>();
        list.add("https://pp.vk.me/c4247/u120721978/a_5229ede7.jpg");
        list.add("https://pp.vk.me/c4247/u120721978/a_5229ede7.jpg");
        list.add("https://pp.vk.me/c4247/u120721978/a_5229ede7.jpg");
        list.add("https://pp.vk.me/c4247/u120721978/a_5229ede7.jpg");
        return list;
    }

    private void setupFab() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    private void setupDrawable() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                boolean showTitle = (mCollapsingToolbar.getHeight() + verticalOffset) <= (mToolbar.getHeight()*3) ;
                mToolbar.setTitle(showTitle?"Twitter":"");
                mToolbar.setOverflowIcon(getDrawable(R.drawable.ic_3d_rotation_black_18dp));
            }
        });
    }

    private void setupViewPage() {
        SimpleViewPagerAdapter mAdapter = new SimpleViewPagerAdapter(getSupportFragmentManager());


//        fragmentMain = AllFragment.newInstance();
//        fragmentMain2 = AllFragment2.newInstance();
//        fragmentSub = CatalogueFragment.newInstance();
//        mAdapter.addFragment(fragmentMain, "All");
//        mAdapter.addFragment(fragmentMain2, "Dribble");
//        mAdapter.addFragment(fragmentSub, "Catalogue");
//        mAdapter.addFragment(CatalogueFragment2.newInstance(), "Catalogue2");
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
//            case R.id.action_cart :
//                presenter.resetListItemProduct();
//                Toast.makeText(this, "R.id.action_cart", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_search:
//                Toast.makeText(this, "R.id.action_search", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_favorite :
//                Toast.makeText(this, "R.id.action_favorite", Toast.LENGTH_SHORT).show();
//                return true;
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
    protected MainPresentationModel createPresentationModel() {
        return new MainPresentationModel();
    }

    //MainView
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


}
