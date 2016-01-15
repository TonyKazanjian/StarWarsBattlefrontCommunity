package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.CommentViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends AppCompatActivity {

    private Post post;

    private RecyclerView mCommentView;
    private CommentViewAdapter mCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle b = getIntent().getExtras();
        post = b.getParcelable(MainActivity.EXTRA_POST);

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        mCommentView = (RecyclerView)findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this));
        mCommentList = new CommentViewAdapter(post.getComments(),post);
        mCommentView.setAdapter(mCommentList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Replies to post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
