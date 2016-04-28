package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 4/1/2016.
 */

public class Comment extends Content implements Parcelable {

    public static final String EXTRA_NEW_COMMENT = "new comment";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeInt(this.post_id);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeParcelable(this.content, flags);
        dest.writeParcelable(this.author, flags);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.post_id = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.content = in.readParcelable(ContentBody.class.getClassLoader());
        this.author = in.readParcelable(Author.class.getClassLoader());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}