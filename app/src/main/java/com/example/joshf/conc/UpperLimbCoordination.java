package com.example.joshf.conc;

/**
 * Created by joshf on 2016/10/28.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class UpperLimbCoordination extends Fragment {


    TextView title_text;

    public static UpperLimbCoordination newInstance() {
        UpperLimbCoordination fragment = new UpperLimbCoordination();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.limb_coordination, container, false);

        title_text = (TextView)rootView.findViewById(R.id.title_text_upper);
        int at = PreferenceConnector.assessment_type;
        switch(at){
            case 13:title_text.setText("HIA I: Upper"); break;
            case 23:title_text.setText("HIA 2: Upper"); break;
            case 33:title_text.setText("HIA 3: Upper"); break;
            default: title_text.setText("Upper"); break;
        }

/*
        PreferenceConnector.acc_values1 = new double[100000];
        PreferenceConnector.acc_values2 = new double[100000];;
        PreferenceConnector.acc_values0 = new double[100000];;
        PreferenceConnector.acc_cnt = 0;

        PreferenceConnector.rot_values1 = new double[100000];;
        PreferenceConnector.rot_values2 = new double[100000];;
        PreferenceConnector.rot_values0 = new double[100000];;
        PreferenceConnector.rot_cnt =0;

*/      return rootView;
    }

    public void button_check_clicked(View v){

        //////////////////////////////////////////////////////////////////////////////
        //ALL THE PREFERENCE CONNECTOR VALUES?VARIABLES SHOULD BE SAVED
        ///////////////////////////////////////////////////////////////////////////

        TextView scoretv = (TextView)v.findViewById(R.id.text_upper_score);
        String scrtext = scoretv.getText().toString();
        if (scrtext.matches("")) {
            Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();

        }
        else {

            int at = PreferenceConnector.assessment_type;
            switch(at){
                case 13:PreferenceConnector.hia1_upper_score = Integer.parseInt(scrtext); break;
                case 23:PreferenceConnector.hia2_upper_score = Integer.parseInt(scrtext); break;
                case 33:PreferenceConnector.hia3_upper_score = Integer.parseInt(scrtext); break;
                default: break;//PreferenceConnector.hia1_upper_score = Integer.parseInt(scrtext); break;
            }

            //PreferenceConnector.upper_score = Integer.parseInt(scrtext);

        }
    }

/*    public void home_icon_clicked(View view) {

        Intent intent = new Intent(this, TestHomeActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        finish();
        startActivity(intent);
    }

    public void help_icon_clicked(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

}
