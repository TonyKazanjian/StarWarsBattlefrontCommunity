package com.codementor.android.starwarsbattlefrontcommunity.image;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;
import com.codementor.android.starwarsbattlefrontcommunity.utils.PictureUtils;

/**
 * Created by tonyk_000 on 2/25/2016.
 */
public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);

        //get the extra for the fullscreen image
        Intent intent = getIntent();

        Uri imageFile = intent.getParcelableExtra(Content.FULLSCREEN_IMAGE_EXTRA);

        MediaStore.Images.Media.getContentUri(imageFile.getPath());
        //set the resource reference on an ImageView
        ImageView fullscreenImage = (ImageView)findViewById(R.id.iv_image_fullscreen);

        if (imageFile == null){
            Log.i("FullScreenImageActivity", "There's no image");
            throw new IllegalAccessError("There needs to be a valid image URI passed to this Activity");
        }

        String imageUri = getRealPathFromURI(imageFile);

        Bitmap bitmap = PictureUtils.decodeBitmapFromFile(imageUri,this);

        fullscreenImage.setImageBitmap(bitmap);
        //make sure that we are setting the activity up so that it is in immersive mode
        fullscreenImage.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        //TODO: scale the image so it takes as much space as possible, scale appropriately in landscape or portrait
//        fullscreenImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //TODO: go back to the main activity - when we go in to immersive mode, the toolbar goes away. When whe tap on the screen the toolbar comes back
        this.getSupportActionBar().hide();
    }

    //if you have a location in the MediaStore you need to get it using a cursorloader
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
