package com.example.joshf.conc;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by joshf on 2016/10/21.
 */

public class CameraDialog extends DialogFragment {

    ImageButton check;
    ImageButton cancel;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_dialog, container,
                false);

        //check = (ImageButton)   getFragmentManager().fin

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        CameraFragment cameraFragment = CameraFragment.newInstance();
        transaction.replace(R.id.dialog_photo_holder, cameraFragment);
        transaction.commit();
        // Do something else
        return rootView;
    }

}
