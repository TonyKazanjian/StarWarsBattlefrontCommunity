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
import com.codementor.android.starwarsbattlefrontcommunity.model.CommentResponse;
import com.codementor.android.starwarsbattlefrontcommunity.model.PostObject;
import com.codementor.android.starwarsbattlefrontcommunity.view.CommentViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends AppCompatActivity {

    private PostObject mPost;

    private RecyclerView mCommentView;
    private CommentViewAdapter mCommentList;

    private List<CommentResponse> mCommentResponses;

    public static final String EXTRA_CONTENT_TYPE_COMMENT = "comment";
    private static final int REQUEST_CODE_COMMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle b = getIntent().getExtras();
        mPost = b.getParcelable(MainActivity.EXTRA_POST);

        List<PostObject> posts = new ArrayList<>();
        posts.add(mPost);

        populateDiscussion();

        mCommentView = (RecyclerView) findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onContentAdd();
            }
        });
    }

    public List<CommentResponse> populateDiscussion(){
        BattlefrontClient client = APIServiceGenerator.createService(BattlefrontClient.class);
        Call<CommentResponse> call = client.getComments(mPost.getId());
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful()) {

                    mCommentList = new CommentViewAdapter(response.body().getComments(), mPost);
                }
                mCommentView.setAdapter(mCommentList);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });

        return mCommentResponses;
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
//            mCommentList.addComment(newComment);
        }
    }

}

