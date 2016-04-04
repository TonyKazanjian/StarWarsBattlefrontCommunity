package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class PostObject extends ContentObject implements Parcelable {

    public static final String EXTRA_NEW_POST = "new post";

    private List<CommentObject> mComments = new ArrayList<>();

    private String title;
    private int topic_id;
    private int comment_count;

    /**
     * body : Haha, nope. Sorry to break it to you, Han.
     * image : []
     */

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
        super.writeToParcel(dest, flags);
        dest.writeTypedList(mComments);
        dest.writeString(this.title);
        dest.writeInt(this.topic_id);
        dest.writeInt(this.comment_count);
        dest.writeInt(this.id);
        dest.writeInt(this.post_id);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeParcelable(this.content, flags);
        dest.writeParcelable(this.author, flags);
//        dest.writeTypedArray(this.image_urls, flags);
    }

    public PostObject() {
    }

    protected PostObject(Parcel in) {
        super(in);
        this.mComments = in.createTypedArrayList(CommentObject.CREATOR);
        this.title = in.readString();
        this.topic_id = in.readInt();
        this.comment_count = in.readInt();
        this.id = in.readInt();
        this.post_id = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.content = in.readParcelable(ContentEntity.class.getClassLoader());
        this.author = in.readParcelable(AuthorEntity.class.getClassLoader());
//        this.image_urls = in.createTypedArray(ContentEntity.Image.CREATOR);
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

