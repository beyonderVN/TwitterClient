package com.longngohoang.twitter.mobile.ui.resume.adapter.vmfactory;

import android.view.View;

import com.longngohoang.twitter.mobile.ui.resume.adapter.viewholder.BaseViewHolder;
import com.longngohoang.twitter.mobile.ui.resume.adapter.viewmodel.RepoVM;


/**
 * Created by Long on 10/5/2016.
 */

public interface ResumeHolderFactory {
    BaseViewHolder createHolder(int type, View view);
    int getType(RepoVM repoVM);
}
