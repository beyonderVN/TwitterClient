package com.longngohoang.news.appcore.data.backend.twitter.models;

public class TimelineItem {

    private final String createdAt;
    private final String text;

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    private final User user;

    public TimelineItem(String createdAt, String text, User user) {
        this.createdAt = createdAt;
        this.text = text;
        this.user = user;
    }
}
