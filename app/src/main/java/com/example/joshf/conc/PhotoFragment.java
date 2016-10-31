package com.example.joshf.conc;

import android.animation.Animator;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.v4.app.Fragment;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.InputQueue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.Fragment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by joshf on 2016/08/31.
 */

public class PhotoFragment extends Fragment{

    CheckSelectedCallback mCallback;
    //ImageCallback iCallback;

    public interface CheckSelectedCallback {
        public void onCheckSelected(byte[] image);
    }
/*
    public interface ImageCallback {
        public void imageCallback(byte[] image);
    }
*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (CheckSelectedCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CheckSelectedCallback");
        }
/*        try {
            iCallback = (ImageCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ImageCallback");
        }*/
    }

    public ImageView playerPhoto;
    public ImageButton startCamera;
    private String TAG ="PhotoFragment";

    String player_select;
    String run_mode;
    byte[] imageData;
    public ImageButton cameraButton;
    private ImageButton cancel;
    private ImageButton check;

    public Boolean photoLoaded;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_photo, container, false);
        Activity activity = getActivity();

        playerPhoto = (ImageView) view.findViewById(R.id.playerFinalPhoto);
        cancel = (ImageButton) view.findViewById(R.id.cancel);
        check = (ImageButton) view.findViewById(R.id.check);
        cameraButton = (ImageButton) activity.findViewById(R.id.cameraButton);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

            imageData = getArguments().getByteArray("player_photo");
            //iCallback.imageCallback(imageData);
            Bitmap myBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
            playerPhoto.setImageBitmap(rotatedBitmap);


            check.setVisibility(View.VISIBLE);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onCheckSelected(imageData);
                    check.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    cameraButton.setVisibility(View.VISIBLE);
                    photoLoaded=true;

                }
            });

            cancel.setVisibility(View.VISIBLE);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    cameraButton.setVisibility(View.GONE);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    CameraFragment cameraFragment = CameraFragment.newInstance();
                    transaction.replace(R.id.dialog_photo_holder, cameraFragment).commit();
                }
            });
        }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

}
