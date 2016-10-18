package com.longngo.moviebox.ui.nytimes.adapter.vmfactory;

import android.view.View;

import com.longngo.moviebox.ui.nytimes.adapter.viewholder.BaseViewHolder;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.NYTimesMViewTypeFactory;


/**
 * Created by Long on 10/5/2016.
 */

public interface HolderFactory extends NYTimesMViewTypeFactory {
    BaseViewHolder createHolder(int type, View view);
}
