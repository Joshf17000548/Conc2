package com.example.joshf.conc;


        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.Random;


public class HIA2GFragment extends Fragment {

    OnConcSelectedListener concCallback;
    //database
    public HIA2AActivity hia2test;

    private EditText other;
    private EditText other2;
    int HIA2_Test5_Question1;
    int HIA2_Test5_Question2;
    private static final String TAG = "Video Check";
    public static HIA2GFragment newInstance() {
        HIA2GFragment fragment = new HIA2GFragment();
        return fragment;
    }

    public HIA2GFragment() {
    }

    public interface OnConcSelectedListener {
        public void onDigitBackSelected(int position);
        public void onMonthBackSelected(int score);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia2_g, container, false);
        other = (EditText) rootView.findViewById(R.id.editText16);
        other2 = (EditText) rootView.findViewById(R.id.editText15);

        //Rand number generator
        Random rand2 = new Random();

        int number101 = rand2.nextInt(10);int number102 = rand2.nextInt(10);int number103 = rand2.nextInt(10);

        int number104 = rand2.nextInt(10);int number105 = rand2.nextInt(10);int number106 = rand2.nextInt(10);
        int number107 = rand2.nextInt(10);

        int number108 = rand2.nextInt(10);int number109 = rand2.nextInt(10);int number110 = rand2.nextInt(10);
        int number111 = rand2.nextInt(10);int number112 = rand2.nextInt(10);

        int number113 = rand2.nextInt(10);int number114 = rand2.nextInt(10);int number115 = rand2.nextInt(10);
        int number116 = rand2.nextInt(10);int number117 = rand2.nextInt(10);int number118 = rand2.nextInt(10);

        int number201 = rand2.nextInt(10);int number202 = rand2.nextInt(10);int number203 = rand2.nextInt(10);

        int number204 = rand2.nextInt(10);int number205 = rand2.nextInt(10);int number206 = rand2.nextInt(10);
        int number207 = rand2.nextInt(10);

        int number208 = rand2.nextInt(10);int number209 = rand2.nextInt(10);int number210 = rand2.nextInt(10);
        int number211 = rand2.nextInt(10);int number212 = rand2.nextInt(10);

        int number213 = rand2.nextInt(10);int number214 = rand2.nextInt(10);int number215 = rand2.nextInt(10);
        int number216 = rand2.nextInt(10);int number217 = rand2.nextInt(10);int number218 = rand2.nextInt(10);

        TextView numero11 = (TextView)rootView.findViewById(R.id.textView46);
        String n11 = String.valueOf(number101).concat("-").concat(String.valueOf(number102)).
                concat("-").concat(String.valueOf(number103)).concat(" /");
        numero11.setText(n11);

        TextView numero12 = (TextView)rootView.findViewById(R.id.textView47);
        String n12 = String.valueOf(number104).concat("-").concat(String.valueOf(number105)).
                concat("-").concat(String.valueOf(number106)).concat("-").
                concat(String.valueOf(number107)).concat(" /");
        numero12.setText(n12);

        TextView numero13 = (TextView)rootView.findViewById(R.id.textView48);
        String n13 = String.valueOf(number108).concat("-").concat(String.valueOf(number109)).
                concat("-").concat(String.valueOf(number110)).concat("-").
                concat(String.valueOf(number111)).concat("-").
                concat(String.valueOf(number112)).concat(" /");
        numero13.setText(n13);

        TextView numero14 = (TextView)rootView.findViewById(R.id.textView49);
        String n14 = String.valueOf(number113).concat("-").concat(String.valueOf(number114)).
                concat("-").concat(String.valueOf(number115)).concat("-").
                concat(String.valueOf(number116)).concat("-").concat(String.valueOf(number117)).
                concat("-").concat(String.valueOf(number118));
        numero14.setText(n14);

        TextView numero21 = (TextView)rootView.findViewById(R.id.textView53);
        String n21 = String.valueOf(number201).concat("-").concat(String.valueOf(number202)).
                concat("-").concat(String.valueOf(number203)).concat(" /");
        numero21.setText(n21);

        TextView numero22 = (TextView)rootView.findViewById(R.id.textView52);
        String n22 = String.valueOf(number204).concat("-").concat(String.valueOf(number205)).
                concat("-").concat(String.valueOf(number206)).concat("-").
                concat(String.valueOf(number207)).concat(" /");
        numero22.setText(n22);

        TextView numero23 = (TextView)rootView.findViewById(R.id.textView51);
        String n23 = String.valueOf(number208).concat("-").concat(String.valueOf(number209)).
                concat("-").concat(String.valueOf(number210)).concat("-").
                concat(String.valueOf(number211)).concat("-").
                concat(String.valueOf(number212)).concat(" /");
        numero23.setText(n23);

        TextView numero24 = (TextView)rootView.findViewById(R.id.textView50);
        String n24 = String.valueOf(number213).concat("-").concat(String.valueOf(number214)).
                concat("-").concat(String.valueOf(number215)).concat("-").
                concat(String.valueOf(number216)).concat("-").concat(String.valueOf(number217)).
                concat("-").concat(String.valueOf(number218));
        numero24.setText(n24);

/*        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();*/


            other.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try{
                        String dig_back_score = other.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + dig_back_score);
                        HIA2_Test5_Question1 = Integer.parseInt(other.getText().toString());
                        if("".equals(dig_back_score)){
                            HIA2_Test5_Question1 = Integer.parseInt("0");
                            HIA2.HIA2_Test5_Question1=HIA2_Test5_Question1;
                            concCallback.onDigitBackSelected(HIA2_Test5_Question1);
                        }
                        else{
                            HIA2.HIA2_Test5_Question1=HIA2_Test5_Question1;
                            concCallback.onDigitBackSelected(HIA2_Test5_Question1);
                        }

                    }
                    catch(NumberFormatException e){
                        //exception
                    }

                }
            });

            other2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try{
                        String conc_score = other2.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + conc_score);
                        HIA2_Test5_Question2 = Integer.parseInt(other2.getText().toString());
                        if("".equals(conc_score)){
                            HIA2_Test5_Question2 = Integer.parseInt("0");
                            HIA2.HIA2_Test5_Question2=HIA2_Test5_Question2;
                            concCallback.onMonthBackSelected(HIA2_Test5_Question2);
                        }
                        else{
                            HIA2.HIA2_Test5_Question2=HIA2_Test5_Question2;
                            concCallback.onMonthBackSelected(HIA2_Test5_Question2);
                        }

                    }
                    catch(NumberFormatException e){
                        //exception
                    }

                }
            });

        //}

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
/*        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();*/


                    try{
                        String dig_back_score = other.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + dig_back_score);
                        HIA2_Test5_Question1 = Integer.parseInt(other.getText().toString());
                        if("".equals(dig_back_score)){
                            HIA2_Test5_Question1 = Integer.parseInt("0");
                            HIA2.HIA2_Test5_Question1=HIA2_Test5_Question1;
                            concCallback.onDigitBackSelected(HIA2_Test5_Question1);
                        }
                        else{
                            HIA2.HIA2_Test5_Question1=HIA2_Test5_Question1;
                            concCallback.onDigitBackSelected(HIA2_Test5_Question1);
                        }

                    }
                    catch(NumberFormatException e){
                        //exception
                    }

                    try{
                        String conc_score = other2.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + conc_score);
                        HIA2_Test5_Question2 = Integer.parseInt(other2.getText().toString());
                        if("".equals(conc_score)){
                            HIA2_Test5_Question2 = Integer.parseInt("0");
                            HIA2.HIA2_Test5_Question2=HIA2_Test5_Question2;
                            concCallback.onMonthBackSelected(HIA2_Test5_Question2);
                        }
                        else{
                            HIA2.HIA2_Test5_Question2=HIA2_Test5_Question2;
                            concCallback.onMonthBackSelected(HIA2_Test5_Question2);
                        }

                    }
                    catch(NumberFormatException e){
                        //exception
                    }



       // }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            concCallback = (HIA2GFragment.OnConcSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}