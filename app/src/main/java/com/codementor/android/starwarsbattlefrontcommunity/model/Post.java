package com.codementor.android.starwarsbattlefrontcommunity.model;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class Post {

    private int mTitle;
    private int mAuthor;
    private int mDate;
    private int mContent;
    private int mAuthorPhoto;

    public Post(int title, int author, int date, int content, int authorPhoto) {
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mContent = content;
        mAuthorPhoto = authorPhoto;
    }

    public int getTitle() {
        return mTitle;
    }

    public void setTitle(int title) {
        mTitle = title;
    }

    public int getAuthor() {
        return mAuthor;
    }

    public void setAuthor(int author) {
        mAuthor = author;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public int getContent() {
        return mContent;
    }

    public void setContent(int content) {
        mContent = content;
    }

    public int getAuthorPhoto() {
        return mAuthorPhoto;
    }

    public void setAuthorPhoto(int authorPhoto) {
        mAuthorPhoto = authorPhoto;
    }
}
