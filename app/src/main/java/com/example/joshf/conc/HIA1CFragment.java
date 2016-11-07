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


public class HIA1CFragment extends Fragment implements CheckBox.OnCheckedChangeListener {
    private static final String TAG = "Video Check";
    int HIA1_Test3_Question1=0;
    int HIA1_Test3_Question2 = 0;
    int HIA1_Test3_Question3=0;
    int HIA1_Test3_Question4=0;
    int HIA1_Test3_Question5=0;
    int madTot=0;
    //database
    public HIA1AActivity hia1test;

    public static HIA1CFragment newInstance() {
        HIA1CFragment fragment = new HIA1CFragment();
        return fragment;
    }

    public HIA1CFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_c, container, false);

        RadioButton mButton = (RadioButton) rootView.findViewById(R.id.checkBox_MADD1_INC);
        RadioButton mButton1 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD1_COR);
        RadioButton mButton2 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD2_INC);
        RadioButton mButton3 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD2_COR);
        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD3_INC);
        RadioButton mButton5 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD3_COR);
        RadioButton mButton6 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD4_INC);
        RadioButton mButton7 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD4_COR);
        RadioButton mButton8 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD5_INC);
        RadioButton mButton9 = (RadioButton) rootView.findViewById(R.id.checkBox_MADD5_COR);

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

        return rootView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        boolean checked2 = ((RadioButton) buttonView).isChecked();
        Activity a = getActivity();

        if(a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            switch (buttonView.getId()) {
                case R.id.checkBox_MADD1_COR:
                    if (checked2) {
                        HIA1_Test3_Question1 = 1;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question1=1;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    } else {
                        HIA1_Test3_Question1 = 0;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question1=0;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    }

                case R.id.checkBox_MADD2_COR:
                    if (checked2) {
                        HIA1_Test3_Question2 = 1;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question2=1;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    } else {
                        HIA1_Test3_Question2 = 0;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question2=0;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    }

                case R.id.checkBox_MADD3_COR:
                    if (checked2) {
                        HIA1_Test3_Question3 = 1;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question3=1;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    } else {
                        HIA1_Test3_Question3 = 0;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question3=0;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    }

                case R.id.checkBox_MADD4_COR:
                    if (checked2) {
                        HIA1_Test3_Question4 = 1;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question4=1;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    } else {
                        HIA1_Test3_Question4 = 0;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question4=0;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    }

                case R.id.checkBox_MADD5_COR:
                    if (checked2) {
                        HIA1_Test3_Question5 = 1;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question5=1;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    } else {
                        HIA1_Test3_Question5 = 0;
                        madTot = HIA1_Test3_Question1 + HIA1_Test3_Question4 + HIA1_Test3_Question2 + HIA1_Test3_Question3 + HIA1_Test3_Question5;
                        HIA1.HIA1_Test3_Question5=0;
                        Log.v(TAG, "Purple Monkeys " + madTot);

                        break;
                    }

            }
        }

    }
}
