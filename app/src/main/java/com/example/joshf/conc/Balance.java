package com.example.joshf.conc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.zip.Inflater;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


public class Balance extends Fragment implements SensorEventListener {

    ToneGenerator toneGen1;
    private SensorManager mSensorManager2;
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

    double maxaccy, minaccy;

    long timeprev, timenow, timervalue;
    int cnt=0;
    long duration_initiation = 2000;
    long duration_setup = 5000;
    long duration_assessment = 5000;

    TextView taz,tpi,tro,tmvd,tmlrms,taprms,tpd, ttimervalue,tstatus;

    View startbutton;
    Button reset;
    public Button bt2, bt3, bt4;

    EditText edit_errorcnt;
    TextView tgs;
    TextView title_text;
    public TextView ptp;

    long initstarttime, starttime, endtime;

    boolean timerstarted;
    boolean initiation;

    //int errorcount = 0;
    int status = 0;
    int balancestatus = 0;

    Boolean doublestatus = false;
    Boolean singlestatus = false;
    Boolean tandemstatus = false;

    Boolean doublepressed = false;
    Boolean singlepressed = false;
    Boolean tandempressed = false;

    public static Balance newInstance() {
        Balance fragment = new Balance();
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
                        if(!PreferenceConnector.balance_test_completed)
                        {
                            startbutton.setClickable(true);
                            add();
                        }else{
                            reset.setVisibility(View.GONE);
                        }

                        dialog.dismiss();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                if(getActivity()!=null)
                  dialog.show();
            }
        }else{
            remove();
        }
    }


//    PowerManager.WakeLock mWakeLock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.balance, container, false);
        Log.e("balance", "onCreate");


        mSensorManager2 = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticField = mSensorManager2.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mRotationVector = mSensorManager2.getDefaultSensor (Sensor.TYPE_ROTATION_VECTOR);

        //      PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //    mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "My Tag");
        //  mWakeLock.acquire();

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


        PreferenceConnector.bal_time_stamp = new double[9999];
        PreferenceConnector.bal_event_stamp= new double[10];
        PreferenceConnector.bal_status_stamp= new double[10];
        PreferenceConnector.bal_acc_values0 = new double[9999];;
        PreferenceConnector.bal_acc_values1 = new double[9999];
        PreferenceConnector.bal_acc_values2 = new double[9999];;
        PreferenceConnector.acc_cnt = 0;
        PreferenceConnector.bal_rot_values0 = new double[9999];;
        PreferenceConnector.bal_rot_values1 = new double[9999];;
        PreferenceConnector.bal_rot_values2 = new double[9999];;
        PreferenceConnector.rot_cnt =0;




        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ) {


            ttimervalue = (TextView) rootView.findViewById(R.id.texttimervalue);

            startbutton = rootView.findViewById(R.id.balanceFrame);
            reset = (Button) rootView.findViewById(R.id.reset);

            bt2 = (Button) rootView.findViewById(R.id.double_leg);
            bt3 = (Button) rootView.findViewById(R.id.single_leg);
            bt4 = (Button) rootView.findViewById(R.id.tandem);


            long timestamp = System.currentTimeMillis();

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

            maxaccy = 0;
            minaccy = 0;


            timeprev = 0;
            timenow = 0;
            timervalue = 0;

            timerstarted = false;

            status = 0;
            balancestatus = 1;

            reset.setVisibility(View.GONE);
            set_values();

            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    balancestatus = 1;
                    set_button_colors();
                }

            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    balancestatus=2;
                    set_button_colors();

                }

            });
            bt4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    balancestatus=3;
                    set_button_colors();

                }

            });

            startbutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if(status==0) {

                        toneGen1.startTone(ToneGenerator.TONE_PROP_BEEP, 500);
                        starttime = System.currentTimeMillis();


/*                    if (status != 4)
                        startbutton.setClickable(false);
                    // starttime = 0;*/


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

                        maxaccy = 0;
                        minaccy = 0;


//        status = 1;

                        initiation = true;
                        timerstarted = false;
                        // Do something in response to button click
                    }

                }
            });

            reset.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Log.e("reset", "reset");
                    Log.e("doublestatus", String.valueOf(doublestatus));
                    Log.e("singlestatus", String.valueOf(singlestatus));
                    Log.e("tandemstatus", String.valueOf(tandemstatus));
                    startbutton.setClickable(true);
                    switch (balancestatus) {
                        case 1:
                            PreferenceConnector.balance_dl = 0;
                            PreferenceConnector.balance_dl_APRMS = 0;
                            PreferenceConnector.balance_dl_MLRMS = 0;
                            PreferenceConnector.balance_dl_PTP = 0;
                            doublestatus = false;
                            set_button_colors();
                            reset.setVisibility(View.GONE);
                            break;
                        case 2:
                            PreferenceConnector.balance_sl = 0;
                            PreferenceConnector.balance_sl_APRMS = 0;
                            PreferenceConnector.balance_sl_MLRMS = 0;
                            PreferenceConnector.balance_sl_PTP = 0;
                            singlestatus = false;
                            set_button_colors();
                            reset.setVisibility(View.GONE);
                            break;
                        case 3:
                            PreferenceConnector.balance_ts = 0;
                            PreferenceConnector.balance_ts_APRMS = 0;
                            PreferenceConnector.balance_ts_MLRMS = 0;
                            PreferenceConnector.balance_ts_PTP = 0;
                            tandemstatus = false;
                            set_button_colors();
                            reset.setVisibility(View.GONE);
                            break;
                    }
                }

            });

            set_button_colors();

        }

        return rootView;
    }

    public void setClickable(){
        bt2.setClickable(true);
        bt3.setClickable(true);
        bt4.setClickable(true);
    }

    public void setNotClickable(){
        bt2.setClickable(false);
        bt3.setClickable(false);
        bt4.setClickable(false);
    }


    @Override
    public void onDestroy() {
        //mWakeLock.release();
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long timestamp = System.currentTimeMillis();

        if (status==0) {
            timervalue = 0;
            PreferenceConnector.acc_cnt = 0;
            PreferenceConnector.rot_cnt = 0;
            setClickable();
        }
        else if (status==1) {
            timervalue = (duration_initiation - (timestamp - starttime));
            setNotClickable();
        }
        else if (status==2) {
            timervalue = (duration_assessment - (timestamp - starttime));
            PreferenceConnector.acc_cnt = PreferenceConnector.acc_cnt + 1;
            PreferenceConnector.rot_cnt = PreferenceConnector.rot_cnt + 1;
        }
        else if (status==3) {
            timervalue = 0;
            PreferenceConnector.max_cnt = PreferenceConnector.acc_cnt;

            //           PreferenceConnector.acc_cnt = 0;
            //         PreferenceConnector.rot_cnt = 0;
        }
        else
            timervalue = 0;


        if ((status == 0) && (initiation == true)) {
            status = 1;
        }
        else  if ((status == 1) && (timervalue <=0))
        {
            status = 2;
            starttime = timestamp;
        }

        else if ((status == 2) && (timervalue <=0))
        {
            toneGen1.startTone(ToneGenerator.TONE_PROP_NACK,300);
            status = 3;
            starttime = timestamp;
        }
        else if ((status == 3) && (timervalue <=0))
        {
            status = 0;
            initiation = false;
            PreferenceConnector.bal_event_stamp[cnt]=timestamp-starttime;
            PreferenceConnector.bal_status_stamp[cnt]=balancestatus;
            cnt = cnt+1;
            upload_results();
            balancestatus = balancestatus +1;
            set_button_colors();
        }

/*        if ((status == 3) && ((timestamp - starttime - duration_asessment) >=0))
        {
            status = 3;
            starttime = timestamp;
        }*/


/*        if ((initiation==true) && ((timestamp - initstarttime) > duration_initiation))
        {
            starttime = timestamp;
            timerstarted = true;
            initiation = false;

        }*/



/*            if ((timerstarted) && ((timestamp - starttime) > duration_assessment)) {
                initiation = false;
                timerstarted = false;

                timervalue = timestamp - starttime;
                endtime = timestamp;
                MVD = sumMVD;///N;
                MLRMS = Math.pow((sumML / N), 0.5);
                APRMS = Math.pow((sumAP / N), 0.5);
                PD = 0;
            }*/



        // set_values();



/*
        if (timerstarted == true)
        {
            timervalue = timestamp - starttime;
        }



        if (initiation == true)
        {
            //long ttt = 5000;
            timervalue = duration_initiation - (timestamp - initstarttime);

        }
*/



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

            pitch = Math.toDegrees(matrixValues[1]);
            pitch = pitch + 45;
            pitch = Math.round(pitch * 1000.0) / 1000.0;

            roll = Math.toDegrees(matrixValues[2]);
            roll = roll - 45;
            roll = Math.round(roll * 1000.0) / 1000.0;



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

                if (Math.abs(accy) > maxaccy)
                    maxaccy = Math.abs(accy);

                if (minaccy > Math.abs(accy))
                    minaccy = Math.abs(accy);

            }
            else
            {
                MVD = 0;//sumMVD;///N;
                MLRMS = 0;//Math.pow((sumML / N), 0.5);
                APRMS = 0;//Math.pow((sumAP / N), 0.5);
                PD = 0;

                maxaccy = Math.abs(accy);
                minaccy = Math.abs(accy);

            }

            // if (status == 0)
            //   timervalue = 0;

            ttimervalue.setText(Integer.toString(Math.round(timervalue)));
        }

        if (status ==2) {
            PreferenceConnector.bal_time_stamp[PreferenceConnector.acc_cnt] = timestamp-starttime;
            PreferenceConnector.bal_acc_values0[PreferenceConnector.acc_cnt] = valuesAccelerometer[0];
            PreferenceConnector.bal_acc_values1[PreferenceConnector.acc_cnt] = valuesAccelerometer[1];
            PreferenceConnector.bal_acc_values2[PreferenceConnector.acc_cnt] = valuesAccelerometer[2];

            PreferenceConnector.bal_rot_values1[PreferenceConnector.rot_cnt] = matrixValues[0];
            PreferenceConnector.bal_rot_values1[PreferenceConnector.rot_cnt] = matrixValues[1];
            PreferenceConnector.bal_rot_values2[PreferenceConnector.rot_cnt] = matrixValues[2];
        }
    }




    void set_values (){

        if ((status == 3) && (timervalue <= 0)) {
            balancestatus = balancestatus +1;
            if (balancestatus >= 5) {
                finish();

            }
        }
    }

    public void finish(){
        PreferenceConnector.balance_status = balancestatus-1;
        mSensorManager2.unregisterListener(this,     mAccelerometer);
        mSensorManager2.unregisterListener(this,     mMagneticField);
        mSensorManager2.unregisterListener(this,     mRotationVector);
        mSensorManager2.unregisterListener(this);
        toneGen1.release();

        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setMessage("Please continue to the next slide")
                .setTitle(R.string.dialog_title);
        builder2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder2.create();
        dialog.show();

    }

    public void upload_results() {
        //////////////////////////////////////////////////////////////////////////////
        //ALL THE PREFERENCE CONNECTOR VALUES?VARIABLES SHOULD BE SAVED
        ///////////////////////////////////////////////////////////////////////////

        //    errorcount = Integer.parseInt(edit_errorcnt.getText().toString());
        int at = PreferenceConnector.assessment_type;

            switch (balancestatus) {
                case 1:
                    // PreferenceConnector.balance_dl = errorcount;
                    PreferenceConnector.balance_dl_MLRMS = (float) MLRMS;
                    PreferenceConnector.balance_dl_APRMS = (float) APRMS;
                    PreferenceConnector.balance_dl_PTP = (float) (maxaccy - minaccy);
                    doublestatus=true;
                    break;
                case 2:
                    // PreferenceConnector.balance_sl = errorcount;
                    PreferenceConnector.balance_sl_MLRMS = (float) MLRMS;
                    PreferenceConnector.balance_sl_APRMS = (float) APRMS;
                    PreferenceConnector.balance_sl_PTP = (float) (maxaccy - minaccy);
                    singlestatus=true;
                    break;
                case 3:
                    // PreferenceConnector.balance_ts = errorcount;
                    PreferenceConnector.balance_ts_MLRMS = (float) MLRMS;
                    PreferenceConnector.balance_ts_APRMS = (float) APRMS;
                    PreferenceConnector.balance_ts_PTP = (float) (maxaccy - minaccy);
                    tandemstatus=true;
                    break;
                default:
                    //   PreferenceConnector.balance_dl = 0;
                    PreferenceConnector.balance_dl_MLRMS = (float) MLRMS;
                    PreferenceConnector.balance_dl_APRMS = (float) APRMS;
                    PreferenceConnector.balance_dl_PTP = (float) (maxaccy - minaccy);

            }

/*            try {

                // INCLUDE TIMESTAMP IF POSSIBLE
                PreferenceConnector.writedata("data.txt", PreferenceConnector.acc_values0);
                PreferenceConnector.writedata("data.txt", PreferenceConnector.acc_values1);
                PreferenceConnector.writedata("data.txt", PreferenceConnector.acc_values2);
                PreferenceConnector.writedata("data.txt", PreferenceConnector.rot_values0);
                PreferenceConnector.writedata("data.txt", PreferenceConnector.rot_values1);
                PreferenceConnector.writedata("data.txt", PreferenceConnector.rot_values2);

            } catch (IOException e) {
                e.printStackTrace();
            }*/

        if(doublestatus && singlestatus && tandemstatus){
            PreferenceConnector.balance_test_completed = true;
        }


    }

    public void set_button_colors(){
        Resources resources = getActivity().getResources();
        Drawable on_red = resources.getDrawable(R.drawable.balance_button_pressed_required);
        Drawable off_red = resources.getDrawable(R.drawable.balance_button_required);
        Drawable on_green = resources.getDrawable(R.drawable.balance_button_pressed_done);
        Drawable off_green = resources.getDrawable(R.drawable.balance_button_done);

        switch(balancestatus){
            case 1:     {
                doublepressed = true;
                singlepressed = false;
                tandempressed = false;
                break;
            }
            case 2:     {
                doublepressed = false;
                singlepressed = true;
                tandempressed = false;
                break;
            }
            case 3:     {
                doublepressed = false;
                singlepressed = false;
                tandempressed = true;
                break;
            }
        }

        if (doublepressed && doublestatus){
            bt2.setBackground(on_green);
            reset.setVisibility(View.VISIBLE);
            startbutton.setClickable(false);
        }else if(!doublepressed && doublestatus){
            bt2.setBackground(off_green);
        }else if(doublepressed && !doublestatus){
            bt2.setBackground(on_red);
            reset.setVisibility(View.GONE);
            startbutton.setClickable(true);
        }else{
            bt2.setBackground(off_red);
        }

        if (singlepressed && singlestatus){
            bt3.setBackground(on_green);
            reset.setVisibility(View.VISIBLE);
            startbutton.setClickable(false);
        }else if(!singlepressed && singlestatus){
            bt3.setBackground(off_green);
        }else if(singlepressed && !singlestatus){
            bt3.setBackground(on_red);
            reset.setVisibility(View.GONE);
            startbutton.setClickable(true);
        }else{
            bt3.setBackground(off_red);
        }

        if (tandempressed && tandemstatus){
            bt4.setBackground(on_green);
            reset.setVisibility(View.VISIBLE);
            startbutton.setClickable(false);
        }else if(!tandempressed && tandemstatus){
            bt4.setBackground(off_green);
        }else if(tandempressed && !tandemstatus){
            bt4.setBackground(on_red);
            reset.setVisibility(View.GONE);
            startbutton.setClickable(true);
        }else{
            bt4.setBackground(off_red);
        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void add(){
        if (mSensorManager2!=null) {
            mSensorManager2.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager2.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager2.registerListener(this, mRotationVector, SensorManager.SENSOR_DELAY_FASTEST);

        }
        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    }

    public void remove(){
        if (mSensorManager2!=null && toneGen1!=null) {
            mSensorManager2.unregisterListener(this, mAccelerometer);
            mSensorManager2.unregisterListener(this, mMagneticField);
            mSensorManager2.unregisterListener(this, mRotationVector);
            toneGen1.release();
        }


    }



    public void onResume() {

        super.onResume();
        Log.e("balance", "onResume");
        if(PreferenceConnector.gait_test_completed && getUserVisibleHint()) {
        }
        if(getUserVisibleHint()){
            add();
        }
    }

    public void onPause() {
        Log.e("balance", "onPause");
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
/*        switch(item.getItemId())
        {

        }*/

        return true;
    }


}
