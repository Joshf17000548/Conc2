
package com.example.joshf.conc;


/**
 * Created by joshf on 2016/10/28.
 */


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class UpperLimbCoordination extends Fragment {

    Button success, reset, bt1, bt2, bt3, bt4, bt5;


    public static UpperLimbCoordination newInstance() {
        UpperLimbCoordination fragment = new UpperLimbCoordination();
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.limb_coordination, container, false);
        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            Log.e("OC", "ULB");


            success = (Button) rootView.findViewById(R.id.success);
            reset = (Button) rootView.findViewById(R.id.reset);

            bt1 = (Button) rootView.findViewById(R.id.upper1);
            bt2 = (Button) rootView.findViewById(R.id.upper2);
            bt3 = (Button) rootView.findViewById(R.id.upper3);
            bt4 = (Button) rootView.findViewById(R.id.upper4);
            bt5 = (Button) rootView.findViewById(R.id.upper5);

            success.setClickable(true);

            success.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    PreferenceConnector.upper_score = PreferenceConnector.upper_score + 1;
                    if (PreferenceConnector.upper_score >= 5)
                        PreferenceConnector.upper_score = 5;
                    set_button_colors();
                }

            });

            reset.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    PreferenceConnector.upper_score = PreferenceConnector.upper_score - 1;
                    if (PreferenceConnector.upper_score <= 0)
                        PreferenceConnector.upper_score = 0;
                    set_button_colors();
                }

            });
        }

      return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT)
            set_button_colors();

    }
    @Override
    public void onPause() {
        super.onPause();



    }

    public void set_button_colors(){

        Resources resources = getActivity().getResources();
        Drawable pressed = resources.getDrawable(R.drawable.upperbutton_pressed);
        Drawable unpressed = resources.getDrawable(R.drawable.upperbutton_unpressed);

        switch(PreferenceConnector.upper_score){
            case 0:
                bt1.setBackground(unpressed);
                bt2.setBackground(unpressed);
                bt3.setBackground(unpressed);
                bt4.setBackground(unpressed);
                bt5.setBackground(unpressed);
                break;
            case 1:
                bt1.setBackground(pressed);
                bt2.setBackground(unpressed);
                bt3.setBackground(unpressed);
                bt4.setBackground(unpressed);
                bt5.setBackground(unpressed);
                break;
            case 2:
                bt1.setBackground(pressed);
                bt2.setBackground(pressed);
                bt3.setBackground(unpressed);
                bt4.setBackground(unpressed);
                bt5.setBackground(unpressed);
                break;
            case 3:
                bt1.setBackground(pressed);
                bt2.setBackground(pressed);
                bt3.setBackground(pressed);
                bt4.setBackground(unpressed);
                bt5.setBackground(unpressed);
                break;
            case 4:
                bt1.setBackground(pressed);
                bt2.setBackground(pressed);
                bt3.setBackground(pressed);
                bt4.setBackground(pressed);
                bt5.setBackground(unpressed);
                break;
            case 5:
                bt1.setBackground(pressed);
                bt2.setBackground(pressed);
                bt3.setBackground(pressed);
                bt4.setBackground(pressed);
                bt5.setBackground(pressed);
                break;

        }
    }

}

