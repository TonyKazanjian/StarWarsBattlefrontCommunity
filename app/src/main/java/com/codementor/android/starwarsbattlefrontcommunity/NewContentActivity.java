package com.codementor.android.starwarsbattlefrontcommunity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.image.PictureDialogFragment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Comment;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;
import com.codementor.android.starwarsbattlefrontcommunity.model.Post;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewContentActivity extends AppCompatActivity implements PictureDialogFragment.InputListener {

    private Toolbar mToolbar;
    private EditText mContent;
    private Spinner mSpinner;
    private EditText mTitle;
    private ImageView mAttachedImage;

    private ImageButton mPhotoButton;
    private Button mCreateContentButton;

    private TextView mTextCounter;

    private File mPhotoFile;
    private String mCurrentPhotoPath;

    private Uri mUri;

    boolean mIsPost = false;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_SELECT_PHOTO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        //getting position of currently selected topic fragment
        Bundle b = getIntent().getExtras();

        //for trying to get the include_add_content layout to move with the keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setToolbar();

        mIsPost = b.getBoolean(CommunityFragment.EXTRA_CONTENT_TYPE_POST); // if true, then new content will be a Post, else content will be a Comment

        if (mIsPost) {

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
            mSpinner.setSelection(b.getInt(CommunityFragment.EXTRA_TOPIC_PAGE_POSITION));
        }

        mContent = (EditText) findViewById(R.id.new_post_content);
        mAttachedImage = (ImageView) findViewById(R.id.attached_image);

        mCreateContentButton = (Button) findViewById(R.id.post_button);
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

        mCreateContentButton.setOnClickListener(new View.OnClickListener() {
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

        mPhotoButton = (ImageButton) findViewById(R.id.photo_button);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureDialogFragment pictureDialogFragment = new PictureDialogFragment();
                pictureDialogFragment.setListener(NewContentActivity.this);
                pictureDialogFragment.show(getSupportFragmentManager(), PictureDialogFragment.class.getSimpleName());
            }
        });
    }

    private void populateContent(Content content) {
        if (mPhotoFile != null){
            mUri = Uri.fromFile(mPhotoFile);
            content.setContentImageUri(mUri);
        }
        content.setDate(new Date());
        content.setContent(mContent.getText().toString());
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES); //this is the directory created for the application
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
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

    public void pictureIntent(){
        //implicit intent asks for the new picture to be put into the locatino saved in mPhotoFile
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        //querying PackageManager checks to see if there are any apps available that can respond to the intent
//        final boolean canTakePhoto = captureImage.resolveActivity(getPackageManager()) != null;
//        mPhotoButton.setEnabled(canTakePhoto);

//        if (canTakePhoto) {

//            mPhotoButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mPhotoFile = null;
                    try {
                        mPhotoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (mPhotoFile != null) {
                        startActivityForResult(captureImage, REQUEST_TAKE_PHOTO);
                    }
//                }
//            });
//        }
    }

    private void choosePhotoIntent(){
        Intent galleryIntent = new Intent( Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_SELECT_PHOTO);
    }

    private void setPic() {
        // Get the dimensions of the View
        mAttachedImage.setVisibility(View.VISIBLE);
        int targetW = mAttachedImage.getWidth();
        int targetH = mAttachedImage.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
    }

    //to store bitmap image as a file
    private void storeImageAsFile(Bitmap image) throws IOException {

        try {
            FileOutputStream fos = new FileOutputStream(mPhotoFile);
            image.compress(Bitmap.CompressFormat.JPEG,90,fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mAttachedImage.setImageBitmap(imageBitmap);
            // storing imageBitmap as a File, and saving its URI to a Content object
            try {
                storeImageAsFile(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setPic();

        } else  if (requestCode == REQUEST_SELECT_PHOTO && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mCurrentPhotoPath = cursor.getString(columnIndex);
            cursor.close();
            File photoFile = new File(mCurrentPhotoPath);
            if (photoFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                mAttachedImage.setImageBitmap(bitmap);
                setPic();
            }
        }
    }

    @Override
    public void onTakePhotoSelected() {
        pictureIntent();
    }

    @Override
    public void onChoosePhotoSelected() {
        choosePhotoIntent();
    }
}
