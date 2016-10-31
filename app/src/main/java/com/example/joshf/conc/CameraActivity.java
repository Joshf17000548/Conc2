/*
package com.example.joshf.conc;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

*/
/**
 * Created by Josh_PC on 2016/10/13.
 *//*

public class CameraActivity extends Activity {

    ImageView screenShot;
    ImageButton check;
    ImageButton cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("CameraActivity", "Activity Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);

        check = (ImageButton) findViewById(R.id.check);
        cancel = (ImageButton) findViewById(R.id.cancel);

        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("screenshot");
        String run = intent.getStringExtra("run");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        screenShot = (ImageView) findViewById(R.id.screenShot);
        screenShot.setImageBitmap(bitmap);


        if (run.equals("first")) {

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            CameraFragment cameraFragment = CameraFragment.newInstance();
            transaction.replace(R.id.cameraFeed, cameraFragment);
            transaction.commit();
        } else{

            check.setVisibility(View.VISIBLE);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //TODO start player profile again and crop image data and pass as argument
    */
/*                mCallback.onCheckSelected();
                    check.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    cameraButton.setVisibility(View.VISIBLE);
                    photoLoaded=true;*//*


                }
            });
            cancel.setVisibility(View.VISIBLE);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);

                    CameraFragment cameraFragment = CameraFragment.newInstance();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.cameraFeed, cameraFragment).commit();
            }
        });
    }






    }
}
*/
