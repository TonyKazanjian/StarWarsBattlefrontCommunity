package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonyk_000 on 1/5/2016.
 */
public class Topic implements Parcelable {
    /**
     * title : Walker Assault
     * id : 1
     * image_url : http://cdn.christianitydaily.com/data/images/full/3656/star-wars-battlefront.jpg
     */

    private String title;
    private int id;
    private String image_url;

    public Topic(String title, int id, String image_url) {
        this.id = id;
        this.image_url = image_url;
        this.title = title;
    }

    //leave this in for getting the post
        private Post mPost;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public Post getPost() {
        return mPost;
    }

    public void setPost(Post post) {
        mPost = post;
    }



//    private String mTopicTitle;
//    private int mBackgroundImage;
//    private Post mPost;
//
//    public String getTopicTitle() {
//        return mTopicTitle;
//    }
//
//    public void setTopicTitle(String title) {
//        mTopicTitle = title;
//    }
//
//    public int getBackgroundImage() {
//        return mBackgroundImage;
//    }
//
//    public void setBackgroundImage(int backgroundImage) {
//        mBackgroundImage = backgroundImage;
//    }
//
//    public Post getPost() {
//        return mPost;
//    }
//
//    public void setPost(Post post) {
//        mPost = post;
//    }
//
//    public Topic(String title, int backgroundImage) {
//        mTopicTitle = title;
//        mBackgroundImage = backgroundImage;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.mTopicTitle);
//        dest.writeInt(this.mBackgroundImage);
//        dest.writeParcelable(this.mPost, 0);
//    }
//
//    protected Topic(Parcel in) {
//        this.mTopicTitle = in.readString();
//        this.mBackgroundImage = in.readInt();
//        this.mPost = in.readParcelable(Post.class.getClassLoader());
//    }
//
//    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
//        public Topic createFromParcel(Parcel source) {
//            return new Topic(source);
//        }
//
//        public Topic[] newArray(int size) {
//            return new Topic[size];
//        }
//    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.image_url);
        dest.writeParcelable(this.mPost, flags);
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
