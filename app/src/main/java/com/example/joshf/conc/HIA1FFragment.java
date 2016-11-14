package com.example.joshf.conc;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;


public class HIA1FFragment extends Fragment {

    private static final String TAG = "Video Check";
    private EditText delmem;
    int HIA1_Test6_Question2 = 0;
    int HIA1_Test6_Question3 = 0;
    int HIA1_Test6_Question4 = 0;
    int cs = 0;
    int HIA1_Test6_Question1;

    TextView option1;
    TextView option2;
    TextView option3;

    //database
    public HIA1AActivity hia1test;

    public static HIA1FFragment newInstance() {
        HIA1FFragment fragment = new HIA1FFragment();
        return fragment;
    }

    public HIA1FFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity a = getActivity();
            if (a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia1_f, container, false);
        delmem = (EditText) rootView.findViewById(R.id.editText12);

        option1 =(TextView) rootView.findViewById(R.id.option1);
        option2 =(TextView) rootView.findViewById(R.id.option2);
        option3 =(TextView) rootView.findViewById(R.id.option3);

        Activity a = getActivity();

        if (a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            try {
                delmem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String delmemstring = delmem.getText().toString();
                        Log.v(TAG, "Video Checkbox: " + delmemstring);
                        if ("".equals(delmemstring)) {
                            HIA1_Test6_Question1 = Integer.parseInt("0");
                            HIA1.HIA1_Test6_Question1 = HIA1_Test6_Question1;
                        } else {
                            HIA1_Test6_Question1 = Integer.parseInt(delmemstring);
                            HIA1.HIA1_Test6_Question1 = HIA1_Test6_Question1;
                        }

                    }
                });
            } catch (NumberFormatException e) {
                //exception
            }

        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        option1.setText(HIA1.HIA1_Option_1);
        option2.setText(HIA1.HIA1_Option_2);
        option3.setText(HIA1.HIA1_Option_3);
    }




    @Override
    public void onPause() {
        super.onPause();
        Activity a = getActivity();

        if (a instanceof HIA1AActivity) {
            hia1test = (HIA1AActivity) getActivity();

            try {
                String delmemstring = delmem.getText().toString();
                Log.v(TAG, "Video Checkbox: " + delmemstring);
                if ("".equals(delmemstring)) {
                    HIA1_Test6_Question1 = Integer.parseInt("0");
                    HIA1.HIA1_Test6_Question1 = HIA1_Test6_Question1;
                } else {
                    HIA1_Test6_Question1 = Integer.parseInt(delmemstring);
                    HIA1.HIA1_Test6_Question1 = HIA1_Test6_Question1;
                }
            } catch (NumberFormatException e) {
                //exception
            }


        }

    }
}
