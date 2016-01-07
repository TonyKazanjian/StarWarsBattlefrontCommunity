package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.ThreadViewAdapter;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends AppCompatActivity {

    private Post mPost;

    private RecyclerView mTopPostView;
    private ThreadViewAdapter mTopPost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle b = getIntent().getExtras();
        mPost = b.getParcelable(MainActivity.EXTRA_POST);

        mTopPostView = (RecyclerView)findViewById(R.id.rv_comment_view);
        mTopPostView.setLayoutManager(new LinearLayoutManager(this));
        mTopPost = new ThreadViewAdapter(mPost);
        mPost = mTopPost.getItem();
        mTopPostView.setAdapter(mTopPost);
    }

    //TODO: get result of clicked post to display in the adapter
}
