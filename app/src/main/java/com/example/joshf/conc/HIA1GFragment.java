package com.example.joshf.conc;


        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.RadioButton;
        import android.widget.Spinner;


public class HIA1GFragment extends Fragment implements AdapterView.OnItemSelectedListener, CheckBox.OnCheckedChangeListener {
    private static final String TAG = "Video Check";

    //database
    public HIA1AActivity hia1test;

    public static HIA1GFragment newInstance() {
        HIA1GFragment fragment = new HIA1GFragment();
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

    public HIA1GFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_g, container, false);

        Spinner spinner1 = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia1_f_spinner1 ,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia1_f_spinner2 ,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        RadioButton mButton = (RadioButton) rootView.findViewById(R.id.checkBox_VI_Y);
        RadioButton mButton1 = (RadioButton) rootView.findViewById(R.id.checkBox_MDD_Y);


        mButton.setOnCheckedChangeListener(this);
        mButton1.setOnCheckedChangeListener(this);




        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Activity a = getActivity();

        if(a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();
            switch (parent.getId()) {
                case R.id.spinner:
                    Log.v(TAG, "Video Checkbox0: " + position);
                    HIA1.HIA1_Test7_Question1=position;
                    return;
                case R.id.spinner2:
                    Log.v(TAG, "Video Checkbox1: " + position);
                    HIA1.HIA1_Test7_Question3=position;
                    return;

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean checked = ((RadioButton) buttonView).isChecked();
        Activity a = getActivity();

        if(a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            switch (buttonView.getId()) {
                case R.id.checkBox_VI_Y:
                    if (checked) {
                        HIA1.HIA1_Test7_Question2=1;
                        Log.v(TAG, "Check Check Ckeck: " + hia1test.objHIA1.HIA1_Test1_Question1);
                        break;
                    } else {
                        HIA1.HIA1_Test7_Question1=0;
                        Log.v(TAG, "Mad Checkbox: " + 0);
                        break;
                    }

                case R.id.checkBox_MDD_Y:
                    if (checked) {
                        HIA1.HIA1_Test7_Question4=1;
                        Log.v(TAG, "Mad Checkbox: " + 1);
                        break;
                    } else {
                        HIA1.HIA1_Test7_Question4=0;
                        Log.v(TAG, "Mad Checkbox: " + 0);
                        break;
                    }

            }
        }
    }
}