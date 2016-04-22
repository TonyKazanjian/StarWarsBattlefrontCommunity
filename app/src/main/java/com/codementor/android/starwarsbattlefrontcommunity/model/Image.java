package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tonykazanjian on 4/19/16.
 */
public class Image implements Parcelable {
    String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String url) {
        this.image_url = url;
    }

    public Image() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image_url);
    }

    protected Image(Parcel in) {
        this.image_url = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}


