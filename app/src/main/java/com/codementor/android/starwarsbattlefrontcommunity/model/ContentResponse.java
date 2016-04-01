package com.codementor.android.starwarsbattlefrontcommunity.model;

import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public abstract class ContentResponse {

    private List<ContentObject> mContentObjects;

    public List<ContentObject> getContentObjects() {
        return mContentObjects;
    }

    public void setContentObjects(List<ContentObject> contentObjects) {
        mContentObjects = contentObjects;
    }

    /**
     * current_page : 0
     * total_pages : 1
     * total_count : 2
     * per_page : 20
     * posts : [{"title":"Check out this crazy action shot!","id":2,"topic_id":1,"created_at":"2016-03-28T23:33:31.013Z","updated_at":"2016-03-28T23:33:31.013Z","comment_count":3,"content":{"body":"This is seriously insane. Check this out!","image_urls":["http://images.eurogamer.net/2013/articles/1/7/7/1/4/1/4/star-wars-battlefront-details-team-based-blast-mode-1438302156234.jpg/EG11/thumbnail/900x450/format/jpg/1771414.jpg"]},"author":{"name":"Luke Skywalker","profile_image_url":"http://vignette1.wikia.nocookie.net/starwars/images/d/d9/Luke-rotjpromo.jpg/revision/latest?cb=20091030151422"}},{"title":"I feel bad for my son...","id":1,"topic_id":1,"created_at":"2016-03-27T23:33:31.013Z","updated_at":"2016-03-27T23:33:31.013Z","comment_count":25,"content":{"body":"I just don't know what to tell him. Should I let him know I'm his father?","image_urls":[]},"author":{"name":"Darth Vader","profile_image_url":"http://coolspotters.com/files/photos/227336/darth-vader-profile.jpg"}}]
     */

    private int current_page;
    private int total_pages;
    private int total_count;
    private int per_page;

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
