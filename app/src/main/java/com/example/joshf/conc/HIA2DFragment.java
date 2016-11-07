package com.example.joshf.conc;


        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
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

        import java.util.Random;


public class HIA2DFragment extends Fragment implements CheckBox.OnCheckedChangeListener {

    //database
    public HIA2AActivity hia2test;
    OnOrienSelectedListener orienCallback;

    String[] wordArray,wordArray1,wordArray2,wordArray3,wordArray4;
    int HIA2_Test4_Question6=0;
    int HIA2_Test4_Question1= 0;
    int HIA2_Test4_Question2= 0;
    int HIA2_Test4_Question3= 0;
    int HIA2_Test4_Question4= 0;
    int HIA2_Test4_Question5= 0;
    int HIA2_Test4_Question7;
    String string_orien;
    private EditText other;
    private static final String TAG = "Video Check";

    public interface OnOrienSelectedListener {
        public void onOrienSelected(int position);
        public void onImedMemSelected(int score);
    }

    public static HIA2DFragment newInstance() {
        HIA2DFragment fragment = new HIA2DFragment();
        return fragment;
    }

    public HIA2DFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia2_d, container, false);
        other = (EditText) rootView.findViewById(R.id.editText8);


        wordArray =   getResources().getStringArray(R.array.randomWords);
        wordArray1 =   getResources().getStringArray(R.array.randomWords1);
        wordArray2 =   getResources().getStringArray(R.array.randomWords2);
        wordArray3 =   getResources().getStringArray(R.array.randomWords3);
        wordArray4 =   getResources().getStringArray(R.array.randomWords4);

        //rand word generator
        Random rand = new Random();

        int num11 = rand.nextInt(10);
        int num12 = rand.nextInt(10);
        int num13 = rand.nextInt(10);
        int num14 = rand.nextInt(10);
        int num15 = rand.nextInt(10);

        TextView no_11 = (TextView)rootView.findViewById(R.id.textView43);
        no_11.setText(wordArray[num11] + "  ");
        TextView no_12 = (TextView)rootView.findViewById(R.id.textView42);
        no_12.setText(wordArray1[num12] + "  ");
        TextView no_13 = (TextView)rootView.findViewById(R.id.textView41);
        no_13.setText(wordArray2[num13] + "  ");
        TextView no_14 = (TextView)rootView.findViewById(R.id.textView40);
        no_14.setText(wordArray3[num14] + "  ");
        TextView no_15 = (TextView)rootView.findViewById(R.id.textView37);
        no_15.setText(wordArray4[num15] + "  ");

        int num21 = rand.nextInt(10);
        int num22 = rand.nextInt(10);
        int num23 = rand.nextInt(10);
        int num24 = rand.nextInt(10);
        int num25 = rand.nextInt(10);

        TextView no_21 = (TextView)rootView.findViewById(R.id.textView32);
        no_21.setText(wordArray[num21] + "  ");
        TextView no_22 = (TextView)rootView.findViewById(R.id.textView31);
        no_22.setText(wordArray1[num22] + "  ");
        TextView no_23 = (TextView)rootView.findViewById(R.id.textView29);
        no_23.setText(wordArray2[num23] + "  ");
        TextView no_24 = (TextView)rootView.findViewById(R.id.textView28);
        no_24.setText(wordArray3[num24] + "  ");
        TextView no_25 = (TextView)rootView.findViewById(R.id.textView27);
        no_25.setText(wordArray4[num25] + "  ");

        int num31 = rand.nextInt(10);
        int num32 = rand.nextInt(10);
        int num33 = rand.nextInt(10);
        int num34 = rand.nextInt(10);
        int num35 = rand.nextInt(10);

        TextView no_31 = (TextView)rootView.findViewById(R.id.textView01);
        no_31.setText(wordArray[num31] + "  ");
        TextView no_32 = (TextView)rootView.findViewById(R.id.textView02);
        no_32.setText(wordArray1[num32] + "  ");
        TextView no_33 = (TextView)rootView.findViewById(R.id.textView03);
        no_33.setText(wordArray2[num33] + "  ");
        TextView no_34 = (TextView)rootView.findViewById(R.id.textView04);
        no_34.setText(wordArray3[num34] + "  ");
        TextView no_35 = (TextView)rootView.findViewById(R.id.textView05);
        no_35.setText(wordArray4[num35] + "  ");

        RadioButton mButton = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_1);
        RadioButton mButton1 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_2);
        RadioButton mButton2 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_3);
        RadioButton mButton3 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_4);
        RadioButton mButton4 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_5);
        RadioButton mButton5 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_6);
        RadioButton mButton6 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_7);
        RadioButton mButton7 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_8);
        RadioButton mButton8 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_9);
        RadioButton mButton9 = (RadioButton) rootView.findViewById(R.id.checkBox_ORIEN_10);

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

        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();


            other.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try{
                        String mem_score = other.getText().toString();
                        HIA2_Test4_Question7 = Integer.parseInt(other.getText().toString());
                        if("".equals(mem_score)){
                            HIA2_Test4_Question7 = Integer.parseInt("0");
                            HIA2.HIA2_Test4_Question7=HIA2_Test4_Question7;
                            // orienCallback.onImedMemSelected(HIA2_Test4_Question7);
                        }
                        else{
                            HIA2.HIA2_Test4_Question7=HIA2_Test4_Question7;
                            Log.v(TAG, "Video Checkbox: " + mem_score);
                            orienCallback.onImedMemSelected(HIA2_Test4_Question7);
                        }
                    }
                    catch(NumberFormatException e){
                        //exception
                    }


                }
            });
        }

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();

                    try{
                        String mem_score = other.getText().toString();
                        HIA2_Test4_Question7 = Integer.parseInt(other.getText().toString());
                        if("".equals(mem_score)){
                            HIA2_Test4_Question7 = Integer.parseInt("0");
                            HIA2.HIA2_Test4_Question7=HIA2_Test4_Question7;
                            // orienCallback.onImedMemSelected(HIA2_Test4_Question7);
                        }
                        else{
                            HIA2.HIA2_Test4_Question7=HIA2_Test4_Question7;
                            Log.v(TAG, "Video Checkbox: " + mem_score);
                            orienCallback.onImedMemSelected(HIA2_Test4_Question7);
                        }
                    }
                    catch(NumberFormatException e){
                        //exception
                    }

        }

    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean checked2 = ((RadioButton) buttonView).isChecked();

        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();

            switch (buttonView.getId()) {
                case R.id.checkBox_ORIEN_1:
                    if (checked2) {
                        HIA2_Test4_Question1 = 1;
                        HIA2.HIA2_Test4_Question1=1;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;

                    } else {
                        HIA2_Test4_Question1 = 0;
                        HIA2.HIA2_Test4_Question1=0;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    }

                case R.id.checkBox_ORIEN_3:
                    if (checked2) {
                        HIA2_Test4_Question2 = 1;
                        HIA2.HIA2_Test4_Question2=1;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    } else {
                        HIA2_Test4_Question2 = 0;
                        HIA2.HIA2_Test4_Question2=0;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    }

                case R.id.checkBox_ORIEN_5:
                    if (checked2) {
                        HIA2_Test4_Question3 = 1;
                        HIA2.HIA2_Test4_Question3=1;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    } else {
                        HIA2_Test4_Question3 = 0;
                        HIA2.HIA2_Test4_Question3=0;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    }

                case R.id.checkBox_ORIEN_7:
                    if (checked2) {
                        HIA2_Test4_Question4 = 1;
                        HIA2.HIA2_Test4_Question4=1;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    } else {
                        HIA2_Test4_Question4 = 0;
                        HIA2.HIA2_Test4_Question4=0;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    }

                case R.id.checkBox_ORIEN_9:
                    if (checked2) {
                        HIA2_Test4_Question5 = 1;
                        HIA2.HIA2_Test4_Question5=1;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    } else {
                        HIA2_Test4_Question5 = 0;
                        HIA2.HIA2_Test4_Question5=0;
                        HIA2_Test4_Question6 = HIA2_Test4_Question1 + HIA2_Test4_Question2 + HIA2_Test4_Question3 + HIA2_Test4_Question4 + HIA2_Test4_Question5;
                        HIA2.HIA2_Test4_Question6=HIA2_Test4_Question6;
                        Log.v(TAG, "Purple Monkeys " + HIA2_Test4_Question6);
                        orienCallback.onOrienSelected(HIA2_Test4_Question6);
                        break;
                    }
            }
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            orienCallback = (OnOrienSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
