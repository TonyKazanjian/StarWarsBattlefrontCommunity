package com.codementor.android.starwarsbattlefrontcommunity;

import com.codementor.android.starwarsbattlefrontcommunity.model.Topic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tonyk_000 on 3/29/2016.
 */
public interface BattlefrontClient {
    @GET("/topics")
    Call<List<Topic>> getTopics();
}
