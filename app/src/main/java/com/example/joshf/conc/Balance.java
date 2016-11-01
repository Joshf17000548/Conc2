package com.example.joshf.conc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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


public class Balance extends Fragment implements SensorEventListener {

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

    double maxaccy, minaccy;

    long timeprev, timenow, timervalue;

    long duration_initiation = 5000;
    long duration_setup = 5000;
    long duration_assessment = 15000;

    TextView taz,tpi,tro,tmvd,tmlrms,taprms,tpd, ttimervalue,tstatus;

    Button startbutton;
    public Button bt1, bt2, bt3, bt4;

    EditText edit_errorcnt;
    TextView tgs;
    TextView title_text;
    public TextView ptp;

    long initstarttime, starttime, endtime;

    boolean timerstarted;
    boolean initiation;

    int errorcount = 0;
    int status = 0;
    int balancestatus = 0;

    public static Balance newInstance() {
        Balance fragment = new Balance();
        return fragment;
    }

//    PowerManager.WakeLock mWakeLock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.balance, container, false);



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



        PreferenceConnector.acc_values0 = new double[9999];;
        PreferenceConnector.acc_values1 = new double[9999];
        PreferenceConnector.acc_values2 = new double[9999];;
        PreferenceConnector.acc_cnt = 0;
        PreferenceConnector.rot_values0 = new double[9999];;
        PreferenceConnector.rot_values1 = new double[9999];;
        PreferenceConnector.rot_values2 = new double[9999];;
        PreferenceConnector.rot_cnt =0;


        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mRotationVector = mSensorManager.getDefaultSensor (Sensor.TYPE_ROTATION_VECTOR);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_FASTEST);






        taz= (TextView)rootView.findViewById(R.id.textazimuthvalue);
        tpi= (TextView)rootView.findViewById(R.id.textpitchvalue);
        tro= (TextView)rootView.findViewById(R.id.textrollvalue);

        ttimervalue = (TextView)rootView.findViewById(R.id.texttimervalue);

        tmvd= (TextView)rootView.findViewById(R.id.textMVDvalue);
        tmlrms= (TextView)rootView.findViewById(R.id.textMLRMSvalue);
        taprms= (TextView)rootView.findViewById(R.id.textAPRMSvalue);
        tpd= (TextView)rootView.findViewById(R.id.textPDvalue);
        tstatus = (TextView)rootView.findViewById(R.id.textstatusvalue);
        ptp = (TextView)rootView.findViewById(R.id.textpeaktopeak);


        startbutton = (Button)rootView.findViewById(R.id.button);


        bt1 = (Button)rootView.findViewById(R.id.button_double_leg);
        bt2 = (Button)rootView.findViewById(R.id.button_single_leg);
        bt3 = (Button)rootView.findViewById(R.id.button_tandem_stance);



        long timestamp = System.currentTimeMillis();


        //tcX= (TextView)findViewById(R.id.x_acis);
        //tcY= (TextView)findViewById(R.id.y_acis);
        //tcZ= (TextView)findViewById(R.id.z_acis);


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
        set_button_colors();


        edit_errorcnt = (EditText) rootView.findViewById(R.id.edit_error_cnt);
        tgs = (TextView)rootView.findViewById(R.id.text_diagnose_status);
        title_text = (TextView)rootView.findViewById(R.id.title_text_balance);
        int at = PreferenceConnector.assessment_type;
        switch(at){
            case 12:title_text.setText("HIA I: Balance"); break;
            case 22:title_text.setText("HIA 2: Balance"); break;
            case 32:title_text.setText("HIA 3: Balance"); break;
            default: title_text.setText("Balance"); break;
        }


        set_values();

        startbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                starttime = System.currentTimeMillis();

                if (status != 4)
                    startbutton.setClickable(false);
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

                maxaccy = 0;
                minaccy = 0;



//        status = 1;

                initiation = true;
                timerstarted = false;
                // Do something in response to button click

            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        //mWakeLock.release();
        super.onDestroy();
    }


    /** Called when the user touches the button */
   /* public void start_clicked(View view) {

        starttime = System.currentTimeMillis();

        if (status != 4)
            startbutton.setClickable(false);
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

        maxaccy = 0;
        minaccy = 0;



//        status = 1;

        initiation = true;
        timerstarted = false;
        // Do something in response to button click
    }*/

    @Override
    public void onSensorChanged(SensorEvent event) {

        long timestamp = System.currentTimeMillis();


        if (status==0) {
            timervalue = 0;
            PreferenceConnector.acc_cnt = 0;
            PreferenceConnector.rot_cnt = 0;
        }
        else if(status==1)
            timervalue = (duration_setup - (timestamp - starttime));
        else if (status==2)
            timervalue = (duration_initiation - (timestamp - starttime));
        else if (status==3) {
            timervalue = (duration_assessment - (timestamp - starttime));
            PreferenceConnector.acc_cnt = PreferenceConnector.acc_cnt + 1;
            PreferenceConnector.rot_cnt = PreferenceConnector.rot_cnt + 1;
        }
        else if (status==4) {
            timervalue = 0;
            PreferenceConnector.max_cnt = PreferenceConnector.acc_cnt;

            //           PreferenceConnector.acc_cnt = 0;
            //         PreferenceConnector.rot_cnt = 0;
        }
        else
            timervalue = 0;


        if ((status == 0) && (initiation == true))
            status = 1;
        else  if ((status == 1) && (timervalue <=0))
        {
            status = 2;
            starttime = timestamp;

        }

        else if ((status == 2) && (timervalue <=0))
        {
            status = 3;
            starttime = timestamp;
        }
        else if ((status == 3) && (timervalue <=0))
        {
            status = 4;
            starttime = timestamp;
            initiation = false;
        }
        else if ((status==4) && (initiation==true))
        {
            status = 0;
            initiation = false;
            //status = 3;
            //starttime = timestamp;
        }






/*
        if ((status == 3) && ((timestamp - starttime - duration_asessment) >=0))
        {
            status = 3;
            starttime = timestamp;
        }

*/
        /*

        if ((initiation==true) && ((timestamp - initstarttime) > duration_initiation))
        {
            starttime = timestamp;
            timerstarted = true;
            initiation = false;

        }
*/

/*
            if ((timerstarted==true) && ((timestamp - starttime) > duration_assessment)) {
                initiation = false;
                timerstarted = false;

                timervalue = timestamp - starttime;
                endtime = timestamp;
                MVD = sumMVD;///N;
                MLRMS = Math.pow((sumML / N), 0.5);
                APRMS = Math.pow((sumAP / N), 0.5);
                PD = 0;
            }
  */

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



            if ((status == 0) || (status == 1) || (status == 2))
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

            if (status == 3)
            {

                sumMVD = sumMVD + (Math.pow((Math.pow((ML - MLprev),2) + Math.pow((AP - APprev),2)),0.5))/(timenow - timeprev);

                sumML = sumML + (ML*ML);
                sumAP = sumAP + (AP*AP);
                N = N + 1;



            }

            if ((status == 3) || (status==4)) {
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

            set_values();
        }

        if (status ==3) {
            PreferenceConnector.acc_values0[PreferenceConnector.acc_cnt] = valuesAccelerometer[0];
            PreferenceConnector.acc_values1[PreferenceConnector.acc_cnt] = valuesAccelerometer[1];
            PreferenceConnector.acc_values2[PreferenceConnector.acc_cnt] = valuesAccelerometer[2];

            PreferenceConnector.rot_values1[PreferenceConnector.rot_cnt] = matrixValues[0];
            PreferenceConnector.rot_values1[PreferenceConnector.rot_cnt] = matrixValues[1];
            PreferenceConnector.rot_values2[PreferenceConnector.rot_cnt] = matrixValues[2];
        }
    }




    void set_values (){

        taz.setText(Double.toString((double)Math.round(azimuth * 100) / 100));
        tpi.setText(Double.toString((double)Math.round(pitch * 100) / 100));
        tro.setText(Double.toString((double)Math.round(roll * 100) / 100));

        tmvd.setText(Double.toString(MVD));
        tmlrms.setText(Double.toString((double)Math.round(MLRMS * 100) / 100));
        taprms.setText(Double.toString((double)Math.round(APRMS * 100) / 100));
        tpd.setText(Double.toString(PD));


        ttimervalue.setText(Double.toString(Math.round(timervalue)));


        if (status == 0)
        {
            tstatus.setText("Idle");
            startbutton.setText("START ASSESSMENT");
        }
        else if (status==1) {

            tstatus.setText("Setup time");
            startbutton.setText("WAIT");

        }
        else if (status==2) {
            tstatus.setText("Initiating");
            startbutton.setText("WAIT");
        }

        else if (status == 3) {
            tstatus.setText("Assessment in Progress");
            startbutton.setText("WAIT");
        }
        else  if (status==4)
        {
            tstatus.setText("Ässessment Completed");
            startbutton.setClickable(true);
            startbutton.setText("CLEAR ALL");
        }
        else
            tstatus.setText("Idle");



        ptp.setText("Peak to Peak ML : " + Double.toString(maxaccy - minaccy));


/*
            if (initiation == true)
                tstatus.setText("Initiating");
            else if (timerstarted == true)
                tstatus.setText("Assessment in Progress");
            else
                tstatus.setText("Idle");
  */

            /*
            tvX.setText(Double.toString(azimuth));
            tvY.setText(Double.toString(pitch));
            tvZ.setText(Double.toString(roll));
            tcX.setText(Double.toString(accx));
            tcY.setText(Double.toString(accy));
            tcZ.setText(Double.toString(accz));
  */

    }

    public void diagnose_clicked(View v){
        //////////////////////////////////////////////////////////////////////////////
        //ALL THE PREFERENCE CONNECTOR VALUES?VARIABLES SHOULD BE SAVED
        ///////////////////////////////////////////////////////////////////////////

        //    errorcount = Integer.parseInt(edit_errorcnt.getText().toString());

        String errtext = edit_errorcnt.getText().toString();
        if (errtext.matches("")) {
            Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();

        }
        else

        {
            errorcount = Integer.parseInt(errtext);

            int at = PreferenceConnector.assessment_type;

            if (at == 12) {
                switch (balancestatus) {
                    case 1:
                        PreferenceConnector.hia1_balance_dl = errorcount;
                        PreferenceConnector.hia1_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia1_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia1_balance_dl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 2:
                        PreferenceConnector.hia1_balance_sl = errorcount;
                        PreferenceConnector.hia1_balance_sl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia1_balance_sl_APRMS = (float) APRMS;
                        PreferenceConnector.hia1_balance_sl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 3:
                        PreferenceConnector.hia1_balance_ts = errorcount;
                        PreferenceConnector.hia1_balance_ts_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia1_balance_ts_APRMS = (float) APRMS;
                        PreferenceConnector.hia1_balance_ts_PTP = (float) (maxaccy - minaccy);
                        break;
                    default:
                        PreferenceConnector.hia1_balance_dl = 0;
                        PreferenceConnector.hia1_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia1_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia1_balance_dl_PTP = (float) (maxaccy - minaccy);

                }

                int ed = PreferenceConnector.hia1_balance_dl;
                int es = PreferenceConnector.hia1_balance_sl;
                int et = PreferenceConnector.hia1_balance_ts;

                if ((et >= 3) || (es >= 4) || ((ed + et + es) >= 8)) {
                    tgs.setText("Status: Abnormal ( Test Failed )");
                    PreferenceConnector.hia1_balance_status = 0;
                } else {
                    tgs.setText("Status: Normal ( Test Passed )");
                    PreferenceConnector.hia1_balance_status = 1;
                }
            }


            ////////////////////

            if (at == 22) {
                switch (balancestatus) {
                    case 1:
                        PreferenceConnector.hia2_balance_dl = errorcount;
                        PreferenceConnector.hia2_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia2_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia2_balance_dl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 2:
                        PreferenceConnector.hia2_balance_sl = errorcount;
                        PreferenceConnector.hia2_balance_sl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia2_balance_sl_APRMS = (float) APRMS;
                        PreferenceConnector.hia2_balance_sl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 3:
                        PreferenceConnector.hia2_balance_ts = errorcount;
                        PreferenceConnector.hia2_balance_ts_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia2_balance_ts_APRMS = (float) APRMS;
                        PreferenceConnector.hia2_balance_ts_PTP = (float) (maxaccy - minaccy);
                        break;
                    default:
                        PreferenceConnector.hia2_balance_dl = 0;
                        PreferenceConnector.hia2_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia2_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia2_balance_dl_PTP = (float) (maxaccy - minaccy);

                }

                int ed = PreferenceConnector.hia2_balance_dl;
                int es = PreferenceConnector.hia2_balance_sl;
                int et = PreferenceConnector.hia2_balance_ts;

                if ((et >= 3) || (es >= 4) || ((ed + et + es) >= 8)) {
                    tgs.setText("Status: Abnormal ( Test Failed )");
                    PreferenceConnector.hia2_balance_status = 0;
                } else {
                    tgs.setText("Status: Normal ( Test Passed )");
                    PreferenceConnector.hia2_balance_status = 1;
                }
            }

            //////////////////////////

            if (at == 32) {
                switch (balancestatus) {
                    case 1:
                        PreferenceConnector.hia3_balance_dl = errorcount;
                        PreferenceConnector.hia3_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia3_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia3_balance_dl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 2:
                        PreferenceConnector.hia3_balance_sl = errorcount;
                        PreferenceConnector.hia3_balance_sl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia3_balance_sl_APRMS = (float) APRMS;
                        PreferenceConnector.hia3_balance_sl_PTP = (float) (maxaccy - minaccy);
                        break;
                    case 3:
                        PreferenceConnector.hia3_balance_ts = errorcount;
                        PreferenceConnector.hia3_balance_ts_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia3_balance_ts_APRMS = (float) APRMS;
                        PreferenceConnector.hia3_balance_ts_PTP = (float) (maxaccy - minaccy);
                        break;
                    default:
                        PreferenceConnector.hia3_balance_dl = 0;
                        PreferenceConnector.hia3_balance_dl_MLRMS = (float) MLRMS;
                        PreferenceConnector.hia3_balance_dl_APRMS = (float) APRMS;
                        PreferenceConnector.hia3_balance_dl_PTP = (float) (maxaccy - minaccy);

                }

                int ed = PreferenceConnector.hia3_balance_dl;
                int es = PreferenceConnector.hia3_balance_sl;
                int et = PreferenceConnector.hia3_balance_ts;

                if ((et >= 3) || (es >= 4) || ((ed + et + es) >= 8)) {
                    tgs.setText("Status: Abnormal ( Test Failed )");
                    PreferenceConnector.hia3_balance_status = 0;
                } else {
                    tgs.setText("Status: Normal ( Test Passed )");
                    PreferenceConnector.hia3_balance_status = 1;
                }
            }



        }
        //return true;

        try {

            // INCLUDE TIMESTAMP IF POSSIBLE
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values0);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values1);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.acc_values2);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values0);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values1);
            PreferenceConnector.writedata("data.txt",PreferenceConnector.rot_values2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void button_double_leg_clicked(View v){
        balancestatus = 1;
        edit_errorcnt.setText("");
        tgs.setText("Status : ");
        set_button_colors();

//        bt2.setClickable(false);
        //      bt3.setClickable(false);
        //    bt4.setClickable(false);

    }


    public void button_single_leg_clicked(View v){
        balancestatus = 2;
        edit_errorcnt.setText("");
        tgs.setText("Status : ");
        set_button_colors();

//        bt2.setClickable(false);
        //      bt3.setClickable(false);
        //    bt4.setClickable(false);

    }


    public void button_tandem_stance_clicked(View v){
        balancestatus = 3;
        edit_errorcnt.setText("");
        tgs.setText("Status : ");
        set_button_colors();

    }




    public void set_button_colors(){

        switch(balancestatus){
            case 1:     {
                bt1.setBackgroundColor(Color.GREEN);
                bt2.setBackgroundColor(Color.RED);
                bt3.setBackgroundColor(Color.RED);

                break;

            }

            case 2:     {
                bt1.setBackgroundColor(Color.RED);
                bt2.setBackgroundColor(Color.GREEN);
                bt3.setBackgroundColor(Color.RED);
                break;
            }

            case 3:     {
                bt1.setBackgroundColor(Color.RED);
                bt2.setBackgroundColor(Color.RED);
                bt3.setBackgroundColor(Color.GREEN);
                break;
            }


            default:     {
                bt1.setBackgroundColor(Color.GREEN);
                bt2.setBackgroundColor(Color.RED);
                bt3.setBackgroundColor(Color.RED);
            }

        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    public void onResume() {

        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this,     mMagneticField,     SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this,     mRotationVector,     SensorManager.SENSOR_DELAY_FASTEST);
        // mWakeLock.acquire();
    }

    public void onPause() {
        mSensorManager.unregisterListener(this,     mAccelerometer);
        mSensorManager.unregisterListener(this,     mMagneticField);
        mSensorManager.unregisterListener(this,     mRotationVector);
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
