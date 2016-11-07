package com.example.joshf.conc;

        import android.app.Activity;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia3_d, container, false);

        final CheckBox mButton = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM1);
        final CheckBox mButton1 = (CheckBox) rootView.findViewById(R.id.checkBox_DOM2);
        final CheckBox mButton2 = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM3);
        //final CheckBox mButton3 = (CheckBox) rootView.findViewById(R.id.checkBox3_DOM4);

        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox3_CSY);
        RadioButton mButton5 = (RadioButton) rootView.findViewById(R.id.checkBox3_diag_concY);

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
                        hia3test.objHIA3.setHIA3_Test4_Question1(1);
                        Log.v("1", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question1 = false;
                        hia3test.objHIA3.setHIA3_Test4_Question1(0);
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
                        hia3test.objHIA3.setHIA3_Test4_Question2(1);
                        Log.v("2", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question2 = false;
                        hia3test.objHIA3.setHIA3_Test4_Question2(0);
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
                        hia3test.objHIA3.setHIA3_Test4_Question3(1);
                        Log.v("3", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question3 = false;
                        hia3test.objHIA3.setHIA3_Test4_Question3(0);
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
                    hia3test.objHIA3.setHIA3_Test4_Question6(HIA3_Test4_Question6);
                }
            });


        }


        return rootView;
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
                        hia3test.objHIA3.setHIA3_Test4_Question4(1);
                        Log.v("5", "Test: " + 1);
                        break;
                    } else {
                        //HIA3_Test3_Question1 = false;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question1);
                        hia3test.objHIA3.setHIA3_Test4_Question4(0);
                        Log.v("5", "Test: " + 0);
                        break;
                    }

                case R.id.checkBox3_diag_concY:
                    if (checked2) {
                        //HIA3_Test3_Question3 = true;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question3);
                        hia3test.objHIA3.setHIA3_Test4_Question5(1);
                        Log.v("6", "Test: " + 1);
                        break;
                    } else {
                        //HIA3_Test3_Question3 = false;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question3);
                        hia3test.objHIA3.setHIA3_Test4_Question4(1);
                        Log.v("4", "Test: " + 0);
                        break;
                    }
            }
        }

    }
}
