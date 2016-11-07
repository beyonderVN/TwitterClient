package com.longngohoang.twitter.mobile.ui.browser.newhomelinefragment;

import com.longngohoang.twitter.mobile.MainApplication;
import com.longngohoang.twitter.mobile.ui.base.defaultfragment.DefaultFragment;
import com.longngohoang.twitter.mobile.ui.browser.homelinefragment.HomeLineFragment;

/**
 * Created by Long on 11/7/2016.
 */

public class Newhomelinefragment extends DefaultFragment {

    public static Newhomelinefragment newInstance() {
        return new Newhomelinefragment();
    }

    public Newhomelinefragment() {

    }

    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }
}
