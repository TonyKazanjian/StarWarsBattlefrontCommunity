package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class Comment extends Post {

    public Comment( int author, int date, int content, int authorPhoto) {
        super(0, author, date, content, authorPhoto,null);
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
