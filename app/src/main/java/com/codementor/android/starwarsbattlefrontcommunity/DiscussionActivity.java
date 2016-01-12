package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.view.CommentViewAdapter;
import com.codementor.android.starwarsbattlefrontcommunity.view.PostViewAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tonyk_000 on 1/6/2016.
 */
public class DiscussionActivity extends AppCompatActivity {

    private Post post;

    private RecyclerView mPostView;
    private PostViewAdapter mTopPost;

    private RecyclerView mCommentView;
    private CommentViewAdapter mCommentList;

    private TextView mThreadTitle;
    private TextView mAuthorName;
    private TextView mDatePosted;
    private TextView mPostContent;
    private TextView mCommentCount;
    private CircleImageView mAuthorPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Bundle b = getIntent().getExtras();
        post = b.getParcelable(MainActivity.EXTRA_POST);

//        mThreadTitle = (TextView)findViewById(R.id.thread_title);
//        mThreadTitle.setText(post.getTitle());
//        mAuthorName = (TextView)findViewById(R.id.author_name);
//        mAuthorName.setText(post.getAuthor());
//        mDatePosted = (TextView)findViewById(R.id.post_date);
//        mDatePosted.setText(post.getDate());
//        mPostContent = (TextView)findViewById(R.id.post_content);
//        mPostContent.setText(post.getContent());
//        mAuthorPhoto = (CircleImageView)findViewById(R.id.author_photo);
//        mAuthorPhoto.setImageResource(post.getAuthorPhoto());
//        mCommentCount = (TextView) findViewById(R.id.comment_count);
//        mCommentCount.setText(Integer.toString(post.getComments().size()));

//        List<Post> posts = new ArrayList<>();
//        posts.add(post);



//        mPostView = (RecyclerView)findViewById(R.id.rv_top_post_view);
//        mPostView.setLayoutManager(new LinearLayoutManager(this));
//        mTopPost = new PostViewAdapter(posts);
//        mPostView.setAdapter(mTopPost);

        mCommentView = (RecyclerView)findViewById(R.id.rv_comment_view);
        mCommentView.setLayoutManager(new LinearLayoutManager(this));
        mCommentList = new CommentViewAdapter(post.getComments(), post);
        mCommentView.setAdapter(mCommentList);
    }

    //TODO: get result of clicked post to display in the adapter
}
