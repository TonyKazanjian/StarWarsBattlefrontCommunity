package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class Comment implements Parcelable {

    private int mAuthor;
    private int mDate;
    private int mContent;
    private int mAuthorPhoto;

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

    public Comment(int author, int date, int content, int authorPhoto) {
        mAuthor = author;
        mDate = date;
        mContent = content;
        mAuthorPhoto = authorPhoto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mAuthor);
        dest.writeInt(this.mDate);
        dest.writeInt(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
    }

    protected Comment(Parcel in) {
        this.mAuthor = in.readInt();
        this.mDate = in.readInt();
        this.mContent = in.readInt();
        this.mAuthorPhoto = in.readInt();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
