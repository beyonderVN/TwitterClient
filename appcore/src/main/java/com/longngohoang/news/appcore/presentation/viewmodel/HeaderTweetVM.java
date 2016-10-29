package com.longngohoang.news.appcore.presentation.viewmodel;

/**
 * Created by Long on 10/28/2016.
 */
public class HeaderTweetVM extends BaseVM {
    public String username;
    public String name;
    public String relativeTimestamp;
    public String text;

    public HeaderTweetVM(String username, String name, String relativeTimestamp,String text) {
        this.username = username;
        this.name = name;
        this.relativeTimestamp = relativeTimestamp;
        this.text = text;
    }

    public HeaderTweetVM() {

    }

    @Override
    public int getVMType(TwitterViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
