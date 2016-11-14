package com.example.joshf.conc;

/**
 * Created by joshf on 2016/10/28.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


public class Gait extends Fragment implements SensorEventListener {

    ToneGenerator toneGen;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagneticField;
    private Sensor mRotationVector;

    double alpha = 0.8;
    private float[] valuesAccelerometer;
    private float[] valuesMagneticField;
    private float[] valuesRotationVector;
    private double[] gravity;
    private double[] linear_acceleration;
    private float[] matrixR;
    private float[] mRotationMatrix;
    private float[] matrixI;
    private float[] matrixValues;

    double azimuth, pitch, roll;
    double azimuth_init, pitch_init, roll_init;
    double accx, accy, accz;
    double ML, AP, MLprev, APprev, N, sumML, sumAP,sumMVD;
    double MVD, MLRMS, APRMS, PD;

    long timeprev, timenow, timervalue;

    long duration_initiation = 2000;
    long duration_setup = 5000;
    long duration_assessment = 14000;

    TextView /*taz,tpi,tro,tmvd,tmlrms,taprms,tpd,*/ ttimervalue;//,tstatus;


    public View startbutton;
    Button bt1, bt2, bt3, reset;

    //EditText edit_time_text;

    TextView title_text;

    long initstarttime, starttime, endtime;

    boolean timerstarted;
    boolean initiation;
    boolean teststart;

    float assessmenttime = 0;
    int status = 0;
    int teststatus = 1;

//    PowerManager.WakeLock mWakeLock;

    public static Gait newInstance() {
        Gait fragment = new Gait();
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Mount phone on the patients chest and press ok only when finised")
                        .setTitle(R.string.dialog_title);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (PreferenceConnector.gait_test_completed==0) {
                            startbutton.setClickable(true);
                            add();
                        }else{
                            reset.setClickable(true);
                        }

                        dialog.dismiss();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }

        }else{
            remove();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.gait, container, false);
      //
        //  Log.e("gait", "onCreate");

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        timerstarted = false;
        initiation = false;

        valuesAccelerometer = new float[3];
        gravity = new double[3];
        linear_acceleration = new double[3];
        valuesMagneticField = new float[3];
        valuesRotationVector = new float[4];

        matrixR = new float[9];
        mRotationMatrix = new float [9];
        matrixI = new float[9];
        matrixValues = new float[3];

        PreferenceConnector.time_stamp = new long[9999];
        PreferenceConnector.event_stamp= new double[5];
        PreferenceConnector.acc_values0 = new double[9999];
        PreferenceConnector.acc_values1 = new double[9999];
        PreferenceConnector.acc_values2 = new double[9999];
        PreferenceConnector.acc_cnt = 0;
        PreferenceConnector.rot_values0 = new double[9999];
        PreferenceConnector.rot_values1 = new double[9999];
        PreferenceConnector.rot_values2 = new double[9999];
        PreferenceConnector.rot_cnt =0;

        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ) {

            ttimervalue = (TextView)rootView.findViewById(R.id.texttimervalue);
            title_text = (TextView)rootView.findViewById(R.id.title_text_tandem) ;

            startbutton = rootView.findViewById(R.id.gaitFrame);
            bt1 = (Button)rootView.findViewById(R.id.button_test1);
            bt2 = (Button)rootView.findViewById(R.id.button_test2);
            bt3 = (Button)rootView.findViewById(R.id.button_test3);
            reset = (Button) rootView.findViewById(R.id.reset);

            reset.setClickable(true);

            final long timestamp = System.currentTimeMillis();

            azimuth = 0;
            pitch = 0;
            roll = 0;

            azimuth_init = 0;
            pitch_init = 0;
            roll_init = 0;

            accx = 0;
            accy = 0;
            accz = 0;

            ML = 0;
            AP = 0;
            MLprev = 0;
            APprev = 0;
            N = 0;
            sumML = 0;
            sumAP = 0;
            sumMVD = 0;

            MVD = 0;
            MLRMS = 0;
            APRMS = 0;
            PD = 0;

            timeprev = 0;
            timenow = 0;
            timervalue = 0;

            timerstarted = false;

            status = 0;
            teststatus = 1;


            set_values();

            startbutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if(status==0) {
                        toneGen.startTone(ToneGenerator.TONE_PROP_BEEP,500);
                        starttime = System.currentTimeMillis();
/*
                if (status != 2)
                    startbutton.setClickable(false);*/
                        // starttime = 0;


                        azimuth = 0;
                        pitch = 0;
                        roll = 0;

                        azimuth_init = 0;
                        pitch_init = 0;
                        roll_init = 0;

                        accx = 0;
                        accy = 0;
                        accz = 0;

                        ML = 0;
                        AP = 0;
                        MLprev = 0;
                        APprev = 0;
                        N = 0;
                        sumML = 0;
                        sumAP = 0;
                        sumMVD = 0;

                        MVD = 0;
                        MLRMS = 0;
                        APRMS = 0;
                        PD = 0;

                        timeprev = 0;
                        timenow = 0;
                        timervalue = 0;

//        status = 1;

                        initiation = true;
                        timerstarted = false;
                        // Do something in response to button click
                    }
                    if ((status==2) && (timervalue>0)){
                        toneGen.startTone(ToneGenerator.TONE_PROP_ACK,500);
                        reset.setVisibility(View.VISIBLE);
                        long timestamp = System.currentTimeMillis();;
                        PreferenceConnector.event_stamp[teststatus-1]=timestamp-starttime;
                        //Log.e("event_stamp",String.valueOf(PreferenceConnector.event_stamp[teststatus-1]));
                        teststatus = teststatus + 1;
                        upload_results();
                        title_text.setText("Tandem Gait: Done");
                        finish();
                        initiation =false;
                        status=3;

                    }
                  //  Log.e("reset","start");
                }
            });
            reset.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                 //   Log.e("reset","reset");
                    resetButton();

                }
            });

            set_button_colors();
        }

        return rootView;
    }

    public void resetButton(){

        startbutton.setClickable(true);
        status=0;

      //  Log.e("status",String.valueOf(teststatus));

        switch (teststatus){

            case 2:
                PreferenceConnector.tandem_t1=0;
                PreferenceConnector.tandem_t1_APRMS=0;
                PreferenceConnector.tandem_t1_MLRMS=0;
                teststatus = 1;
                break;
            case 3:
                PreferenceConnector.tandem_t2=0;
                PreferenceConnector.tandem_t2_APRMS=0;
                PreferenceConnector.tandem_t2_MLRMS=0;
                teststatus = 2;
                break;
            case 4:
                PreferenceConnector.tandem_t3=0;
                PreferenceConnector.tandem_t3_APRMS=0;
                PreferenceConnector.tandem_t3_MLRMS=0;
                teststatus = 3;
                break;
            case 5:
                PreferenceConnector.tandem_t4=0;
                PreferenceConnector.tandem_t4_APRMS=0;
                PreferenceConnector.tandem_t4_MLRMS=0;
                teststatus = 4;
                break;
        }

      //  Log.e("status",String.valueOf(teststatus));



        if(PreferenceConnector.gait_test_completed==1)
            add();
        title_text.setText("Tandem Gait: Attempt " + String.valueOf(teststatus) + "/4");
        PreferenceConnector.gait_test_completed=0;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        long timestamp = System.currentTimeMillis();;

            if (status == 0) {
                if(teststatus!=1) {
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                }
                timervalue = 0;
                //PreferenceConnector.acc_cnt = 0;
               // PreferenceConnector.rot_cnt = 0;
            } else if (status == 1) {
                timervalue = (duration_initiation - (timestamp - starttime));
                reset.setClickable(false);
                reset.setVisibility(View.INVISIBLE);
            } else if (status == 2) {
                timervalue = (duration_assessment - (timestamp - starttime));
                PreferenceConnector.acc_cnt = PreferenceConnector.acc_cnt + 1;
                PreferenceConnector.rot_cnt = PreferenceConnector.rot_cnt + 1;
            } else if (status == 3) {
                //timervalue = 0;
                PreferenceConnector.max_cnt = PreferenceConnector.acc_cnt;

                // PreferenceConnector.acc_cnt = 0;
                // PreferenceConnector.rot_cnt = 0;
            } else
                timervalue = 0;


            if ((status == 0) && (initiation == true)) {
                status = 1;
                set_button_colors();
            } else if ((status == 1) && (timervalue <= 0)) {
                toneGen.startTone(ToneGenerator.TONE_PROP_BEEP2, 500);
                status = 2;
                starttime = timestamp;
                set_button_colors();
            } else if ((status == 2) && (timervalue <= 0)) {
                toneGen.startTone(ToneGenerator.TONE_PROP_NACK, 300);
                status = 3;
                starttime = timestamp;
                initiation = false;
                set_button_colors();
            } else if ((status == 3) && (timervalue <= 0)) {
             //   Log.e("3","3");
                initiation = false;
                teststatus = teststatus + 1;
                set_values();
                upload_results();
                status = 0;
                set_button_colors();

                PreferenceConnector.event_stamp[teststatus-1]=duration_assessment-timervalue;
              //  Log.e("event_stamp",String.valueOf(PreferenceConnector.event_stamp[teststatus-1]));


            }

            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    for (int i = 0; i < 3; i++) {
                        valuesAccelerometer[i] = event.values[i];

                        // alpha is calculated as t / (t + dT)
                        // with t, the low-pass filter's time-constant
                        // and dT, the event delivery rate
                        gravity[i] = alpha * gravity[i] + (1 - alpha) * event.values[i];
                        linear_acceleration[i] = event.values[i] - gravity[i];
                    }
                    break;
                //	case Sensor.TYPE_MAGNETIC_FIELD:
                //		for(int i =0; i < 3; i++)
                //		{    valuesMagneticField[i] = event.values[i];   }
                //		break;


                // we received a sensor event. it is a good practice to check
                // that we received the proper event
                case Sensor.TYPE_ROTATION_VECTOR: {
                    valuesRotationVector = event.values;
                }
                //SensorManager.getRotationMatrixFromVector(mRotationMatrix , event.values);

        }


        //  	boolean success =
        SensorManager.getRotationMatrixFromVector(mRotationMatrix, valuesRotationVector);

        //	if(success)
        {
            SensorManager.getOrientation(mRotationMatrix, matrixValues);
            azimuth = Math.toDegrees(matrixValues[0]);
            azimuth = Math.round(azimuth * 1000.0) / 1000.000;
            //Log.e("azimuth", String.valueOf(azimuth));

            pitch = Math.toDegrees(matrixValues[1]);
            pitch = pitch + 45;
            pitch = Math.round(pitch * 1000.0) / 1000.0;
            //Log.e("pitch", String.valueOf(pitch));

            roll = Math.toDegrees(matrixValues[2]);
            roll = roll - 45;
            roll = Math.round(roll * 1000.0) / 1000.0;
          //  Log.e("roll", String.valueOf(roll));



            if ((status == 0) || (status == 1) )
            {
                azimuth_init = azimuth;
                pitch_init = pitch;
                roll_init = roll;
            }

            azimuth = azimuth - azimuth_init;
            pitch = pitch - pitch_init;
            roll = roll - roll_init;

            accx = linear_acceleration[0];
            accx = Math.round(accx * 1000.0) / 1000.0;
            accy = linear_acceleration[1];
            accy = Math.round(accy * 1000.0) / 1000.0;
            accz = linear_acceleration[2];
            accz = Math.round(accz * 1000.0) / 1000.0;

            String message = "A" + String.valueOf(azimuth) + "B" + String.valueOf(pitch) + "C" + String.valueOf(roll) + "D" + Long.toString(timestamp) + "G" + String.valueOf(accx) + "H" + String.valueOf(accy) + "I" + String.valueOf(accz) + "J";

            // Client.start_sending( "Y", message );

            MLprev = ML;
            APprev = AP;
            timeprev = timenow;

            ML = pitch;
            AP = roll;
            timenow = timestamp;

            if (status == 2)
            {

                sumMVD = sumMVD + (Math.pow((Math.pow((ML - MLprev),2) + Math.pow((AP - APprev),2)),0.5))/(timenow - timeprev);

                sumML = sumML + (ML*ML);
                sumAP = sumAP + (AP*AP);
                N = N + 1;
            }

            if ((status == 2) || (status==3)) {
                MVD = sumMVD;///N;
                MLRMS = Math.pow((sumML / N), 0.5);
                APRMS = Math.pow((sumAP / N), 0.5);
                PD = 0;

            }
            else
            {
                MVD = 0;//sumMVD;///N;
                MLRMS = 0;//Math.pow((sumML / N), 0.5);
                APRMS = 0;//Math.pow((sumAP / N), 0.5);
                PD = 0;
            }

/*            if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE )
                set_values();*/
        }

        if (status==2) {
/*            PreferenceConnector.time_stamp[PreferenceConnector.acc_cnt] = timestamp-starttime;
            PreferenceConnector.acc_values0[PreferenceConnector.acc_cnt] = valuesAccelerometer[0];
            PreferenceConnector.acc_values1[PreferenceConnector.acc_cnt] = valuesAccelerometer[1];
            PreferenceConnector.acc_values2[PreferenceConnector.acc_cnt] = valuesAccelerometer[2];
            PreferenceConnector.rot_values1[PreferenceConnector.rot_cnt] = matrixValues[0];
            PreferenceConnector.rot_values1[PreferenceConnector.rot_cnt] = matrixValues[1];
            PreferenceConnector.rot_values2[PreferenceConnector.rot_cnt] = matrixValues[2];*/
          //  Log.e("time_stamp",String.valueOf(PreferenceConnector.time_stamp[PreferenceConnector.acc_cnt]));

        }


        ttimervalue.setText(Integer.toString(Math.round(timervalue)));


    }

    void set_values () {
/*        Log.e("set_values",String.valueOf(teststatus));
        Log.e("set_values",String.valueOf(status));
        Log.e("set_values",String.valueOf(timervalue));*/
        if ((status == 3) && (timervalue <= 0)) {
            if (teststatus < 5) {
                Log.e("set_values","title");
                title_text.setText("Tandem Gait: Attempt " + String.valueOf(teststatus) + "/4");
            } else {
                title_text.setText("Tandem Gait: Done");
                startbutton.setClickable(false);
                finish();
            }

        }
    }

    public void upload_results(){

        //////////////////////////////////////////////////////////////////////////////
        //ALL THE PREFERENCE CONNECTOR VALUES?VARIABLES SHOULD BE SAVED
        ///////////////////////////////////////////////////////////////////////////
       // Log.e("MPRMS",String.valueOf(MLRMS));
        BigDecimal mlrms = new BigDecimal(Double.toString(MLRMS));
        mlrms = mlrms.setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal aprms = new BigDecimal(Double.toString(APRMS));
        aprms = aprms.setScale(2, BigDecimal.ROUND_HALF_UP);


        PreferenceConnector.test_status = teststatus;
            switch (teststatus) {
                case 2:
                    PreferenceConnector.tandem_t1 =(int) (duration_assessment-timervalue);
                    PreferenceConnector.tandem_t1_MLRMS = mlrms.floatValue();
                    PreferenceConnector.tandem_t1_APRMS = aprms.floatValue();
                    break;
                case 3:
                    PreferenceConnector.tandem_t2 =(int) (duration_assessment-timervalue);
                    PreferenceConnector.tandem_t2_MLRMS = mlrms.floatValue();
                    PreferenceConnector.tandem_t2_APRMS = aprms.floatValue();
                    break;
                case 4:
                    PreferenceConnector.tandem_t3 =(int) (duration_assessment-timervalue);
                    PreferenceConnector.tandem_t3_MLRMS = mlrms.floatValue();
                    PreferenceConnector.tandem_t3_APRMS = aprms.floatValue();
                    break;
                case 5:
                    PreferenceConnector.tandem_t4 =(int) (duration_assessment-timervalue);
                    PreferenceConnector.tandem_t4_MLRMS = mlrms.floatValue();
                    PreferenceConnector.tandem_t4_APRMS = aprms.floatValue();
                    break;
                default:
                    PreferenceConnector.tandem_t1 = 0;
            }

/*        try {
            // INCLUDE TIMESTAMP IF POSSIBLE
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values0);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values1);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values2);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values0);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values1);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values2);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //return true;
    }

    public void finish(){

        PreferenceConnector.gait_test_completed=1;

        mSensorManager.unregisterListener(this,     mAccelerometer);
        mSensorManager.unregisterListener(this,     mMagneticField);
        mSensorManager.unregisterListener(this,     mRotationVector);
        mSensorManager.unregisterListener(this);
        toneGen.release();

        reset.setClickable(true);


        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setMessage("Test complete. Swipe left")
                .setTitle(R.string.dialog_title);
        builder2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder2.create();
        dialog.show();

    }

    public void set_button_colors(){
        Resources resources = getActivity().getResources();
        Drawable on = resources.getDrawable(R.drawable.balance_button_pressed);
        Drawable off = resources.getDrawable(R.drawable.balance_button);

        switch(status){
            case 0:     {
                bt1.setBackground(on );
                bt2.setBackground(off );
                bt3.setBackground(off );
                break;
            }

            case 1:     {
                bt1.setBackground(off );
                bt2.setBackground(on );
                bt3.setBackground(off );
                break;
            }

            case 2:     {
                bt1.setBackground(off );
                bt2.setBackground(off );
                bt3.setBackground(on );
                break;
            }


            default:     {
                bt1.setBackground(on );
                bt2.setBackground(off );
                bt3.setBackground(off );
            }


        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void add(){
        if (mSensorManager!=null) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager.registerListener(this, mRotationVector, SensorManager.SENSOR_DELAY_FASTEST);

        }
        toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    }

    public void remove(){
        if (mSensorManager!=null && toneGen!=null) {
            mSensorManager.unregisterListener(this, mAccelerometer);
            mSensorManager.unregisterListener(this, mMagneticField);
            mSensorManager.unregisterListener(this, mRotationVector);
        }

    }



    public void onResume() {

        super.onResume();
        Log.e("gait", "onResume");
        if((PreferenceConnector.gait_test_completed==1) && getUserVisibleHint()) {
            startbutton.setClickable(false);
            title_text.setText("Tandem Gait: Done");
        }
        if(getUserVisibleHint()){
            add();
        }


            // mWakeLock.acquire();
    }

    public void onPause() {
        Log.e("gait", "onPause");
        remove();


            //mWakeLock.release();


        super.onPause();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // MenuInflater inflater=getMenuInflater();
        //   inflater.inflate(R.allmenus.tandemmenu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch(item.getItemId())
        {
            case R.id.feeds:
                break;
            case R.id.friends:
                break;
            case R.id.about:
                break;
        }*/
        return true;
    }
}
