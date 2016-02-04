package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class Comment extends Content implements Parcelable {


    public Comment(String author, String content, int authorPhoto) {
        mAuthor = author;
//        mDate = date;
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
        dest.writeLong(mDate != null ? mDate.getTime() : -1);
        dest.writeString(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
    }

    protected Comment(Parcel in) {
        this.mAuthor = in.readString();
        long tmpMDate = in.readLong();
        this.mDate = tmpMDate == -1 ? null : new Date(tmpMDate);
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
