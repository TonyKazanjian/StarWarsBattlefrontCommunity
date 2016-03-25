package com.codementor.android.starwarsbattlefrontcommunity.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tonyk_000 on 2/13/2016.
 */
public class PictureUtils {

    //for getting Uri from bitmap
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, null, null);
        return Uri.parse(path);
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES); //this is the directory created for the application
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        String CurrentPhotoPath = "file:/" + image.getAbsolutePath();
        return image;
    }

    //gets the file path from Uri, in order to create the file object
    public static String getRealPathFromURI(Uri uri, Activity activity) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static Bitmap decodeBitmapFromFile(String path, int destWidth, int destHeight){
        //Read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferQualityOverSpeed = true; //trying to get higher quality image
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //figure out how much to scale down by
        int inSampleSize = 4;
        if (srcHeight > destHeight || srcWidth > destWidth){
            if (srcWidth > srcHeight){
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Read in and create final bitmap
        return BitmapFactory.decodeFile(path, options);
    }

    //This checks to see how big the screen is, then scales the image down to that size
    public static Bitmap decodeBitmapFromFile(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return decodeBitmapFromFile(path, size.x, size.y);
    }
}

