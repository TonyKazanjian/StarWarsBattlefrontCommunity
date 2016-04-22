package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/5/2016.
 */
public class Topic implements Parcelable {

    private String title;
    private int id;
    private String image_url;

    public Topic(String title, int id, String image_url) {
        this.id = id;
        this.image_url = image_url;
        this.title = title;
    }

    //leave this in for getting the post
        private ContentResponse mPost;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public ContentResponse getPost() {
        return mPost;
    }

    public void setPost(ContentResponse post) {
        mPost = post;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.image_url);
        dest.writeParcelable((Parcelable) this.mPost, flags);
    }

    public Topic() {
    }

    protected Topic(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.image_url = in.readString();
        this.mPost = in.readParcelable(Post.class.getClassLoader());
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel source) {
            return new Topic(source);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };
}
