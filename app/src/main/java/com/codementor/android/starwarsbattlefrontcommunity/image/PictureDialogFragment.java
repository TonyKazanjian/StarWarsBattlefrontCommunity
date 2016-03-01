package com.codementor.android.starwarsbattlefrontcommunity.image;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_photo_select, null);
        final TextView takePhoto = (TextView) dialogLayout.findViewById(R.id.take_picture);
        final TextView choosePhoto = (TextView) dialogLayout.findViewById(R.id.choose_picture);

//        builder.setView(dialogLayout)
//                .setPositiveButton(takePhoto, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (mNameInputListener != null){
//                            mNameInputListener.onOkayClicked(new Name(firstNameInput.getText().toString(),
//                                    lastNameInput.getText().toString()));
//                        }
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        NameInputDialogFragment.this.getDialog().cancel();
//                    }
//                });

        int[] textViews = {R.id.take_picture,R.id.choose_picture};

        builder.setView(dialogLayout).setSingleChoiceItems(textViews.length, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(captureImage);
            }
        });
        return builder.create();

    }

}
