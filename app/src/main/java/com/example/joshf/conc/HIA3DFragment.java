package com.example.joshf.conc;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;


public class HIA3DFragment extends Fragment implements CheckBox.OnCheckedChangeListener {

    TextView tandemResult;
    TextView tandemTime;
    TextView tandemAPRMS;
    TextView tandemMPRMS;

    TextView dlResult;
    TextView dlptp;
    TextView dlAPRMS;
    TextView dlMLRMS;

    TextView slResult;
    TextView slptp;
    TextView slAPRMS;
    TextView slMLRMS;

    TextView tsResult;
    TextView tsptp;
    TextView tsAPRMS;
    TextView tsMLRMS;

    //database
    public HIA3AActivity hia3test;

    private EditText other;
    String HIA3_Test4_Question6;

    public static HIA3DFragment newInstance() {
        HIA3DFragment fragment = new HIA3DFragment();
        return fragment;
    }

    public HIA3DFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_hia3_d, container, false);

        final CheckBox mButton = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM1);
        final CheckBox mButton1 = (CheckBox) rootView.findViewById(R.id.checkBox_DOM2);
        final CheckBox mButton2 = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM3);
        //final CheckBox mButton3 = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM4);

        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox3_CSY);
        RadioButton mButton5 = (RadioButton) rootView.findViewById(R.id.checkBox3_diag_concY);

        tandemResult = (TextView) rootView.findViewById(R.id.TGtestResult);
        tandemTime = (TextView) rootView.findViewById(R.id.TGTimeResult);
        tandemAPRMS = (TextView) rootView.findViewById(R.id.TGalrmsResult);
        tandemMPRMS = (TextView) rootView.findViewById(R.id.TGmlrmsResult);

        dlResult = (TextView) rootView.findViewById(R.id.DLResult);
        dlptp = (TextView) rootView.findViewById(R.id.DLptpResult);
        dlAPRMS = (TextView) rootView.findViewById(R.id.DLalrmsResult);
        dlMLRMS = (TextView) rootView.findViewById(R.id.DLmprmsResult);

        slResult = (TextView) rootView.findViewById(R.id.SLtestResult);
        slptp = (TextView) rootView.findViewById(R.id.SLptpResult);
        slAPRMS = (TextView) rootView.findViewById(R.id.SLalrmsResult);
        slMLRMS = (TextView) rootView.findViewById(R.id.SLmprmsResult);

        tsResult = (TextView) rootView.findViewById(R.id.TStestResult);
        tsptp = (TextView) rootView.findViewById(R.id.TSptpResult);
        tsAPRMS = (TextView) rootView.findViewById(R.id.TSalrmsResult);
        tsMLRMS = (TextView) rootView.findViewById(R.id.TSmprmsResult);

        mButton4.setOnCheckedChangeListener(this);
        mButton5.setOnCheckedChangeListener(this);

        other = (EditText) rootView.findViewById(R.id.editText7);

        Activity c = getActivity();
        if(c instanceof HIA3AActivity) {
            hia3test = (HIA3AActivity) getActivity();

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton.isChecked();
                    if (checked2) {
                        //HIA3_Test3_Question1 = true;
                        HIA3.HIA3_Test4_Question1=1;
                        Log.v("1", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question1 = false;
                        HIA3.HIA3_Test4_Question1=0;
                        Log.v("0", "Test: " + 0);
                    }
                }
            });

            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton1.isChecked();
                    if (checked2) {
                        //HIA1_Test2_Question2 = true;
                        HIA3.HIA3_Test4_Question2=1;
                        Log.v("2", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question2 = false;
                        HIA3.HIA3_Test4_Question2=0;
                        Log.v("2", "Test: " + 0);
                    }
                }
            });

            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton2.isChecked();
                    if (checked2) {
                        //HIA1_Test2_Question3 = true;
                        HIA3.HIA3_Test4_Question3=1;
                        Log.v("3", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question3 = false;
                        HIA3.HIA3_Test4_Question3=0;
                        Log.v("3", "Test: " + 0);
                    }
                }
            });

          /*  mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton3.isChecked();
                    if (checked2) {
                        // HIA1_Test2_Question4 = true;
                        // hia3test.objHIA3.setHIA3_Test4_Question4(1);
                        Log.v("4", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question4 = false;
                        // hia3test.objHIA3.setHIA3_Test4_Question4(0);
                        Log.v("4", "Test: " + 0);
                    }
                }
            });*/

            other.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    HIA3_Test4_Question6 =other.getText().toString();
                    Log.v("check string", "Video Checkbox: " + HIA3_Test4_Question6);
                    //do string database
                    HIA3.HIA3_Test4_Question6=HIA3_Test4_Question6;
                }
            });


        }


        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

                HIA3_Test4_Question6 =other.getText().toString();
                Log.v("check string", "Video Checkbox: " + HIA3_Test4_Question6);
                //do string database
                HIA3.HIA3_Test4_Question6=HIA3_Test4_Question6;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (PreferenceConnector.gait_test_completed==1) {
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
//////////////Balance

        if (PreferenceConnector.doubleStatus) {
            dlResult.setText("Passed");
            dlptp.setText(String.valueOf(PreferenceConnector.balance_dl_PTP));
            dlAPRMS.setText(String.valueOf(PreferenceConnector.balance_dl_APRMS));
            dlMLRMS.setText(String.valueOf(PreferenceConnector.balance_dl_MLRMS));
        } else {
            dlResult.setText("Failed");
            dlResult.setTextColor(getResources().getColor(R.color.reset));
        }


        if (PreferenceConnector.singleStatus) {
            slResult.setText("Passed");
            slptp.setText(String.valueOf(PreferenceConnector.balance_sl_PTP));
            slAPRMS.setText(String.valueOf(PreferenceConnector.balance_sl_APRMS));
            slMLRMS.setText(String.valueOf(PreferenceConnector.balance_sl_MLRMS));
        } else {
            slResult.setText("Failed");
            slResult.setTextColor(getResources().getColor(R.color.reset));
        }


        if (PreferenceConnector.tandemStatus) {
            tsResult.setText("Passed");
            tsptp.setText(String.valueOf(PreferenceConnector.balance_ts_PTP));
            tsAPRMS.setText(String.valueOf(PreferenceConnector.balance_ts_APRMS));
            tsMLRMS.setText(String.valueOf(PreferenceConnector.balance_ts_MLRMS));
        } else {
            tsResult.setText("Failed");
            tsResult.setTextColor(getResources().getColor(R.color.reset));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        boolean checked2 = ((RadioButton) buttonView).isChecked();

        Activity c = getActivity();
        if(c instanceof HIA3AActivity) {
            hia3test = (HIA3AActivity) getActivity();
            switch (buttonView.getId()) {
                case R.id.checkBox3_CSY:
                    if (checked2) {
                        //HIA3_Test3_Question1 = true;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question1);
                        HIA3.HIA3_Test4_Question4=1;
                        Log.v("5", "Test: " + 1);
                        break;
                    } else {
                        //HIA3_Test3_Question1 = false;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question1);
                        HIA3.HIA3_Test4_Question4=0;
                        Log.v("5", "Test: " + 0);
                        break;
                    }

                case R.id.checkBox3_diag_concY:
                    if (checked2) {
                        //HIA3_Test3_Question3 = true;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question3);
                        HIA3.HIA3_Test4_Question5=1;
                        Log.v("6", "Test: " + 1);
                        break;
                    } else {
                        //HIA3_Test3_Question3 = false;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question3);
                        HIA3.HIA3_Test4_Question5=0;
                        Log.v("4", "Test: " + 0);
                        break;
                    }
            }
        }

    }
}
