package com.example.joshf.conc;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.v4.app.Fragment;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.InputQueue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.Fragment;

import java.io.File;
import java.io.IOException;

/**
 * Created by joshf on 2016/08/31.
 */
public class PhotoFragment extends Fragment  {

    public ImageView playerFinalPhoto;
    public ImageButton startCamera;

    String player_select;
    String player_code;
    String run_mode;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_photo, container, false);;
        player_select = getArguments().getString("player_select");
        player_code = getArguments().getString("player_code");
        run_mode = getArguments().getString("run_mode");
        playerFinalPhoto = (ImageView) view.findViewById(R.id.playerFinalPhoto);

        Activity activity = getActivity();
        startCamera = (ImageButton) activity.findViewById(R.id.cameraButton);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String photoPath = getActivity().getExternalFilesDir(null).toString()+"/"+player_code+"/"+player_code+"P.jpg";
        Log.e("phot path", photoPath);
        if(run_mode == null){
            startCamera.setVisibility(View.VISIBLE);
        }

        if (player_select.equalsIgnoreCase("existing")) {

            //File imgFile = new  File(photoPath);
            Bitmap myBitmap = BitmapFactory.decodeFile(photoPath);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
            playerFinalPhoto.setImageBitmap(rotatedBitmap);
            playerFinalPhoto.setVisibility(View.VISIBLE);

        }else{
            playerFinalPhoto.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.unknown_profile));
        }
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

/*    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }*/

}
