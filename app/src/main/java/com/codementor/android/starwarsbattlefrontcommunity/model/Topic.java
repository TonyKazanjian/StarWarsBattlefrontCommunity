package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/5/2016.
 */
public class Topic implements Parcelable {

    private String mTopicTitle;
    private int mBackgroundImage;
    private Post mPost;

    public String getTopicTitle() {
        return mTopicTitle;
    }

    public void setTopicTitle(String title) {
        mTopicTitle = title;
    }

    public int getBackgroundImage() {
        return mBackgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        mBackgroundImage = backgroundImage;
    }

    public Post getPost() {
        return mPost;
    }

    public void setPost(Post post) {
        mPost = post;
//        post.setDate(R.id.post_date);
//        post.setTitle(R.id.thread_title);
//        post.setContent(R.id.post_content);
//        post.setAuthor(R.id.author_name);
//        post.setAuthorPhoto(R.id.author_photo);
    }

    public Topic(String title, int backgroundImage) {
        mTopicTitle = title;
        mBackgroundImage = backgroundImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTopicTitle);
        dest.writeInt(this.mBackgroundImage);
        dest.writeParcelable(this.mPost, 0);
    }

    protected Topic(Parcel in) {
        this.mTopicTitle = in.readString();
        this.mBackgroundImage = in.readInt();
        this.mPost = in.readParcelable(Post.class.getClassLoader());
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        public Topic createFromParcel(Parcel source) {
            return new Topic(source);
        }

        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };
}
