package com.codementor.android.starwarsbattlefrontcommunity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonyk_000 on 3/29/2016.
 * This is our API/HTTP client heart. It defines methods to create basic REST adapters for a given class/interface
 */
public class APIServiceGenerator {

    public static final String API_BASE_URL = "http://private-anon-b89e9fd3b-battlefront.apiary-mock.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    //defines annotated class or interface for API requests
    public static <S> S createService(Class<S> serviceClass){
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
