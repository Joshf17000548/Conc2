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


public class HIA1GFragment extends Fragment {

    //database
    public HIA1AActivity hia1test;

    String[] wordArray,wordArray1,wordArray2,wordArray3,wordArray4;
    private EditText memscore;
    int HIA1_Test4_Question1;
    private EditText digback;
    int HIA1_Test4_Question2;
    private static final String TAG = "Video Check";


    public static HIA1GFragment newInstance() {
        HIA1GFragment fragment = new HIA1GFragment();
        return fragment;
    }

    public HIA1GFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_g, container, false);
        memscore = (EditText) rootView.findViewById(R.id.editText11);
        digback = (EditText) rootView.findViewById(R.id.editText12);

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

        TextView no_31 = (TextView)rootView.findViewById(R.id.textView1);
        no_31.setText(wordArray[num31] + "  ");
        TextView no_32 = (TextView)rootView.findViewById(R.id.textView2);
        no_32.setText(wordArray1[num32] + "  ");
        TextView no_33 = (TextView)rootView.findViewById(R.id.textView3);
        no_33.setText(wordArray2[num33] + "  ");
        TextView no_34 = (TextView)rootView.findViewById(R.id.textView4);
        no_34.setText(wordArray3[num34] + "  ");
        TextView no_35 = (TextView)rootView.findViewById(R.id.textView5);
        no_35.setText(wordArray4[num35] + "  ");

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

        Activity a = getActivity();

        if(a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            memscore.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    try {
                        String memscorestring = memscore.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + memscorestring);
                        if ("".equals(memscorestring)){
                            HIA1_Test4_Question1 = Integer.parseInt("0");
                            hia1test.objHIA1.setHIA1_Test4_Question1(HIA1_Test4_Question1);
                        }
                        else{
                            HIA1_Test4_Question1 = Integer.parseInt(memscorestring);
                            hia1test.objHIA1.setHIA1_Test4_Question1(HIA1_Test4_Question1);
                        }

                    }
                    catch (NumberFormatException e)
                    {
                        //exception
                    }

                }
            });

            digback.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try{
                        String digbackstring = digback.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + digbackstring);
                        if ("".equals(digbackstring)){
                            HIA1_Test4_Question2 = Integer.parseInt("0");
                            hia1test.objHIA1.setHIA1_Test4_Question2(HIA1_Test4_Question2);
                        }
                        else{
                            HIA1_Test4_Question2 = Integer.parseInt(digbackstring);
                            hia1test.objHIA1.setHIA1_Test4_Question2(HIA1_Test4_Question2);
                        }

                    }
                    catch (NumberFormatException e){
                        //exception
                    }

                }
            });

        }

        return rootView;
    }


}
