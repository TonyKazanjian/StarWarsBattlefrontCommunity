package com.codementor.android.starwarsbattlefrontcommunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
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

    private Comment mComment;

    public static final String EXTRA_CONTENT_TYPE_COMMENT = "comment";
    private static final int REQUEST_CODE_COMMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle b = getIntent().getExtras();
        post = b.getParcelable(MainActivity.EXTRA_POST);

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        mCommentView = (RecyclerView) findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this));
        mCommentList = new CommentViewAdapter(post.getComments(),post);
        mCommentView.setAdapter(mCommentList);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContentAdd();
            }
        });
    }

    public void onContentAdd(){
        Bundle b = new Bundle();
        Intent i = new Intent(this, NewContentActivity.class);
        b.putBoolean(EXTRA_CONTENT_TYPE_COMMENT, false);
        i.putExtras(b);
        startActivityForResult(i, REQUEST_CODE_COMMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CODE_COMMENT){
            if (data == null){
                return;
            }

            Bundle extras = data.getExtras();
            if(extras == null) {
                return;
            }

            Comment newComment = extras.getParcelable(Comment.EXTRA_NEW_COMMENT);

            newComment.setAuthorPhoto(R.drawable.bb8);
            newComment.setAuthor("AndroidPadawan");
            mCommentList.addComment(newComment);
        }
    }

}

