package com.longngo.moviebox.ui.nytimes.adapter.vmfactory;

import android.view.View;

import com.longngo.moviebox.R;
import com.longngo.moviebox.ui.nytimes.adapter.viewholder.BaseViewHolder;
import com.longngo.moviebox.ui.nytimes.adapter.viewholder.DocHolder;
import com.longngo.moviebox.ui.nytimes.adapter.viewholder.LoadingMoreHolder;
import com.longngo.moviebox.ui.nytimes.adapter.viewholder.NoMoreItemHolder;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.DocVM;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.LoadingMoreVM;
import com.ngohoang.along.appcore.presentation.nytimes.viewmodel.NoMoreItemVM;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {
    private static final int DOC_VM = R.layout.layout_item_new;
    private static final int LOADING_MORE = R.layout.infinite_loading;
    private static final int NO_MORE = R.layout.infinite_no_more;



    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case DOC_VM:
                return new DocHolder(view);
            case LOADING_MORE:
                return new LoadingMoreHolder(view);
            case NO_MORE:
                return new NoMoreItemHolder(view);
        }
        return null;
    }

    @Override
    public int getType(LoadingMoreVM loadingMoreVM) {
        return LOADING_MORE;
    }

    @Override
    public int getType(NoMoreItemVM noMoreItemVM) {
        return NO_MORE;
    }

    @Override
    public int getType(DocVM docVM) {
        return DOC_VM;
    }


}
