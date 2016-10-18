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
import android.util.Base64;
import android.util.Log;
import android.view.InputQueue;
import android.view.LayoutInflater;
import android.view.View;
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
    ImageCallback iCallback;

    public interface CheckSelectedCallback {
        public void onCheckSelected();
    }
    public interface ImageCallback {
        public void imageCallback(byte[] image);
    }

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
        try {
            iCallback = (ImageCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ImageCallback");
        }
    }

    public ImageView playerPhoto;
    public ImageButton startCamera;
    private String TAG ="PhotoFragment";

    String player_select;
    String run_mode;
    Boolean camera;
    byte[] imageData;
    public ImageButton cameraButton;
    private ImageButton cancel;
    private ImageButton check;
    Player selectedPlayer;


    DisplayImageOptions options;
    ImageLoader imageLoader;
    private Activity context;

    public Boolean photoLoaded;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_photo, container, false);
        Activity activity = getActivity();

        player_select = getArguments().getString("player_select");
        run_mode = getArguments().getString("run_mode");
        camera = getArguments().getBoolean("camera");
        playerPhoto = (ImageView) view.findViewById(R.id.playerFinalPhoto);
        cancel = (ImageButton) activity.findViewById(R.id.cancel);
        check = (ImageButton) activity.findViewById(R.id.check);
        cameraButton = (ImageButton) activity.findViewById(R.id.cameraButton);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (player_select.equalsIgnoreCase("existing")) {
            if (camera){

                imageData = getArguments().getByteArray("player_photo");
                iCallback.imageCallback(imageData);
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
                playerPhoto.setImageBitmap(rotatedBitmap);
            }else{
                selectedPlayer  =((ObjectWrapperForBinder) getArguments().getBinder("player_info")).getData();

               // File cacheDir = StorageUtils.getOwnCacheDirectory(context, "http://104.198.254.110/ConcApp/Player_Image/");
                try {
                    // Get singletone instance of ImageLoader
                    imageLoader = ImageLoader.getInstance();
                    // Create configuration for ImageLoader (all options are optional)
                    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
// Initialize ImageLoader with created configuration. Do it once.
                    imageLoader.init(config);
                    options = new DisplayImageOptions.Builder()
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .showImageOnLoading(R.mipmap.ic_launcher)//display stub image until image is loaded
                            .displayer(new RoundedBitmapDisplayer(10))
                            .build();
                    //---------------/IMG----
                }catch (Exception e){
                    e.printStackTrace();
                }
                String photoPath = String.valueOf(selectedPlayer.getCode_Player());
                try {
                    imageLoader.displayImage("http://104.198.254.110/ConcApp/Player_Image/" + photoPath +"IMG.png", playerPhoto, options);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (camera) {
                check.setVisibility(View.VISIBLE);
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.onCheckSelected();
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
                        CameraFragment cameraFragment = CameraFragment.newInstance();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.cameraFeed, cameraFragment).commit();
                    }
                });
            }
        }else{
            playerPhoto.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.unknown_profile));
        }
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

}
