package com.codementor.android.starwarsbattlefrontcommunity.model;

import java.util.List;

/**
 * Created by tonyk_000 on 4/1/2016.
 */
public class CommentResponse extends ContentResponse {
    private List<CommentObject> comments;

    public List<CommentObject> getComments() {
        return comments;
    }

    private void setComments(List<CommentObject> comments) {
        this.comments = comments;
    }
}