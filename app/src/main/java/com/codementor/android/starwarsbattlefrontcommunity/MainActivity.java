package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.codementor.android.starwarsbattlefrontcommunity.model.Content;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "top post";
    CommunityFragment mCommunityFragment = new CommunityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent discussionIntent(Context packageContext, Content post){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext, DiscussionActivity.class);
        bundle.putParcelable(EXTRA_POST, post);
        intent.putExtras(bundle);
        return intent;
    }


}
