package com.codementor.android.starwarsbattlefrontcommunity.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

/**
 * Created by tonyk_000 on 12/14/2015.
 */
public class PostThreadView extends CardView {

    private TextView mThreadTitle;
    private TextView mAuthorName;
    private TextView mDatePosted;
    private TextView mPostContent;
    private ImageView mAuthorPhoto;

    public PostThreadView(Context context) {
        this(context, null);
    }

    public PostThreadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostThreadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(this.getContext(), R.layout.thread_item, this);

        mThreadTitle = (TextView) findViewById(R.id.thread_title);
        mAuthorName = (TextView) findViewById(R.id.author_name);
        mDatePosted = (TextView) findViewById(R.id.post_date);
        mPostContent = (TextView) findViewById(R.id.post_content);
        mAuthorPhoto = (ImageView) findViewById(R.id.author_photo);
    }

    public void populateView(Post post){

        mThreadTitle.setText(post.getTitle());
        mAuthorName.setText(post.getAuthor());
        mDatePosted.setText(post.getDate());
        mPostContent.setText(post.getContent());
        mAuthorPhoto.setImageResource(post.getAuthorPhoto());
    }


}
