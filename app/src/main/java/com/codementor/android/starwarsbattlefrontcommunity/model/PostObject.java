package com.codementor.android.starwarsbattlefrontcommunity.model;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class PostObject extends ContentObject {

    private String title;
    private int topic_id;
    private int comment_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

}

