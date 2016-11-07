package com.example.joshf.conc;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import static com.example.joshf.conc.R.id.textView_concresult;
        import static com.example.joshf.conc.R.id.textView_delmemresult;
        import static com.example.joshf.conc.R.id.textView_imedmemresult;
        import static com.example.joshf.conc.R.id.textView_orientationresult;
        import static com.example.joshf.conc.R.id.textView_totalresult;


public class HIA2EFragment extends Fragment {


    int total;
    public HIA2AActivity attempt;
    TextView o_result;
    String o_result_string, m_result_string,conc_result,del_result;
    int conc1,conc2;
    TextView textView, textView1, textView2,textView3;
    int tempval,val1,val2,HIA2_Test6_Question1;
    private EditText delmem;
    private static final String TAG = "Tag Check";
    String delmemstring;
    int delmemscore;
    //database
    public HIA2AActivity hia2test;

    public static HIA2EFragment newInstance() {
        HIA2EFragment fragment = new HIA2EFragment();
        return fragment;
    }

    public HIA2EFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_hia2_e, container, false);
        delmem = (EditText) rootView.findViewById(R.id.editText12);


        //o_result = (TextView) rootView.findViewById(R.id.textView_orientationresult);
        //o_result.setVisibility(View.GONE);
        //Log.v(TAG, "TEMPVAL: " + tempval);
        //o_result_string= String.valueOf(tempval);
        // o_result.setText(o_result_string);

        // String myTag = getTag();
        // Log.v(TAG, "Orien Tag: " + myTag);

        // ((HIA2AActivity)getActivity()).setTabFragmentB(myTag);
        //String delmemstring =delmem.getText().toString();
        //textView = (TextView) rootView.findViewById(textView_orientationresult);
        //Bundle bundle = getArguments();
        //Log.v(TAG, "Got here");
        //if (bundle!= null)
        //{
        //  textView.setText(bundle.getInt("pos"));
        // Log.v(TAG, "SET FLAG");
        //}

        Activity a = getActivity();
        if(a instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();

            delmem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try{
                        delmemstring = delmem.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + delmemstring);
                        if("".equals(delmemstring)){
                            HIA2_Test6_Question1 = Integer.parseInt("0");
                            hia2test.objHIA2.setHIA2_Test6_Question1(HIA2_Test6_Question1);
                        }
                        else{
                            HIA2_Test6_Question1 = Integer.parseInt(delmemstring);
                            hia2test.objHIA2.setHIA2_Test6_Question1(HIA2_Test6_Question1);
                            attempt.attemp1.delayedRecall= HIA2_Test6_Question1;
                        }
                    }
                    catch(NumberFormatException e){
                        //exception
                    }

                    //delmemscore = Integer.parseInt(delmemstring);
                    textView3 = (TextView) getView().findViewById(textView_delmemresult);
                    textView3.setText(delmemstring);


                    total = conc1 + val1 + val2 + HIA2_Test6_Question1;
                    Log.v("conc1:", ""+ conc1);
                    Log.v("val1:", ""+ conc1);
                    Log.v("val2:", ""+ conc1);
                    Log.v("conc1:", ""+ HIA2_Test6_Question1);
                    Log.v("total:", ""+ total);

                    String totalstring;
                    totalstring = String.valueOf(total);
                    textView2 = (TextView) getView().findViewById(textView_totalresult);
                    textView2.setText(totalstring);
                }
            });
        }
        return rootView;
    }

    public void updateOrienScore(int position) {

        // attempt = (HIA2AActivity) getActivity();
        //setText(attempt.attemp1.orientation);
        //Log.v(TAG, "TEMPVAL: " + position);
        //HIA2EFragment fraggy = new HIA2EFragment();
        /*TextView textView;
        textView = (TextView) getView().findViewById(textView_orientationresult);
        Log.v(TAG, "TEMPVAL tview: " + textView);
        textView.setVisibility(View.VISIBLE);
        o_result_string= String.valueOf(position);
        textView.setText(o_result_string);*/
        // Bundle args = new Bundle();
        // args.putInt("pos",position);
        //fraggy.setArguments(args);
        //tempval= position;


    }

    @Override
    public void onResume()
    {
        super.onResume();
        //crashes app when the other activities run

        Activity a = getActivity();
        // Log.v(TAG, "Video Checkbox: " + a);
        // attempt = (HIA2AActivity) getActivity();
        //  Log.v(TAG, "Video Checkbox: " + a);


        if(a instanceof HIA2AActivity)
        {
            attempt = (HIA2AActivity) getActivity();
            textView = (TextView) getView().findViewById(textView_orientationresult);
            o_result_string= String.valueOf(attempt.attemp1.orientation);
            textView.setText(o_result_string);
            val1 = Integer.parseInt(o_result_string);

            textView1 = (TextView) getView().findViewById(textView_imedmemresult);
            m_result_string = String.valueOf(attempt.attemp1.imedmem);
            textView1.setText(m_result_string);
            val2 = Integer.parseInt(m_result_string);

            textView2 = (TextView) getView().findViewById(textView_concresult);
            conc1 = attempt.attemp1.digback + attempt.attemp1.monthback;
            conc_result = String.valueOf(conc1);
            textView2.setText(conc_result);

            textView3 = (TextView) getView().findViewById(textView_delmemresult);
            attempt.attemp1.delayedRecall= HIA2_Test6_Question1;
            del_result=String.valueOf(attempt.attemp1.delayedRecall);
            textView3.setText(del_result);
            //hia2test.objHIA2.setHIA2_Test6_Question1(HIA2_Test6_Question1);

            attempt.attemp1.sacTotal = total;

        }

    }


}
