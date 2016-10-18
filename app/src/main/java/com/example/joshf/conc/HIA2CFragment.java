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


public class HIA2CFragment extends Fragment {
    private EditText other;
    private static final String TAG = "Video Check";
    String HIA2_Test3_Question1;
    //database
    public HIA2AActivity hia2test;

    public static HIA2CFragment newInstance() {
        HIA2CFragment fragment = new HIA2CFragment();
        return fragment;
    }

    public HIA2CFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia2_c, container, false);
        other = (EditText) rootView.findViewById(R.id.editText);

        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();

            other.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    HIA2_Test3_Question1 = other.getText().toString();
                    Log.v(TAG, "Video Checkbox: " + HIA2_Test3_Question1);
                    hia2test.objHIA2.setHIA2_Test3_Question1(HIA2_Test3_Question1);
                }
            });

        }
        return rootView;

    }

    /*@Override
    public void onPause( ){
        super.onPause();
        if(other.getText() != null) {
            String unusual_symp =other.getText().toString();
            Log.v(TAG, "Video Checkbox: " + unusual_symp);
        }
    }*/
}
