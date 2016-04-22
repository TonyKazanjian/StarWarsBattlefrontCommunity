package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tonykazanjian on 4/19/16.
 */
public class Author implements Parcelable {

    @SerializedName("name")
    private String mName;
    @SerializedName("profile_image_url")
    private String mProfileImageUrl;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getProfile_image_url() {
        return mProfileImageUrl;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.mProfileImageUrl = profile_image_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mProfileImageUrl);
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.mName = in.readString();
        this.mProfileImageUrl = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
