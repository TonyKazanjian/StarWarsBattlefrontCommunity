package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tonyk_000 on 2/1/2016.
 */
public abstract class Content implements Parcelable {

     String mTitle;
     String mAuthor;
     Date mDate;
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
        mAuthorPhoto = authorPhoto;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {

        mDate = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}