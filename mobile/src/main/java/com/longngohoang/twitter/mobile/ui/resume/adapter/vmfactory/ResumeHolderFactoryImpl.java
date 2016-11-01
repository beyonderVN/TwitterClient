package com.longngohoang.twitter.mobile.ui.resume.adapter.vmfactory;

import android.view.View;

import com.longngohoang.twitter.mobile.R;
import com.longngohoang.twitter.mobile.ui.resume.adapter.viewholder.BaseViewHolder;
import com.longngohoang.twitter.mobile.ui.resume.adapter.viewholder.RepoHolder;
import com.longngohoang.twitter.mobile.ui.resume.adapter.viewmodel.RepoVM;


/**
 * Created by Long on 10/5/2016.
 */

public class ResumeHolderFactoryImpl implements ResumeHolderFactory {

    private static final int REPO = R.layout.layout_item_repo;


    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case REPO:
                return new RepoHolder(view);
        }
        return null;
    }

    @Override
    public int getType(RepoVM repoVM) {
        return REPO;
    }


}
