package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.util.ArrayList;
import java.util.List;


public class NewContentActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private EditText mContent;
    private Spinner mSpinner;
    private EditText mTitle;

    private ImageButton mCameraButton;
    private Button mCreateContentButton;

    boolean mIsPost = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        Bundle b = getIntent().getExtras(); //getting position of currently selected topic fragment
        setToolbar();

        mIsPost = b.getBoolean(CommunityFragment.EXTRA_CONTENT_TYPE_POST); // if true, then new content will be a Post, else content will be a Comment

        if (mIsPost){
            mSpinner = (Spinner)findViewById(R.id.topic_dropdown);
            mTitle = (EditText)findViewById(R.id.new_post_title);

            mSpinner.setVisibility(View.VISIBLE);
            mTitle.setVisibility(View.VISIBLE);

            //For spinner
            final List<String> topicList = new ArrayList<>();
            topicList.add(0,"Droid Run");
            topicList.add(1,"Hero Hunt");
            topicList.add(2, "Walker Assault");
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, topicList);
            // Specify the layout to use when the list of choices appears
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            mSpinner.setAdapter(spinnerAdapter);
            mSpinner.setSelection(b.getInt(CommunityFragment.EXTRA_TOPIC_PAGE_POSITION));
        }

        mContent = (EditText)findViewById(R.id.new_post_content);
        mCameraButton = (ImageButton)findViewById(R.id.camera_button);
        mCreateContentButton = (Button)findViewById(R.id.post_button);


        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCreateContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mIsPost){
                    Post newPost = new Post();
                    newPost.setTitle(mTitle.getText().toString());
                    Intent newPostData = new Intent();
                    newPostData.putExtra(Post.EXTRA_NEW_POST, newPost);
                    newPostData.putExtra(CommunityFragment.EXTRA_TOPIC_PAGE_POSITION, mSpinner.getSelectedItemPosition());
                    setResult(RESULT_OK, newPostData);
                    finish();
                } else {
                    Comment newComment = new Comment();
                    newComment.setContent(mContent.getText().toString());
                    Intent newCommentData = new Intent();
                    newCommentData.putExtra(Comment.EXTRA_NEW_COMMENT, newComment);
                    setResult(RESULT_OK, newCommentData);
                    finish();
                }
            }
        });
    }


    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {

            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_clear_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }
}
