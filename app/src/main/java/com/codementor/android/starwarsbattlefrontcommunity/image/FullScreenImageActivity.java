package com.codementor.android.starwarsbattlefrontcommunity.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.model.Content;

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
        Bundle imageBundle = intent.getExtras();
        //set the resource reference on an ImageView
        ImageView fullscreenImage = (ImageView)findViewById(R.id.iv_image_fullscreen);

        fullscreenImage.setImageBitmap((Bitmap) imageBundle.getParcelable(Content.FULLSCREEN_IMAGE_EXTRA));
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
}
