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
        import android.widget.RadioButton;
        import android.widget.TextView;


public class HIA3EFragment extends Fragment implements CheckBox.OnCheckedChangeListener {

    //database
    public HIA3AActivity hia3test;

    public static HIA3EFragment newInstance() {
        HIA3EFragment fragment = new HIA3EFragment();
        return fragment;
    }

    public HIA3EFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia3_e, container, false);
        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox3_WCCY);

        mButton4.setOnCheckedChangeListener(this);

        final CheckBox mButton = (CheckBox) rootView.findViewById(R.id.checkBox3_ABASS1);
        final CheckBox mButton1 = (CheckBox) rootView.findViewById(R.id.checkBox_ABASS2);
        final CheckBox mButton2 = (CheckBox) rootView.findViewById(R.id.checkBox3_ABASS3);
        final CheckBox mButton3 = (CheckBox) rootView.findViewById(R.id.checkBox_ABASS4);

        Activity c = getActivity();
        if(c instanceof HIA3AActivity) {
            hia3test = (HIA3AActivity) getActivity();

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton.isChecked();
                    if (checked2) {
                        //HIA3_Test3_Question1 = true;
                        hia3test.objHIA3.setHIA3_Test5_Question2(1);
                        Log.v("2", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question1 = false;
                        hia3test.objHIA3.setHIA3_Test5_Question2(0);
                        Log.v("2", "Test: " + 0);
                    }
                }
            });

            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton1.isChecked();
                    if (checked2) {
                        //HIA1_Test2_Question2 = true;
                        hia3test.objHIA3.setHIA3_Test5_Question3(1);
                        Log.v("3", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question2 = false;
                        hia3test.objHIA3.setHIA3_Test5_Question3(0);
                        Log.v("3", "Test: " + 0);
                    }
                }
            });

            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton2.isChecked();
                    if (checked2) {
                        //HIA1_Test2_Question3 = true;
                        hia3test.objHIA3.setHIA3_Test5_Question4(1);
                        Log.v("4", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question3 = false;
                        hia3test.objHIA3.setHIA3_Test5_Question4(0);
                        Log.v("4", "Test: " + 0);
                    }
                }
            });

            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked2 = mButton3.isChecked();
                    if (checked2) {
                        // HIA1_Test2_Question4 = true;
                        hia3test.objHIA3.setHIA3_Test5_Question5(1);
                        Log.v("5", "Test: " + 1);
                    } else {
                        //HIA1_Test2_Question4 = false;
                        hia3test.objHIA3.setHIA3_Test5_Question5(0);
                        Log.v("5", "Test: " + 0);
                    }
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
                case R.id.checkBox3_WCCY:
                    if (checked2) {
                        //HIA3_Test3_Question1 = true;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question1);
                        hia3test.objHIA3.setHIA3_Test5_Question1(1);
                        Log.v("1", "Test: " + 1);
                        break;
                    } else {
                        //HIA3_Test3_Question1 = false;
                        //Log.v(TAG, "Purple Monkeys " + HIA3_Test3_Question1);
                        hia3test.objHIA3.setHIA3_Test5_Question1(0);
                        Log.v("1", "Test: " + 0);
                        break;
                    }
            }
        }
    }
}
