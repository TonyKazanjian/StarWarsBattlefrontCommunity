package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class PostObject extends ContentObject {

    public static final String EXTRA_NEW_POST = "new post";

    private List<CommentObject> mComments = new ArrayList<>();

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

    public List<CommentObject> getComments() {
        return mComments;
    }

    public void setComments(List<CommentObject> comments) {
        mComments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.topic_id);
        dest.writeInt(this.comment_count);
    }

    public PostObject() {
    }

    protected PostObject(Parcel in) {
        this.title = in.readString();
        this.topic_id = in.readInt();
        this.comment_count = in.readInt();
    }

    public static final Creator<PostObject> CREATOR = new Creator<PostObject>() {
        @Override
        public PostObject createFromParcel(Parcel source) {
            return new PostObject(source);
        }

        @Override
        public PostObject[] newArray(int size) {
            return new PostObject[size];
        }
    };
}

