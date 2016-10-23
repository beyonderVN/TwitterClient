package com.longngo.mobile.ui.nytimes.adapter.vmfactory;

import android.view.View;

import com.longngo.mobile.ui.nytimes.adapter.viewholder.BaseViewHolder;
import com.longngo.mobile.ui.nytimes.adapter.viewholder.DocHolder;
import com.longngo.mobile.ui.nytimes.adapter.viewholder.DocNoImageHolder;
import com.longngo.mobile.ui.nytimes.adapter.viewholder.LoadingMoreHolder;
import com.longngo.mobile.ui.nytimes.adapter.viewholder.NoMoreItemHolder;
import com.longngohoang.news.appcore.presentation.nytimes.viewmodel.DocNoImageVM;
import com.longngohoang.news.appcore.presentation.nytimes.viewmodel.DocVM;
import com.longngohoang.news.appcore.presentation.nytimes.viewmodel.LoadingMoreVM;
import com.longngohoang.news.appcore.presentation.nytimes.viewmodel.NoMoreItemVM;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {
    private static final int DOC_VM = com.longngo.moviebox.R.layout.layout_item_news;
    private static final int DOC_NO_IMAGE_VM = com.longngo.moviebox.R.layout.layout_item_news_no_image;
    private static final int LOADING_MORE = com.longngo.moviebox.R.layout.infinite_loading;
    private static final int NO_MORE = com.longngo.moviebox.R.layout.infinite_no_more;



    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case DOC_VM:
                return new DocHolder(view);
            case LOADING_MORE:
                return new LoadingMoreHolder(view);
            case NO_MORE:
                return new NoMoreItemHolder(view);
            case DOC_NO_IMAGE_VM:
                return new DocNoImageHolder(view);
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
    public int getType(DocNoImageVM docNoImageVM) {
        return DOC_NO_IMAGE_VM;
    }

    @Override
    public int getType(DocVM docVM) {
        return DOC_VM;
    }


}
