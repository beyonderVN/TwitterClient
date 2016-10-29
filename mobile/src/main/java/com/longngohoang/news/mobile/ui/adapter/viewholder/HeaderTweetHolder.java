package com.longngohoang.news.mobile.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.longngohoang.news.appcore.presentation.viewmodel.HeaderTweetVM;
import com.longngohoang.news.mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/28/2016.
 */
public class HeaderTweetHolder extends BaseViewHolder<HeaderTweetVM>  {
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvRelativeTimestamp)
    TextView tvRelativeTimestamp;
    @BindView(R.id.tvText)
    TextView tvText;
    public HeaderTweetHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(HeaderTweetVM item) {
        tvUserName.setText(itemView.getResources().getString(R.string._64_name,item.username) );
        tvName.setText(item.name);
        tvRelativeTimestamp.setText(item.relativeTimestamp);
        tvText.setText(item.text);

    }
}
