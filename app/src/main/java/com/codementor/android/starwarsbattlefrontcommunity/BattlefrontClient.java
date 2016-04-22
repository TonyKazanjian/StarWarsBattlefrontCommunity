package com.codementor.android.starwarsbattlefrontcommunity;

import com.codementor.android.starwarsbattlefrontcommunity.model.CommentResponse;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.model.PostResponse;
import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tonyk_000 on 3/29/2016.
 */
public interface BattlefrontClient {
    @GET("/topics")
    Call<List<Topic>> getTopics();

    //getting all the posts that belong to {topic_id}
    @GET("/{topic_id}/posts")
    Call<PostResponse> getPosts(
            @Path("topic_id") int topicId
    );

    //getting the comments that belong to the [post_id}
    @GET("/{post_id}/comments")
    Call<CommentResponse> getComments(
            @Path("post_id") int postId
    );

    @POST("/{topic_id}/posts")
    Call<JsonObject> newPost(
            @Body String newPostObject
    );

}
