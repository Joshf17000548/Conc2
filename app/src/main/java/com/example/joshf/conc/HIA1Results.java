package com.example.joshf.conc;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * Created by joshf on 2016/11/07.
 */

public class HIA1Results extends Fragment implements AdapterView.OnItemSelectedListener{

    int HIA_test;

    static int MQrequired = 5;
    static int IMRrequired = 12;
    static int DBrequired = 3;
    static int TGTrequired = 14000;
    static int TGALRMSrequired = 5;
    static int TGMPRMSrequired = 5;
    static int DRrequired = 3;
    static int DMRrequired = 3;

    int maddocksResult;

    TextView MQresults;
    TextView IMRresults;
    TextView DBresults;
    TextView DMRresults;
    View SYMresults;
    View IRresults;

    TextView tandemResult;
    TextView tandemTime;
    TextView tandemAPRMS;
    TextView tandemMPRMS;

    TextView SYMtestResult;
    TextView IRtestResult;

    TextView IMRbaseline;
    TextView DBbaseline;
    TextView DMRbaseline;

    TextView tandemTimebaseline;
    TextView tandemAPRMSbaseline;
    TextView tandemMPRMSbaseline;


    Spinner result;
    private ArrayAdapter<CharSequence> adapter3;


    public static HIA1Results newInstance() {
        HIA1Results fragment = new HIA1Results();
        return fragment;
    }

    public HIA1Results() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_results, container, false);
      //  Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_team);
      //  toolbar.setLogo(R.drawable.logo);
        //getActivity().setSupportActionBar(toolbar);


            SYMtestResult = (TextView) rootView.findViewById(R.id.SYMtestResult);
            IRtestResult = (TextView) rootView.findViewById(R.id.IRtestResult);

            MQresults = (TextView) rootView.findViewById(R.id.MQresult);
            IMRresults = (TextView) rootView.findViewById(R.id.IMRresult);
            DBresults = (TextView) rootView.findViewById(R.id.DBresult);
            DMRresults = (TextView) rootView.findViewById(R.id.DMRresult);
            SYMresults = rootView.findViewById(R.id.SYMresults);
            IRresults = rootView.findViewById(R.id.IRresults);

            tandemResult = (TextView) rootView.findViewById(R.id.TGtestResult);
            tandemTime = (TextView) rootView.findViewById(R.id.TGTimeResult);
            tandemAPRMS = (TextView) rootView.findViewById(R.id.TGalrmsResult);
            tandemMPRMS = (TextView) rootView.findViewById(R.id.TGmprmsResult);

            IMRbaseline = (TextView) rootView.findViewById(R.id.IMRbaseline);
            DBbaseline = (TextView) rootView.findViewById(R.id.DBbaseline);
            DMRbaseline = (TextView) rootView.findViewById(R.id.DMRbaseline);

            tandemTimebaseline = (TextView) rootView.findViewById(R.id.TGTimeBaseline);
            tandemAPRMSbaseline = (TextView) rootView.findViewById(R.id.TGalrmsBaseline);
            tandemMPRMSbaseline = (TextView) rootView.findViewById(R.id.TGmprmsBaseline);


            result = (Spinner) rootView.findViewById(R.id.spinner3);
            this.adapter3 = ArrayAdapter.createFromResource(this.getActivity(), R.array.player_removed_spinner, android.R.layout.simple_spinner_dropdown_item);
            this.adapter3 = ArrayAdapter.createFromResource(this.getActivity(), R.array.player_removed_spinner, R.layout.multiline_spinner_dropdown_item);
            result.setAdapter(adapter3);
            result.setOnItemSelectedListener(this);


        return rootView;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            switch (parent.getId()) {

                case R.id.spinner3:
                   // Log.v(TAG, "Video Checkbox2: " + position);
                    HIA1.HIA1_result_chosen =true;
                    HIA1.HIA1_Test7_Question5=position;
                    return;

            }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {

            Log.e("Baseline", String.valueOf(Baseline.Baseline_Immediate_Memory));

            if (Baseline.Baseline_Immediate_Memory != 0)
                IMRbaseline.setText(String.valueOf(Baseline.Baseline_Immediate_Memory));

            if (Baseline.Baseline_Digits_Backwards != 0)
                DBbaseline.setText(String.valueOf(Baseline.Baseline_Digits_Backwards));

            if (Baseline.Baseline_Delayed_Memory != 0)
                DMRbaseline.setText(String.valueOf(Baseline.Baseline_Delayed_Memory));

            if (Baseline.Baseline_Tandem_Time != 0)
                tandemTimebaseline.setText(String.valueOf(Baseline.Baseline_Tandem_Time));

            if (Baseline.Baseline_Tandem_AP != 0)
                tandemAPRMSbaseline.setText(String.valueOf(Baseline.Baseline_Tandem_AP));

            if (Baseline.Baseline_Tandem_ML != 0)
                tandemMPRMSbaseline.setText(String.valueOf(Baseline.Baseline_Tandem_ML));

////////////////Moddocks Questions
            maddocksResult = HIA1.HIA1_Test3_Question1 + HIA1.HIA1_Test3_Question4 + HIA1.HIA1_Test3_Question2 + HIA1.HIA1_Test3_Question3 + HIA1.HIA1_Test3_Question5;
            MQresults.setText(String.valueOf(maddocksResult));
            if ((maddocksResult < MQrequired))
                MQresults.setTextColor(getResources().getColor(R.color.reset));

//////////////Imediate Memory Recall
            IMRresults.setText(String.valueOf(HIA1.HIA1_Test4_Question1));
            if ((HIA1.HIA1_Test4_Question1 < IMRrequired))
                IMRresults.setTextColor(getResources().getColor(R.color.reset));
            if ((HIA1.HIA1_Test4_Question1 < Baseline.Baseline_Immediate_Memory) && (Baseline.Baseline_Immediate_Memory != 0))
                IMRresults.setTextColor(getResources().getColor(R.color.reset));

//////////////Digits Backwards
            DBresults.setText(String.valueOf(HIA1.HIA1_Test4_Question2));
            if ((HIA1.HIA1_Test4_Question2 < DBrequired))
                DBresults.setTextColor(getResources().getColor(R.color.reset));
            if ((Baseline.Baseline_Immediate_Memory != 0) && (HIA1.HIA1_Test4_Question2 < Baseline.Baseline_Digits_Backwards))
                DBresults.setTextColor(getResources().getColor(R.color.reset));

/////////////Tandem Gait

            if (PreferenceConnector.gait_test_completed == 1) {
                tandemResult.setText("Passed");
            } else {
                tandemResult.setText("Failed");
                tandemResult.setTextColor(getResources().getColor(R.color.reset));
            }


            switch (PreferenceConnector.test_status) {
                case 2:
                    tandemTime.setText(String.valueOf(PreferenceConnector.tandem_t1));
                    tandemAPRMS.setText(String.valueOf(PreferenceConnector.tandem_t1_APRMS));
                    tandemMPRMS.setText(String.valueOf(PreferenceConnector.tandem_t1_MLRMS));
                    break;
                case 3:
                    tandemTime.setText(String.valueOf(PreferenceConnector.tandem_t2));
                    tandemAPRMS.setText(String.valueOf(PreferenceConnector.tandem_t2_APRMS));
                    tandemMPRMS.setText(String.valueOf(PreferenceConnector.tandem_t2_MLRMS));
                    break;
                case 4:
                    tandemTime.setText(String.valueOf(PreferenceConnector.tandem_t3));
                    tandemAPRMS.setText(String.valueOf(PreferenceConnector.tandem_t3_APRMS));
                    tandemMPRMS.setText(String.valueOf(PreferenceConnector.tandem_t3_MLRMS));
                    break;
                case 5:
                    tandemTime.setText(String.valueOf(PreferenceConnector.tandem_t4));
                    tandemAPRMS.setText(String.valueOf(PreferenceConnector.tandem_t4_APRMS));
                    tandemMPRMS.setText(String.valueOf(PreferenceConnector.tandem_t4_MLRMS));
                    break;
                default:


            }


/////////////Symptom Checklist
            if (HIA1.HIA1_Test5_Question1 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Headache");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question2 == 1) {
                Log.e("1", "1");
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Dizziness");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question3 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Pressure in head");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question4 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Nauseous");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question5 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Blurred Vision");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question6 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Sensitive to light or noise");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question7 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Feeling slowed down");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question8 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Feeling in a fog");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }
            if (HIA1.HIA1_Test5_Question9 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Feeling in a unwell");
                SYMtestResult.setTextColor(getResources().getColor(R.color.reset));
                SYMtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) SYMresults).addView(sym1);

            }


/////////////Delayed Recall

            DMRresults.setText(String.valueOf(HIA1.HIA1_Test6_Question1));
            if ((HIA1.HIA1_Test6_Question1 < DMRrequired))
                DMRresults.setTextColor(getResources().getColor(R.color.reset));
            if ((HIA1.HIA1_Test6_Question1 < Baseline.Baseline_Delayed_Memory) && (Baseline.Baseline_Delayed_Memory != 0))
                DMRresults.setTextColor(getResources().getColor(R.color.reset));


////////////Imediate Removal

            if (HIA1.HIA1_Test1_Question1 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Tonic Posturing");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question2 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Convulsion");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question3 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Confirmed loss of consciousness");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question4 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Suspected loss of consciousness");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question5 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Balance disturbance / ataxia");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question6 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Player not orientated in time, place or person");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question7 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Clearly dazed");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question8 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Definite confusion");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question9 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Definite behavioural changes");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }

            if (HIA1.HIA1_Test1_Question10 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("On field identification of concussion");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
            if (HIA1.HIA1_Test1_Question11 == 1) {
                TextView sym1 = new TextView(getActivity());
                sym1.setText("Oculomotor signs");
                IRtestResult.setTextColor(getResources().getColor(R.color.reset));
                IRtestResult.setText("Failed");
                sym1.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                ((LinearLayout) IRresults).addView(sym1);

            }
        }


    }


    @Override
    public void onPause() {
        super.onPause();
        Activity a = getActivity();
        if (getActivity().getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT&&IRresults!=null&&SYMresults!=null) {

            if (a instanceof HIA1AActivity) {
                ((LinearLayout) IRresults).removeAllViews();
                ((LinearLayout) SYMresults).removeAllViews();
            }
        }
    }


}


