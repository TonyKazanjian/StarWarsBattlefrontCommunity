package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostThread extends LinearLayout {

    private TextView mThreadTitle;
    private TextView mAuthorName;
    private TextView mDatePosted;
    private TextView mPostContent;
    private ImageView mAuthorPhoto;

    private List<Post> mPosts;

    public PostThread(Context context) {
        this(context, null);
    }

    public PostThread(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostThread(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setOrientation(LinearLayout.HORIZONTAL);
        inflate(this.getContext(), R.layout.thread_item, this);
        mThreadTitle = (TextView) findViewById(R.id.thread_title);
        mAuthorName = (TextView) findViewById(R.id.author_name);
        mDatePosted = (TextView) findViewById(R.id.post_date);
        mPostContent = (TextView) findViewById(R.id.post_content);
        mAuthorPhoto = (ImageView) findViewById(R.id.author_photo);
    }

    public List<Post> createThreads(){
        mPosts = new ArrayList<>();

        for (int i = 0; i<0; i++){
            Post post = new Post(R.id.thread_title,R.id.author_name,
                    R.id.post_date, R.id.post_content, R.id.author_photo);

            mPosts.add(post);
            post = mPosts.get(i);
        }
        return mPosts;
    }


}
