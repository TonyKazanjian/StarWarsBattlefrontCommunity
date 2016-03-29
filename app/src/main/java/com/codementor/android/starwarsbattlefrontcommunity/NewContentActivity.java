package com.codementor.android.starwarsbattlefrontcommunity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codementor.android.starwarsbattlefrontcommunity.image.PictureDialogFragment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;
import com.codementor.android.starwarsbattlefrontcommunity.utils.PictureUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewContentActivity extends AppCompatActivity implements PictureDialogFragment.InputListener {

    private EditText mContent;
    private Spinner mSpinner;
    private EditText mTitle;
    private ImageView mAttachedImage;

    private TextView mTextCounter;

    private File mPhotoFile;

    private Uri mUri;

    boolean mIsPost = false;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_SELECT_PHOTO = 0;

    //for permissions
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public boolean showPictureDialog = false;

    PictureDialogFragment mPictureDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        //getting position of currently selected topic fragment
        Bundle b = getIntent().getExtras();

        setToolbar();

        mIsPost = b.getBoolean(CommunityFragment.EXTRA_CONTENT_TYPE_POST); // if true, then new content will be a Post, else content will be a Comment
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.newContentLayout);

        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContent.requestFocus();
                    showKeyboard(mContent);
                }
            });
        }

        if (mIsPost) {
            createPostElements();
        }

        mContent = (EditText) findViewById(R.id.new_post_content);
        mAttachedImage = (ImageView) findViewById(R.id.attached_image);

        Button createContentButton = (Button) findViewById(R.id.post_button);
        mTextCounter = (TextView)findViewById(R.id.text_counter);
        mTextCounter.setText(String.valueOf(2000));

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextCounter.setText(String.valueOf(2000 - editable.toString().length()));
            }
        });

        if (createContentButton != null) {
            createContentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mIsPost) {
                        Post post = new Post();
                        post.setTitle(mTitle.getText().toString());
                        post.setTopicSection(mSpinner.getSelectedItemPosition());
                        populateContent(post);
                        Intent newPostData = new Intent();
                        newPostData.putExtra(Post.EXTRA_NEW_POST, post);
                        newPostData.putExtra(CommunityFragment.EXTRA_TOPIC_PAGE_POSITION, mSpinner.getSelectedItemPosition());
                        setResult(RESULT_OK, newPostData);
                        finish();
                    } else {
                        Comment comment = new Comment();
                        populateContent(comment);
                        Intent newCommentData = new Intent();
                        newCommentData.putExtra(Comment.EXTRA_NEW_COMMENT, comment);
                        setResult(RESULT_OK, newCommentData);
                        finish();
                    }
                }
            });
        }

        ImageButton photobutton = (ImageButton) findViewById(R.id.photo_button);
        if (photobutton != null) {
            photobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkPermissions()) {
                        PictureDialogFragment pictureDialogFragment = new PictureDialogFragment();
                        pictureDialogFragment.setListener(NewContentActivity.this);
                        pictureDialogFragment.show(getSupportFragmentManager(), PictureDialogFragment.class.getSimpleName());
                    }
                }
            });
        }
    }

    private void createPostElements(){
        mSpinner = (Spinner) findViewById(R.id.topic_dropdown);
        mTitle = (EditText) findViewById(R.id.new_post_title);

        mSpinner.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);

        //For spinner
        final List<String> topicList = new ArrayList<>();
        topicList.add(0, "Droid Run");
        topicList.add(1, "Hero Hunt");
        topicList.add(2, "Walker Assault");
        // Specify the layout to use when the list of choices appears
//            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.topic_spinner_item, topicList));
        mSpinner.setSelection(getIntent().getExtras().getInt(CommunityFragment.EXTRA_TOPIC_PAGE_POSITION));
    }

    private void populateContent(Content content) {
        if (mPhotoFile != null){
            mUri = Uri.fromFile(mPhotoFile);
            content.setContentImageUri(mUri);
        }
        content.setDate(new Date());
        content.setContent(mContent.getText().toString());
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_clear_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public static void showKeyboard(EditText editText) {
        final InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            updatePhotoView();


        } else  if (requestCode == REQUEST_SELECT_PHOTO && resultCode == Activity.RESULT_OK) {
            mUri = data.getData();

            mPhotoFile = new File(PictureUtils.getRealPathFromURI(mUri, this));

            if (mPhotoFile.exists()){
                updatePhotoView();
            } else {
                Log.i("NewContentActivity","could not load file");
            }
        }
    }

    //To load the Bitmap into the ImageView
    private void updatePhotoView(){
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mAttachedImage.setImageDrawable(null);
        } else {
            mAttachedImage.setVisibility(View.VISIBLE);
            Picasso.with(this).load(mPhotoFile)
                    .into(mAttachedImage);
        }
//        mPictureDialogFragment.isDetached();
    }

    @Override
    public void onTakePhotoSelected() {

        //implicit intent asks for the new picture to be put into the locatino saved in mPhotoFile
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            mPhotoFile = PictureUtils.createImageFile(this);
        } catch (IOException ex) {
            // Error occurred while creating the File
        }
        // Continue only if the File was successfully created
        if (mPhotoFile != null) {
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(mPhotoFile));
            startActivityForResult(captureImage, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    public void onChoosePhotoSelected() {
        Intent galleryIntent = new Intent( Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_SELECT_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("NewContentActivity", "camera and storage permission granted");
                        // process the normal flow
                        showPictureDialog = true;
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("NewContentActivity", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            showDialogOK("You will be unable to attach pictures to your posts",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                    .show();
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }
    }

    //called by the fragment after the permissions dialog disappears, so we will need to open the PictureDialog here
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (showPictureDialog){
            //resetting the boolean
            showPictureDialog = false;
            mPictureDialogFragment = new PictureDialogFragment();
            mPictureDialogFragment.setListener(NewContentActivity.this);
            mPictureDialogFragment.show(getSupportFragmentManager(), PictureDialogFragment.class.getSimpleName());
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    public boolean checkPermissions(){
        int cameraPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);
        int galleryPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermmissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED){
            listPermmissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (galleryPermission != PackageManager.PERMISSION_GRANTED) {
            listPermmissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermmissionsNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this, listPermmissionsNeeded
                    .toArray(new String[listPermmissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
