package com.codementor.android.starwarsbattlefrontcommunity.model;

import com.codementor.android.starwarsbattlefrontcommunity.R;

/**
 * Created by tonyk_000 on 2/1/2016.
 */
public abstract class Content {

     String mTitle;
     String mAuthor;
     int mDate;
     String mContent;
     int mAuthorPhoto;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public int getAuthorPhoto() {
        return mAuthorPhoto;
    }

    public void setAuthorPhoto(int authorPhoto) {
        mAuthorPhoto = R.drawable.bb8;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
