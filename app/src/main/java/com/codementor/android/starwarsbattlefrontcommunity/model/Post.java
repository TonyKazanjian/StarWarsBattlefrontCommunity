package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class Post extends Content implements Parcelable {


    private List<Comment> mComments;

    public Post(String title, String author, int date, String content, int authorPhoto, List<Comment> comments) {
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mContent = content;
        mAuthorPhoto = authorPhoto;

        mComments = comments;
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
        dest.writeString(this.mTitle);
        dest.writeString(this.mAuthor);
        dest.writeInt(this.mDate);
        dest.writeString(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
        dest.writeTypedList(mComments);
    }

    protected Post(Parcel in) {
        this.mTitle = in.readString();
        this.mAuthor = in.readString();
        this.mDate = in.readInt();
        this.mContent = in.readString();
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
