package com.codementor.android.starwarsbattlefrontcommunity.model;

import java.util.List;

/**
 * Created by tonyk_000 on 3/31/2016.
 */
public class PostResponse extends ContentResponse {

    private List<PostObject> posts;

    public List<PostObject> getPosts() {
        return posts;
    }

    public void setPosts(List<PostObject> posts) {
        this.posts = posts;
    }
}
