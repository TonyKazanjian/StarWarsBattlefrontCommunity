package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.CommentViewAdapter;
import com.codementor.android.starwarsbattlefrontcommunity.view.PostViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends AppCompatActivity {

    private Post post;

    private RecyclerView mTopPostView;
    private PostViewAdapter mTopPost;

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

        mTopPostView = (RecyclerView)findViewById(R.id.top_post);
        mTopPostView.setLayoutManager(new LinearLayoutManager(this));
        mTopPost = new PostViewAdapter(posts);
        mTopPostView.setAdapter(mTopPost);

        mCommentView = (RecyclerView)findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this));
        mCommentList = new CommentViewAdapter(post.getComments());
        mCommentView.setAdapter(mCommentList);
    }

    //TODO: get result of clicked post to display in the adapter
}
