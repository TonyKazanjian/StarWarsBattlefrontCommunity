package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class Post extends Content implements Parcelable {

    public static final String EXTRA_NEW_POST = "new post";
    private int mTopicSection;

    private List<Comment> mComments = new ArrayList<>();

    @SerializedName("title")
    private String mTitle;

    @SerializedName("topic_id")
    private int mTopicId;

    @SerializedName("comment_count")
    private int mCommentCount;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getTopicId() {
        return mTopicId;
    }

    public void setTopicId(int mTopicId) {
        this.mTopicId = mTopicId;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int mCommentCount) {
        this.mCommentCount = mCommentCount;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public int getTopicSection() {
        return mTopicSection;
    }

    public void setTopicSection(int topicSection) {
        this.mTopicSection = topicSection;
    }


    public Post() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(mComments);
        dest.writeString(this.mTitle);
        dest.writeInt(this.mTopicId);
        dest.writeInt(this.mCommentCount);
    }

    protected Post(Parcel in) {
        super(in);
        this.mComments = in.createTypedArrayList(Comment.CREATOR);
        this.mTitle = in.readString();
        this.mTopicId = in.readInt();
        this.mCommentCount = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}

