package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends FragmentActivity {

    public static final String EXTRA_POST = "top post";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
//        Bundle b = getIntent().getExtras();
//        post = b.getParcelable(MainActivity.EXTRA_POST);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.discussion_activity);

        if (fragment == null){
            fragment = new DiscussionFragment();
            fm.beginTransaction()
                    .add(R.id.discussion_host,fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Replies to post", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent newIntent(Context packageContext, Post post){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext, DiscussionActivity.class);
        bundle.putParcelable(EXTRA_POST, post);
        intent.putExtras(bundle);
        return intent;
    }
}
