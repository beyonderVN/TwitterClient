package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.view.View;
import android.widget.TextView;

import com.longngohoang.news.appcore.presentation.viewmodel.DocNoImageVM;
import com.longngohoang.news.mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 *
 */

public class DocNoImageHolder extends BaseViewHolder<DocNoImageVM> {
    private static final String TAG = "DocHolder";
    @BindView(R.id.tvTitle)
    TextView des;
    public DocNoImageHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(final DocNoImageVM item) {

        des.setText(item.getDoc().getSnippet());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =item.getDoc().getWebUrl();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.addDefaultShareMenuItem();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl((Activity) itemView.getContext(), Uri.parse(url));
            }
        });

    }
}
