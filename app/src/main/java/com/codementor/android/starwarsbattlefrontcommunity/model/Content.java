package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.IOException;
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

     Uri mContentImageUri;

    public static final String FULLSCREEN_IMAGE_EXTRA = "fullscreenImage";

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

    public Uri getContentImageUri() {
        return mContentImageUri;
    }

    public void setContentImageUri(Uri contentImageUri) {
        this.mContentImageUri = contentImageUri;
    }

    // TODO create helper method that converts the mFileUri into a Bitmap
    public Bitmap getContentImageFromFileSystem(ContentResolver contentResolver) {
        Bitmap bitmap = null;
        try {
            if (mContentImageUri != null) {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mContentImageUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
