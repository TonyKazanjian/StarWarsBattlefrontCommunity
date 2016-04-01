package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

import com.codementor.android.starwarsbattlefrontcommunity.model.PostObject;

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

    public static Intent discussionIntent(Context packageContext, PostObject post){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext, DiscussionActivity.class);
        bundle.putParcelable(EXTRA_POST, (Parcelable) post);
        intent.putExtras(bundle);
        return intent;
    }


}
