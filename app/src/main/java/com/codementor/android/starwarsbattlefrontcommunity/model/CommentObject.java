package com.codementor.android.starwarsbattlefrontcommunity.model;

import android.annotation.SuppressLint;
import android.os.Parcel;

/**
 * Created by tonyk_000 on 4/1/2016.
 */
@SuppressLint("ParcelCreator")
public class CommentObject extends ContentObject {

    public CommentObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
