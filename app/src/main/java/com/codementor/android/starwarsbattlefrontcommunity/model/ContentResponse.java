package com.codementor.android.starwarsbattlefrontcommunity.model;

import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public abstract class ContentResponse {

    private List<Content> mContents;

    private int current_page;
    private int total_pages;
    private int total_count;
    private int per_page;

    public List<Content> getContentObjects() {
        return mContents;
    }

    public void setContentObjects(List<Content> contents) {
        mContents = contents;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }


}
