package com.codementor.android.starwarsbattlefrontcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.codementor.android.starwarsbattlefrontcommunity.model.Post;


public class NewContentActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private Spinner mSpinner;
    private EditText mTitle;
    private EditText mContent;

    private ImageButton mCameraButton;
    private Button mPostButton;

    private Post mNewPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
//        setToolbar();

        Bundle b = getIntent().getExtras();
        mNewPost = b.getParcelable(CommunityFragment.EXTRA_NEW_POST);

        mSpinner = (Spinner)findViewById(R.id.topic_dropdown);
        mTitle = (EditText)findViewById(R.id.new_post_title);
        mContent = (EditText)findViewById(R.id.new_post_content);
        mCameraButton = (ImageButton)findViewById(R.id.camera_button);
        mPostButton = (Button)findViewById(R.id.post_button);

        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNewPost.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNewPost.setContent(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPostData = new Intent();
                newPostData.putExtra(CommunityFragment.EXTRA_NEW_POST, mNewPost);
                setResult(RESULT_OK, newPostData);
                finish();
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