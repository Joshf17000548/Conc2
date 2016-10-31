package com.example.joshf.conc;


        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.RadioButton;


public class HIA1DFragment extends Fragment implements CheckBox.OnCheckedChangeListener {

    //database
    public HIA1AActivity hia1test;

    int HIA1_Test5_Question1;
    int HIA1_Test5_Question2;
    int HIA1_Test5_Question3;
    int HIA1_Test5_Question4;
    int HIA1_Test5_Question5;
    int HIA1_Test5_Question6;
    int HIA1_Test5_Question7;
    int HIA1_Test5_Question8;
    int HIA1_Test5_Question9;
    private static final String TAG = "Video Check";
    int sym_tally=0;

    public static HIA1DFragment newInstance() {
        HIA1DFragment fragment = new HIA1DFragment();
        return fragment;
    }

    public HIA1DFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_d, container, false);

        RadioButton mButton = (RadioButton) rootView.findViewById(R.id.checkBox_Symp22_y);
        RadioButton mButton1 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp22_n);
        RadioButton mButton2 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp23_y);
        RadioButton mButton3 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp23_n);
        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp24_y);
        RadioButton mButton5 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp24_n);
        RadioButton mButton6 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp25_y);
        RadioButton mButton7 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp25_n);
        RadioButton mButton8 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp26_y);
        RadioButton mButton9 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp26_n);
        RadioButton mButton10 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp27_y);
        RadioButton mButton11 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp27_n);
        RadioButton mButton12 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp28_y);
        RadioButton mButton13 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp28_n);
        RadioButton mButton14 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp29_y);
        RadioButton mButton15 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp29_n);
        RadioButton mButton16 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp210_y);
        RadioButton mButton17 = (RadioButton) rootView.findViewById(R.id.checkBox_Symp210_n);

        mButton.setOnCheckedChangeListener(this);
        mButton1.setOnCheckedChangeListener(this);
        mButton2.setOnCheckedChangeListener(this);
        mButton3.setOnCheckedChangeListener(this);
        mButton4.setOnCheckedChangeListener(this);
        mButton5.setOnCheckedChangeListener(this);
        mButton6.setOnCheckedChangeListener(this);
        mButton7.setOnCheckedChangeListener(this);
        mButton8.setOnCheckedChangeListener(this);
        mButton9.setOnCheckedChangeListener(this);
        mButton10.setOnCheckedChangeListener(this);
        mButton11.setOnCheckedChangeListener(this);
        mButton12.setOnCheckedChangeListener(this);
        mButton13.setOnCheckedChangeListener(this);
        mButton14.setOnCheckedChangeListener(this);
        mButton15.setOnCheckedChangeListener(this);
        mButton16.setOnCheckedChangeListener(this);
        mButton17.setOnCheckedChangeListener(this);

        return rootView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean checked = ((RadioButton) buttonView).isChecked();

        Activity a = getActivity();

        if(a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            switch (buttonView.getId()) {
                case R.id.checkBox_Symp22_y:
                    if (checked) {
                        HIA1_Test5_Question1 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question1(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question1 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question1 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question1(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question1 + " tally: " + sym_tally);
                        break;
                    }
                case R.id.checkBox_Symp23_y:
                    if (checked) {
                        HIA1_Test5_Question2 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question2(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question2 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question2 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question2(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question2 + " tally: " + sym_tally);
                        break;
                    }
                case R.id.checkBox_Symp24_y:
                    if (checked) {
                        HIA1_Test5_Question3 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question3(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question3 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question3 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question3(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question3 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp25_y: //+2
                    if (checked) {
                        HIA1_Test5_Question4 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question4(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question4 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question4 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question4(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question4 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp26_y:
                    if (checked) {
                        HIA1_Test5_Question5 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question5(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question5 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question5 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question5(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question5 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp27_y:
                    if (checked) {
                        HIA1_Test5_Question6 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question6(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question6 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question6 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question6(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question6 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp28_y:
                    if (checked) {
                        HIA1_Test5_Question7 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question7(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question7 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question7 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question3(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question7 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp29_y:
                    if (checked) {
                        HIA1_Test5_Question8 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question8(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question8 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question8 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question3(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question8 + " tally: " + sym_tally);
                        break;
                    }

                case R.id.checkBox_Symp210_y:
                    if (checked) {
                        HIA1_Test5_Question9 = 1;
                        hia1test.objHIA1.setHIA1_Test5_Question9(1);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question9 + " tally: " + sym_tally);
                        break;
                    } else {
                        HIA1_Test5_Question9 = 0;
                        hia1test.objHIA1.setHIA1_Test5_Question3(0);
                        sym_tally = HIA1_Test5_Question1 + HIA1_Test5_Question2 + HIA1_Test5_Question3 +
                                HIA1_Test5_Question4 + HIA1_Test5_Question5 + HIA1_Test5_Question6 +
                                HIA1_Test5_Question7 + HIA1_Test5_Question8 + HIA1_Test5_Question9;
                        Log.v(TAG, "Mad Checkbox: " + HIA1_Test5_Question9 + " tally: " + sym_tally);
                        break;
                    }

            }
        }

    }
}
