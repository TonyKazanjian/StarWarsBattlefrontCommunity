package com.codementor.android.starwarsbattlefrontcommunity.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.codementor.android.starwarsbattlefrontcommunity.R;

/**
 * Created by tonyk_000 on 2/29/2016.
 */
public class PictureDialogFragment extends DialogFragment {

    private InputListener mInputListener;

    public interface InputListener {
        void onTakePhotoSelected();
        void onChoosePhotoSelected();
    }

    public void setListener(InputListener inputListener) {
        mInputListener = inputListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_photo_select, null);
        TextView takePhoto = (TextView) dialogLayout.findViewById(R.id.take_picture);
        TextView choosePhoto = (TextView) dialogLayout.findViewById(R.id.choose_picture);

        builder.setView(dialogLayout);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputListener.onTakePhotoSelected();
            }
        });

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputListener.onChoosePhotoSelected();
            }
        });
        return builder.create();
    }
}
