package com.codementor.android.starwarsbattlefrontcommunity.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.codementor.android.starwarsbattlefrontcommunity.utils.BattlefrontApplication;
import com.codementor.android.starwarsbattlefrontcommunity.utils.PictureUtils;

/**
 * Created by tonyk_000 on 3/28/2016.
 */
public class BitmapDecoderAsyncTask extends AsyncTask<Void, Void, Bitmap>{

    public BitmapDecoderListener mBitmapDecoderListener;
    public Bitmap mOriginalBitmap;
    public int mTargetHeight;
    public int mTargetWidth;

    public interface BitmapDecoderListener{
        void onBitmapDecoded(Bitmap bitmap);
    }

    public BitmapDecoderAsyncTask(Bitmap originalBitmap, int targetWidth, int targetHeight,@NonNull BitmapDecoderListener listener) {
        mOriginalBitmap = originalBitmap;
        mBitmapDecoderListener = listener;
        mTargetHeight = targetHeight;
        mTargetWidth = targetWidth;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Uri bitmapUri = PictureUtils.getImageUri(BattlefrontApplication.getContext(), mOriginalBitmap);
        return PictureUtils.decodeBitmapFromFile(bitmapUri.getPath(), mTargetWidth,mTargetHeight);
    }
}
