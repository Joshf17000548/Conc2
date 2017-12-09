package com.example.joshf.conc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

/**
 * Created by joshf on 2016/11/07.
 */

public class HIA2Results extends Fragment implements CheckBox.OnCheckedChangeListener {

    int HIA_test;

    static int MQrequired = 5;
    static int IMRrequired = 12;
    static int DBrequired = 3;
    static int TGTrequired = 14000;
    static int TGALRMSrequired = 5;
    static int TGMPRMSrequired = 5;
    static int DRrequired = 3;
    static int DMRrequired = 3;
    static int MRrequired = 1;
    static int ULrequired =1;

    int maddocksResult;

    TextView MQresults;
    TextView IMRresults;
    TextView DBresults;
    TextView DMRresults;
    TextView SYMnumber;
    TextView SYMtotal;
    TextView MRtotal;

    TextView tandemResult;
    TextView tandemTime;
    TextView tandemAPRMS;
    TextView tandemMPRMS;

    TextView dlResult;
    TextView dlTime;
    TextView dlptp;
    TextView dlAPRMS;
    TextView dlMLRMS;

    TextView slResult;
    TextView slTime;
    TextView slptp;
    TextView slAPRMS;
    TextView slMLRMS;

    TextView tsResult;
    TextView tsTime;
    TextView tsptp;
    TextView tsAPRMS;
    TextView tsMLRMS;

    TextView SYMtestResult;

    TextView upperResult;

    TextView IMRbaseline;
    TextView DBbaseline;
    TextView DMRbaseline;
    TextView SYMnumberbaseline;
    TextView SYMtotalbaseline;
    TextView MRbaseline;

    TextView tandemTimebaseline;
    TextView tandemAPRMSbaseline;
    TextView tandemMPRMSbaseline;

    TextView dlptpbaseline;
    TextView dlAPRMSbaseline;
    TextView dlMLRMSbaseline;

    TextView slptpbaseline;
    TextView slAPRMSbaseline;
    TextView slMLRMSbaseline;

    TextView tsptpbaseline;
    TextView tsAPRMSbaseline;
    TextView tsMLRMSbaseline;

    RadioButton diagnosis;



    Spinner result;
    private ArrayAdapter<CharSequence> adapter3;


    public static HIA2Results newInstance() {
        HIA2Results fragment = new HIA2Results();
        return fragment;
    }

    public HIA2Results() {
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {


            upperResult.setText(String.valueOf(PreferenceConnector.upper_score));
            if (PreferenceConnector.upper_score < ULrequired)
                upperResult.setTextColor(getResources().getColor(R.color.reset));

        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia2_results, container, false);
        //  Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_team);
        //  toolbar.setLogo(R.drawable.logo);
        //getActivity().setSupportActionBar(toolbar);

        SYMtestResult = (TextView) rootView.findViewById(R.id.SYMtestResult);

        MQresults = (TextView) rootView.findViewById(R.id.MQresult);
        IMRresults = (TextView) rootView.findViewById(R.id.IMRresult);
        DBresults = (TextView) rootView.findViewById(R.id.DBresult);
        DMRresults = (TextView) rootView.findViewById(R.id.DMRresult);
        SYMtotal = (TextView) rootView.findViewById(R.id.SYMtotResult);
        SYMnumber = (TextView) rootView.findViewById(R.id.SYMnumResult);
        MRtotal = (TextView) rootView.findViewById(R.id.MRresult);

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

        upperResult = (TextView) rootView.findViewById(R.id.ULResult);

        IMRbaseline = (TextView) rootView.findViewById(R.id.IMRbaseline);
        DBbaseline = (TextView) rootView.findViewById(R.id.DBbaseline);
        DMRbaseline = (TextView) rootView.findViewById(R.id.DMRbaseline);

        tandemTimebaseline = (TextView) rootView.findViewById(R.id.TGTimeBaseline);
        tandemAPRMSbaseline = (TextView) rootView.findViewById(R.id.TGalrmsBaseline);
        tandemMPRMSbaseline = (TextView) rootView.findViewById(R.id.TGmlrmsBaseline);

        SYMnumberbaseline=(TextView) rootView.findViewById(R.id.SYMnumBaseline);
        SYMtotalbaseline=(TextView) rootView.findViewById(R.id.SYMtotBaseline);

        dlptpbaseline=(TextView) rootView.findViewById(R.id.DLptpBaseline);;
        dlAPRMSbaseline=(TextView) rootView.findViewById(R.id.DLalrmsBaseline);;
        dlMLRMSbaseline=(TextView) rootView.findViewById(R.id.DLmprmsBaseline);;

        slptpbaseline=(TextView) rootView.findViewById(R.id.SLptpBaseline);;
        slAPRMSbaseline=(TextView) rootView.findViewById(R.id.SLalrmsBaseline);;
        slMLRMSbaseline=(TextView) rootView.findViewById(R.id.SLmprmsBaseline);;

        tsptpbaseline=(TextView) rootView.findViewById(R.id.TSptpBaseline);;
        tsAPRMSbaseline=(TextView) rootView.findViewById(R.id.TSalrmsBaseline);;
        tsMLRMSbaseline=(TextView) rootView.findViewById(R.id.TSmprmsBaseline);;


        diagnosis = (RadioButton) rootView.findViewById(R.id.CC);
        diagnosis.setOnCheckedChangeListener(this);

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
//////////////// Baseline

        if(Baseline.Baseline_Immediate_Memory!=0)
            IMRbaseline.setText(String.valueOf(Baseline.Baseline_Immediate_Memory));

        if(Baseline.Baseline_Digits_Backwards!=0)
            DBbaseline.setText(String.valueOf(Baseline.Baseline_Digits_Backwards));

        if(Baseline.Baseline_Delayed_Memory!=0)
            DMRbaseline.setText(String.valueOf(Baseline.Baseline_Delayed_Memory));

        if(Baseline.Baseline_Number_of_Symptoms!=0)
            SYMnumberbaseline.setText(String.valueOf(Baseline.Baseline_Number_of_Symptoms));

        if(Baseline.Baseline_Total_Symptoms!=0)
            SYMtotalbaseline.setText(String.valueOf(Baseline.Baseline_Total_Symptoms));

        if(Baseline.Baseline_Tandem_Time!=0)
            tandemTimebaseline.setText(String.valueOf(Baseline.Baseline_Tandem_Time));

        if(Baseline.Baseline_Tandem_AP!=0)
            tandemAPRMSbaseline.setText(String.valueOf(Baseline.Baseline_Tandem_AP));

        if(Baseline.Baseline_Tandem_ML!=0)
            tandemMPRMSbaseline.setText(String.valueOf(Baseline.Baseline_Tandem_ML));

        if(Baseline.Baseline_DL_PTP!=0)
            dlptpbaseline.setText(String.valueOf(Baseline.Baseline_DL_PTP));

        if(Baseline.Baseline_DL_AP!=0)
            dlAPRMSbaseline.setText(String.valueOf(Baseline.Baseline_DL_AP));

        if(Baseline.Baseline_DL_ML!=0)
            dlMLRMSbaseline.setText(String.valueOf(Baseline.Baseline_DL_ML));

        if(Baseline.Baseline_SL_PTP!=0)
            slptpbaseline.setText(String.valueOf(Baseline.Baseline_SL_PTP));

        if(Baseline.Baseline_SL_AP!=0)
            slAPRMSbaseline.setText(String.valueOf(Baseline.Baseline_SL_AP));

        if(Baseline.Baseline_SL_ML!=0)
            slMLRMSbaseline.setText(String.valueOf(Baseline.Baseline_SL_ML));

        if(Baseline.Baseline_TS_PTP!=0)
            tsptpbaseline.setText(String.valueOf(Baseline.Baseline_TS_PTP));

        if(Baseline.Baseline_TS_AP!=0)
            tsAPRMSbaseline.setText(String.valueOf(Baseline.Baseline_TS_AP));

        if(Baseline.Baseline_TS_ML!=0)
            tsMLRMSbaseline.setText(String.valueOf(Baseline.Baseline_TS_ML));

////////////////Moddocks Questions
        maddocksResult = HIA2.HIA2_Test4_Question1 + HIA2.HIA2_Test4_Question2 + HIA2.HIA2_Test4_Question3 + HIA2.HIA2_Test4_Question4 + HIA2.HIA2_Test4_Question5;
        MQresults.setText(String.valueOf(maddocksResult));
        if (maddocksResult < MQrequired)
            MQresults.setTextColor(getResources().getColor(R.color.reset));

//////////////Imediate Memory Recall
        IMRresults.setText(String.valueOf(HIA2.HIA2_Test4_Question7));
        if ((HIA2.HIA2_Test4_Question7 < IMRrequired))
            IMRresults.setTextColor(getResources().getColor(R.color.reset));
        if((HIA2.HIA2_Test4_Question7 < Baseline.Baseline_Immediate_Memory)&&(Baseline.Baseline_Immediate_Memory!=0))
            IMRresults.setTextColor(getResources().getColor(R.color.reset));

//////////////Digits Backwards
        DBresults.setText(String.valueOf(HIA2.HIA2_Test5_Question1));
        if ((HIA2.HIA2_Test5_Question1 < DBrequired))
            DBresults.setTextColor(getResources().getColor(R.color.reset));
        if((Baseline.Baseline_Immediate_Memory!=0)&&(HIA2.HIA2_Test5_Question1 < Baseline.Baseline_Digits_Backwards))
            DBresults.setTextColor(getResources().getColor(R.color.reset));

/////////////Tandem Gait

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



/////////////Symptom Checklist

        int totalSymp = 0;
        int total = HIA2.HIA2_Test2_Question1 + HIA2.HIA2_Test2_Question2 + HIA2.HIA2_Test2_Question3 + HIA2.HIA2_Test2_Question4
                + HIA2.HIA2_Test2_Question5 + HIA2.HIA2_Test2_Question6 + HIA2.HIA2_Test2_Question7 + HIA2.HIA2_Test2_Question8
                + HIA2.HIA2_Test2_Question9 + HIA2.HIA2_Test2_Question10 + HIA2.HIA2_Test2_Question11 + HIA2.HIA2_Test2_Question12
                + HIA2.HIA2_Test2_Question13 + HIA2.HIA2_Test2_Question14 + HIA2.HIA2_Test2_Question15 + HIA2.HIA2_Test2_Question16
                + HIA2.HIA2_Test2_Question17 + HIA2.HIA2_Test2_Question18 + HIA2.HIA2_Test2_Question19 + HIA2.HIA2_Test2_Question20
                + HIA2.HIA2_Test2_Question21 + HIA2.HIA2_Test2_Question22;

        SYMtotal.setText(String.valueOf(total));
        if((total<Baseline.Baseline_Total_Symptoms)&&(Baseline.Baseline_Total_Symptoms!=0))
            SYMtotal.setTextColor(getResources().getColor(R.color.reset));

        if (HIA2.HIA2_Test2_Question1 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question2 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question3 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question4 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question5 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question6 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question7 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question8 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question9 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question10 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question11 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question12 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question13 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question14 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question15 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question16 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question17 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question18 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question19 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question20 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question21 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question22 != 0) {
            totalSymp = totalSymp + 1;
        }

        SYMnumber.setText(String.valueOf(totalSymp));
        if((totalSymp<Baseline.Baseline_Number_of_Symptoms)&&(Baseline.Baseline_Number_of_Symptoms!=0))
            SYMnumber.setTextColor(getResources().getColor(R.color.reset));


/////////////Delayed Recall
        DMRresults.setText(String.valueOf(HIA2.HIA2_Test6_Question1));
        if ((HIA2.HIA2_Test6_Question1 < DMRrequired))
            DMRresults.setTextColor(getResources().getColor(R.color.reset));
        if((HIA2.HIA2_Test6_Question1<Baseline.Baseline_Delayed_Memory)&&(Baseline.Baseline_Delayed_Memory!=0))
            DMRresults.setTextColor(getResources().getColor(R.color.reset));

/////////////Month Reverse

        MRtotal.setText(String.valueOf(HIA2.HIA2_Test5_Question2));
        if (HIA2.HIA2_Test5_Question2 < MRrequired)
            MRtotal.setTextColor(getResources().getColor(R.color.reset));

/////////////Upper





    }
    ////////////result
    @Override
    public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
        boolean checked2 = ((RadioButton) buttonView).isChecked();

        Log.e("result","click");

            switch (buttonView.getId()) {
                case R.id.CC:

                    if (checked2) {
                        Log.e("result","CC");
                        HIA2.HIA2_result =1;
                        HIA2.HIA2_Result_Chosen=true;
                    }
/*                case R.id.CNC:
                    if (checked2) {
                        Log.e("result","CNC");
                        HIA2.HIA2_result =false;
                        HIA2.HIA2_Result_Chosen=true;
                    }*/
            }
        }

}