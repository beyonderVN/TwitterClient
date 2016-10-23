package com.longngohoang.news.mobile.ui.adapter.vmfactory;

import android.view.View;

import com.longngohoang.news.appcore.presentation.viewmodel.NYTimesMViewTypeFactory;
import com.longngohoang.news.mobile.ui.adapter.viewholder.BaseViewHolder;


/**
 * Created by Long on 10/5/2016.
 */

public interface HolderFactory extends NYTimesMViewTypeFactory {
    BaseViewHolder createHolder(int type, View view);
}
