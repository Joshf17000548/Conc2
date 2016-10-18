package com.example.joshf.conc;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;


public class HIA3BFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Video Check";
    private EditText other;
    private EditText other1;
    private EditText other2;
    private EditText other3;
    int HIA3_Test2_Question1;
    String HIA3_Test2_Question2;
    int HIA3_Test2_Question3;
    String HIA3_Test2_Question4;
    int HIA3_Test2_Question5;
    String HIA3_Test2_Question6;
    int HIA3_Test2_Question7;
    String HIA3_Test2_Question8;

    //database
    public HIA3AActivity hia3test;

    public static HIA3BFragment newInstance() {
        HIA3BFragment fragment = new HIA3BFragment();
        return fragment;
    }

    public HIA3BFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia3_b, container, false);

        String [] game_event =
                {"Tackling",
                        "Being tackled",
                        "Ruck/maul",
                        "Scrum",
                        "Accidental collision",
                        "Unknown",
                        "Other"
                };
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner28);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, game_event);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String [] collision_with =
                {"Opponent",
                        "Co-player",
                        "Ground",
                        "Unknown",
                        "Other"
                };
        Spinner spinner1 = (Spinner) rootView.findViewById(R.id.spinner29);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, collision_with);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);

        String [] contact =
                {"Head with head",
                        "Head with shoulder",
                        "Head with upper limb",
                        "Head with knee or hip",
                        "Head with foot/lower leg",
                        "Head with ground",
                        "Indirect transmission of force to head",
                        "Unknown",
                        "Other"
                };
        Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner30);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, contact);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);


        String [] player_technique =
                {"Correct technique",
                        "Incorrect head position",
                        "Other incorrect technique",
                        "Unknown",
                        "Not applicable",
                        "Other"
                };
        Spinner spinner3 = (Spinner) rootView.findViewById(R.id.spinner31);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, player_technique);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);

        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        other = (EditText) rootView.findViewById(R.id.editText2);
        other1 = (EditText) rootView.findViewById(R.id.editText3);
        other2 = (EditText) rootView.findViewById(R.id.editText4);
        other3 = (EditText) rootView.findViewById(R.id.editText5);

        Activity c = getActivity();
        if(c instanceof HIA3AActivity) {
            hia3test = (HIA3AActivity) getActivity();

            other.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    HIA3_Test2_Question2 = other.getText().toString();
                    Log.v(TAG, "Video Checkbox: " + HIA3_Test2_Question2);
                    hia3test.objHIA3.setHIA3_Test2_Question2(HIA3_Test2_Question2);
                }
            });

            other1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    HIA3_Test2_Question4 = other1.getText().toString();
                    Log.v(TAG, "Video Checkbox: " + HIA3_Test2_Question4);
                    hia3test.objHIA3.setHIA3_Test2_Question4(HIA3_Test2_Question4);
                }
            });

            other2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    HIA3_Test2_Question6 = other2.getText().toString();
                    Log.v(TAG, "Video Checkbox: " + HIA3_Test2_Question6);
                    hia3test.objHIA3.setHIA3_Test2_Question6(HIA3_Test2_Question6);
                }
            });

            other3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    HIA3_Test2_Question8 = other3.getText().toString();
                    Log.v(TAG, "Video Checkbox: " + HIA3_Test2_Question8);
                    hia3test.objHIA3.setHIA3_Test2_Question8(HIA3_Test2_Question8);
                }
            });

        }
        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Activity c = getActivity();
        if(c instanceof HIA3AActivity) {
            hia3test = (HIA3AActivity) getActivity();

            switch (parent.getId()) {
                case R.id.spinner28:
                    Log.v(TAG, "Video Checkbox0: " + position);
                    HIA3_Test2_Question1 = position;
                    hia3test.objHIA3.setHIA3_Test2_Question1(position);
                    return;
                case R.id.spinner29:
                    Log.v(TAG, "Video Checkbox1: " + position);
                    HIA3_Test2_Question3 = position;
                    hia3test.objHIA3.setHIA3_Test2_Question3(position);
                    return;
                case R.id.spinner30:
                    Log.v(TAG, "Video Checkbox2: " + position);
                    HIA3_Test2_Question5 = position;
                    hia3test.objHIA3.setHIA3_Test2_Question5(position);
                    return;
                case R.id.spinner31:
                    Log.v(TAG, "Video Checkbox2: " + position);
                    HIA3_Test2_Question7 = position;
                    hia3test.objHIA3.setHIA3_Test2_Question7(position);
                    return;

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   /* @Override
    public void onPause( ){
        super.onPause();
        if(other.getText() != null) {
            String inc_deet1 =other.getText().toString();
            Log.v(TAG, "Video Checkbox: " + inc_deet1);
        }

        if(other1.getText() != null) {
            String inc_deet2 =other1.getText().toString();
            Log.v(TAG, "Video Checkbox: " + inc_deet2);
        }

        if(other2.getText() != null) {
            String inc_deet3 =other2.getText().toString();
            Log.v(TAG, "Video Checkbox: " + inc_deet3);
        }

        if(other3.getText() != null) {
            String inc_deet4 =other3.getText().toString();
            Log.v(TAG, "Video Checkbox: " + inc_deet4);
        }
    }*/
}
