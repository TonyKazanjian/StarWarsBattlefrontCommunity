package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class Post extends Content implements Parcelable {

    public static final String EXTRA_NEW_POST = "new post";


    private List<Comment> mComments = new ArrayList<>();

    public Post(){
    }

    public Post(String title, String author, String content, int authorPhoto, List<Comment> comments) {
        mTitle = title;
        mAuthor = author;
//        mDate = date;
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
        dest.writeLong(mDate != null ? mDate.getTime() : -1);
        dest.writeString(this.mContent);
        dest.writeInt(this.mAuthorPhoto);
        dest.writeTypedList(mComments);
    }

    protected Post(Parcel in) {
        this.mTitle = in.readString();
        this.mAuthor = in.readString();
        long tmpMDate = in.readLong();
        this.mDate = tmpMDate == -1 ? null : new Date(tmpMDate);
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
