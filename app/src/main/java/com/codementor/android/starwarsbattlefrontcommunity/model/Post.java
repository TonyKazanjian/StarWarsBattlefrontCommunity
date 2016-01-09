package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class Post implements Parcelable {

    private int mTitle;
    private int mAuthor;
    private int mDate;
    private int mContent;
    private int mAuthorPhoto;

    private List<Comment> mComments;

    public Post(int title, int author, int date, int content, int authorPhoto, List<Comment> comments) {
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mContent = content;
        mAuthorPhoto = authorPhoto;

        mComments = comments;
    }

    public int getTitle() {
        return mTitle;
    }

    public void setTitle(int title) {
        mTitle = title;
    }

    public int getAuthor() {
        return mAuthor;
    }

    public void setAuthor(int author) {
        mAuthor = author;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public int getContent() {
        return mContent;
    }

    public void setContent(int content) {
        mContent = content;
    }

    public int getAuthorPhoto() {
        return mAuthorPhoto;
    }

    public void setAuthorPhoto(int authorPhoto) {
        mAuthorPhoto = authorPhoto;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTitle);
        dest.writeInt(this.mAuthor);
        dest.writeInt(this.mDate);
        dest.writeInt(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
        dest.writeTypedList(mComments);
    }

    protected Post(Parcel in) {
        this.mTitle = in.readInt();
        this.mAuthor = in.readInt();
        this.mDate = in.readInt();
        this.mContent = in.readInt();
        this.mAuthorPhoto = in.readInt();
        this.mComments = in.createTypedArrayList(Comment.CREATOR);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
