package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostThread extends CardView {

    private List<Post> mPosts;

    private Post mPost;

    public PostThread(Context context) {
        this(context, null);
    }

    public PostThread(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostThread(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(this.getContext(), R.layout.thread_item, this);
    }

    public List<Post> createThreads(){
        mPosts = new ArrayList<>();

        for (int i = 0; i<3; i++){
            Post post = new Post(R.id.thread_title,R.id.author_name,
                    R.id.post_date, R.id.post_content, R.id.author_photo);

            post.setTitle(R.id.thread_title);
            post.setAuthor(R.id.author_name);
            post.setDate(R.id.post_date);
            post.setContent(R.id.post_content);
            post.setAuthorPhoto(R.id.author_photo);

            mPosts.add(post);
            mPost = mPosts.get(i);
        }
        return mPosts;
    }


}
