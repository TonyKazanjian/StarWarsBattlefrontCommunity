package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class MainActivity extends FragmentActivity {

    public static final String EXTRA_POST = "top post";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent newIntent(Context packageContext, Post post){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext, DiscussionActivity.class);
        bundle.putParcelable(EXTRA_POST, post);
        intent.putExtras(bundle);
        return intent;
    }

}
