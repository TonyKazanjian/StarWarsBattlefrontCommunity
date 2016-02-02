package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class Comment extends Content implements Parcelable {


    public Comment(String author, int date, String content, int authorPhoto) {
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
        dest.writeString(this.mAuthor);
        dest.writeInt(this.mDate);
        dest.writeString(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
    }

    protected Comment(Parcel in) {
        this.mAuthor = in.readString();
        this.mDate = in.readInt();
        this.mContent = in.readString();
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
