package com.example.joshf.conc;


        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AlertDialog;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;

/**
 * Created by user on 2016/07/04.
 */

public class HIA2BFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Video Check";
    boolean HIA2_Test2_Question1= false;
    boolean HIA2_Test2_Question2= false;
    boolean HIA2_Test2_Question3= false;
    boolean HIA2_Test2_Question4= false;
    boolean HIA2_Test2_Question5= false;
    boolean HIA2_Test2_Question6= false;
    boolean HIA2_Test2_Question7= false;
    boolean HIA2_Test2_Question8= false;
    boolean HIA2_Test2_Question9= false;
    boolean HIA2_Test2_Question10= false;
    boolean HIA2_Test2_Question11= false;
    boolean HIA2_Test2_Question12= false;
    boolean HIA2_Test2_Question13= false;
    boolean HIA2_Test2_Question14= false;
    boolean HIA2_Test2_Question15= false;
    boolean HIA2_Test2_Question16= false;
    boolean HIA2_Test2_Question17= false;
    boolean HIA2_Test2_Question18= false;
    boolean HIA2_Test2_Question19= false;
    boolean HIA2_Test2_Question20= false;
    boolean HIA2_Test2_Question21= false;
    boolean HIA2_Test2_Question22= false;
    int HIA2_Test2_Question45=0;
    int prev1=0;
    int prev2=0;
    int prev3=0;
    int prev4=0;
    int prev5=0;
    int prev6=0;
    int prev7=0;
    int prev8=0;
    int prev9=0;
    int prev10=0;
    int prev11=0;
    int prev12=0;
    int prev13=0;
    int prev14=0;
    int prev15=0;
    int prev16=0;
    int prev17=0;
    int prev18=0;
    int prev19=0;
    int prev20=0;
    int prev21=0;
    int prev22=0;

    int HIA2_Test2_Question23 =0;
    int HIA2_Test2_Question24 =0;
    int HIA2_Test2_Question25 =0;
    int HIA2_Test2_Question26 =0;
    int HIA2_Test2_Question27 =0;
    int HIA2_Test2_Question28 =0;
    int HIA2_Test2_Question29 =0;
    int HIA2_Test2_Question30 =0;
    int HIA2_Test2_Question31 =0;
    int HIA2_Test2_Question32 =0;
    int HIA2_Test2_Question33 =0;
    int HIA2_Test2_Question34 =0;
    int HIA2_Test2_Question35 =0;
    int HIA2_Test2_Question36 =0;
    int HIA2_Test2_Question37 =0;
    int HIA2_Test2_Question38 =0;
    int HIA2_Test2_Question39 =0;
    int HIA2_Test2_Question40 =0;
    int HIA2_Test2_Question41 =0;
    int HIA2_Test2_Question42 =0;
    int HIA2_Test2_Question43 =0;
    int HIA2_Test2_Question44 =0;
    public HIA2AActivity attempt2;

    //database
    public HIA2AActivity hia2test;

    public void setFlag(){
        Log.v(TAG, "Flag attempt 1 ");
        if((HIA2_Test2_Question1||HIA2_Test2_Question2||HIA2_Test2_Question3 || HIA2_Test2_Question4
                || HIA2_Test2_Question5 ||HIA2_Test2_Question6 ||HIA2_Test2_Question7 || HIA2_Test2_Question8
                ||HIA2_Test2_Question9|| HIA2_Test2_Question10|| HIA2_Test2_Question11||HIA2_Test2_Question12
                ||HIA2_Test2_Question13||HIA2_Test2_Question14||HIA2_Test2_Question15||HIA2_Test2_Question16
                ||HIA2_Test2_Question17||HIA2_Test2_Question18||HIA2_Test2_Question19||HIA2_Test2_Question20
                ||HIA2_Test2_Question21||HIA2_Test2_Question22)==true)
        {
            Log.v(TAG, "Flag attempt ");
            Activity a = getActivity();
            if(a instanceof HIA2AActivity){
                attempt2 = (HIA2AActivity) getActivity();
                attempt2.attemp1.symFlag = 1;
                Log.v(TAG, "Flag set ");
            }
        }
        else{
            Activity a = getActivity();
            if(a instanceof HIA2AActivity) {
                attempt2 = (HIA2AActivity) getActivity();
                attempt2.attemp1.symFlag = 0;
                Log.v(TAG, "Flag reset ");
            }

        }
    }


    public static HIA2BFragment newInstance() {
        HIA2BFragment fragment = new HIA2BFragment();
        return fragment;
    }

    public HIA2BFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //pop up dialog box
        //String s = getResources().getString(R.string.hia2a_section22_header);
        View rootView = inflater.inflate(R.layout.fragment_hia2_b, container, false);
        // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setMessage(s).setTitle("Read before completing the symptoms checklist");

        //AlertDialog dialog = builder.create();
        //dialog.show();

        //move to string values
        String [] symp_num = {"0","1","2","3","4","5","6"};

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) rootView.findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner7);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) rootView.findViewById(R.id.spinner8);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) rootView.findViewById(R.id.spinner9);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) rootView.findViewById(R.id.spinner10);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner5.setAdapter(adapter5);

        Spinner spinner6 = (Spinner) rootView.findViewById(R.id.spinner11);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner6.setAdapter(adapter6);

        Spinner spinner7 = (Spinner) rootView.findViewById(R.id.spinner12);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter7.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner7.setAdapter(adapter7);

        Spinner spinner8 = (Spinner) rootView.findViewById(R.id.spinner13);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter8.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner8.setAdapter(adapter8);

        Spinner spinner9 = (Spinner) rootView.findViewById(R.id.spinner14);
        ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter9.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner9.setAdapter(adapter9);

        Spinner spinner10 = (Spinner) rootView.findViewById(R.id.spinner15);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter10.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner10.setAdapter(adapter10);

        Spinner spinner11 = (Spinner) rootView.findViewById(R.id.spinner16);
        ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter11.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner11.setAdapter(adapter11);

        Spinner spinner12 = (Spinner) rootView.findViewById(R.id.spinner17);
        ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter12.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner12.setAdapter(adapter12);

        Spinner spinner13 = (Spinner) rootView.findViewById(R.id.spinner18);
        ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter13.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner13.setAdapter(adapter13);

        Spinner spinner14 = (Spinner) rootView.findViewById(R.id.spinner19);
        ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter14.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner14.setAdapter(adapter14);

        Spinner spinner15 = (Spinner) rootView.findViewById(R.id.spinner20);
        ArrayAdapter<String> adapter15 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter15.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner15.setAdapter(adapter15);

        Spinner spinner16 = (Spinner) rootView.findViewById(R.id.spinner21);
        ArrayAdapter<String> adapter16 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter16.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner16.setAdapter(adapter16);

        Spinner spinner17 = (Spinner) rootView.findViewById(R.id.spinner22);
        ArrayAdapter<String> adapter17 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter17.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner17.setAdapter(adapter17);

        Spinner spinner18 = (Spinner) rootView.findViewById(R.id.spinner23);
        ArrayAdapter<String> adapter18 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter18.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner18.setAdapter(adapter18);

        Spinner spinner19 = (Spinner) rootView.findViewById(R.id.spinner24);
        ArrayAdapter<String> adapter19 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter19.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner19.setAdapter(adapter19);

        Spinner spinner20 = (Spinner) rootView.findViewById(R.id.spinner25);
        ArrayAdapter<String> adapter20 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter20.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner20.setAdapter(adapter20);

        Spinner spinner21 = (Spinner) rootView.findViewById(R.id.spinner26);
        ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, symp_num);
        adapter21.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner21.setAdapter(adapter21);

        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);
        spinner7.setOnItemSelectedListener(this);
        spinner8.setOnItemSelectedListener(this);
        spinner9.setOnItemSelectedListener(this);
        spinner10.setOnItemSelectedListener(this);
        spinner11.setOnItemSelectedListener(this);
        spinner12.setOnItemSelectedListener(this);
        spinner13.setOnItemSelectedListener(this);
        spinner14.setOnItemSelectedListener(this);
        spinner15.setOnItemSelectedListener(this);
        spinner16.setOnItemSelectedListener(this);
        spinner17.setOnItemSelectedListener(this);
        spinner18.setOnItemSelectedListener(this);
        spinner19.setOnItemSelectedListener(this);
        spinner20.setOnItemSelectedListener(this);
        spinner21.setOnItemSelectedListener(this);

        return rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Activity b = getActivity();
        if(b instanceof HIA2AActivity) {
            hia2test = (HIA2AActivity) getActivity();

            switch (parent.getId()) {
                case R.id.spinner5:
                    HIA2_Test2_Question23 = position;
                    if (position > 0) {
                        HIA2_Test2_Question1 = true;
                        HIA2.HIA2_Test2_Question1=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev1;
                        prev1 = position;
                    } else {
                        HIA2_Test2_Question1 = false;
                        HIA2.HIA2_Test2_Question1=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev1;

                    }
                    Log.v(TAG, "Video Checkbox0: " + HIA2_Test2_Question45 + "state: " + HIA2_Test2_Question1 + "symp value: " + HIA2_Test2_Question23);
                    setFlag();
                    return;
                case R.id.spinner6:
                    HIA2_Test2_Question24 = position;
                    if (position > 0) {
                        HIA2_Test2_Question2 = true;
                        HIA2.HIA2_Test2_Question2=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev2;
                        prev2 = position;
                    } else {
                        HIA2_Test2_Question2 = false;
                        HIA2.HIA2_Test2_Question2=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev2;
                    }
                    Log.v(TAG, "Video Checkbox0: " + HIA2_Test2_Question45 + "state: " + HIA2_Test2_Question2);
                    setFlag();
                    return;
                case R.id.spinner7:
                    HIA2_Test2_Question25 = position;
                    if (position > 0) {
                        HIA2_Test2_Question3 = true;
                        HIA2.HIA2_Test2_Question3=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev3;
                        prev3 = position;
                    } else {
                        HIA2_Test2_Question3 = false;
                        HIA2.HIA2_Test2_Question3=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev3;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner8:
                    HIA2_Test2_Question26 = position;
                    if (position > 0) {
                        HIA2_Test2_Question4 = true;
                        HIA2.HIA2_Test2_Question4=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev4;
                        prev4 = position;
                    } else {
                        HIA2_Test2_Question4 = false;
                        HIA2.HIA2_Test2_Question4=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev4;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner9:
                    HIA2_Test2_Question27 = position;
                    if (position > 0) {
                        HIA2_Test2_Question5 = true;
                        HIA2.HIA2_Test2_Question5=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev5;
                        prev5 = position;
                    } else {
                        HIA2_Test2_Question5 = false;
                        HIA2.HIA2_Test2_Question5=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev5;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner10:
                    HIA2_Test2_Question28 = position;
                    if (position > 0) {
                        HIA2_Test2_Question6 = true;
                        HIA2.HIA2_Test2_Question6=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev6;
                        prev6 = position;
                    } else {
                        HIA2_Test2_Question6 = false;
                        HIA2.HIA2_Test2_Question6=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev6;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner11:
                    HIA2_Test2_Question29 = position;
                    if (position > 0) {
                        HIA2_Test2_Question7 = true;
                        HIA2.HIA2_Test2_Question7=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev7;
                        prev7 = position;
                    } else {
                        HIA2_Test2_Question7 = false;
                        HIA2.HIA2_Test2_Question7=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev7;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner12:
                    HIA2_Test2_Question30 = position;
                    if (position > 0) {
                        HIA2_Test2_Question8 = true;
                        HIA2.HIA2_Test2_Question8=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev8;
                        prev8 = position;
                    } else {
                        HIA2_Test2_Question8 = false;
                        HIA2.HIA2_Test2_Question8=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev8;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner13:
                    HIA2_Test2_Question31 = position;
                    if (position > 0) {
                        HIA2_Test2_Question9 = true;
                        HIA2.HIA2_Test2_Question9=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev9;
                        prev9 = position;
                    } else {
                        HIA2_Test2_Question9 = false;
                        HIA2.HIA2_Test2_Question9=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev9;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner14:
                    HIA2_Test2_Question32 = position;
                    if (position > 0) {
                        HIA2_Test2_Question10 = true;
                        HIA2.HIA2_Test2_Question10=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev10;
                        prev10 = position;
                    } else {
                        HIA2_Test2_Question10 = false;
                        HIA2.HIA2_Test2_Question10=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev10;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner15:
                    HIA2_Test2_Question33 = position;
                    if (position > 0) {
                        HIA2_Test2_Question11 = true;
                        HIA2.HIA2_Test2_Question11=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev11;
                        prev11 = position;
                    } else {
                        HIA2_Test2_Question11 = false;
                        HIA2.HIA2_Test2_Question11=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev11;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner16:
                    HIA2_Test2_Question34 = position;
                    if (position > 0) {
                        HIA2_Test2_Question12 = true;
                        HIA2.HIA2_Test2_Question12=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev12;
                        prev12 = position;
                    } else {
                        HIA2_Test2_Question12 = false;
                        HIA2.HIA2_Test2_Question12=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev12;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner17:
                    HIA2_Test2_Question35 = position;
                    if (position > 0) {
                        HIA2_Test2_Question13 = true;
                        HIA2.HIA2_Test2_Question13=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev13;
                        prev13 = position;
                    } else {
                        HIA2_Test2_Question13 = false;
                        HIA2.HIA2_Test2_Question13=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev13;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner18:
                    HIA2_Test2_Question36 = position;
                    if (position > 0) {
                        HIA2_Test2_Question14 = true;
                        HIA2.HIA2_Test2_Question14=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev14;
                        prev14 = position;
                    } else {
                        HIA2_Test2_Question14 = false;
                        HIA2.HIA2_Test2_Question14=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev14;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner19:
                    HIA2_Test2_Question37 = position;
                    if (position > 0) {
                        HIA2_Test2_Question15 = true;
                        HIA2.HIA2_Test2_Question15=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev15;
                        prev15 = position;
                    } else {
                        HIA2_Test2_Question15 = false;
                        HIA2.HIA2_Test2_Question15=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev15;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner20:
                    HIA2_Test2_Question38 = position;
                    if (position > 0) {
                        HIA2_Test2_Question16 = true;
                        HIA2.HIA2_Test2_Question16=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev16;
                        prev16 = position;
                    } else {
                        HIA2_Test2_Question16 = false;
                        HIA2.HIA2_Test2_Question16=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev16;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner21:
                    HIA2_Test2_Question39 = position;
                    if (position > 0) {
                        HIA2_Test2_Question17 = true;
                        HIA2.HIA2_Test2_Question17=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev17;
                        prev17 = position;
                    } else {
                        HIA2_Test2_Question17 = false;
                        HIA2.HIA2_Test2_Question17=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev17;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner22:
                    HIA2_Test2_Question40 = position;
                    if (position > 0) {
                        HIA2_Test2_Question18 = true;
                        HIA2.HIA2_Test2_Question18=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev18;
                        prev18 = position;
                    } else {
                        HIA2_Test2_Question18 = false;
                        HIA2.HIA2_Test2_Question18=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev18;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner23:
                    HIA2_Test2_Question41 = position;
                    if (position > 0) {
                        HIA2_Test2_Question19 = true;
                        HIA2.HIA2_Test2_Question19=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev19;
                        prev19 = position;
                    } else {
                        HIA2_Test2_Question19 = false;
                        HIA2.HIA2_Test2_Question19=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev19;
                    }
                    Log.v(TAG, "Video Checkbox0: " + position);
                    setFlag();
                    return;
                case R.id.spinner24:
                    HIA2_Test2_Question42 = position;
                    if (position > 0) {
                        HIA2_Test2_Question20 = true;
                        HIA2.HIA2_Test2_Question20=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev20;
                        prev20 = position;
                    } else {
                        HIA2_Test2_Question20 = false;
                        HIA2.HIA2_Test2_Question20=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev20;
                    }
                    Log.v(TAG, "Video Checkbox1: " + position);
                    setFlag();
                    return;
                case R.id.spinner25:
                    HIA2_Test2_Question43 = position;
                    if (position > 0) {
                        HIA2_Test2_Question21 = true;
                        HIA2.HIA2_Test2_Question21=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev21;
                        prev21 = position;
                    } else {
                        HIA2_Test2_Question21 = false;
                        HIA2.HIA2_Test2_Question21=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev21;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;
                case R.id.spinner26:
                    HIA2_Test2_Question44 = position;
                    if (position > 0) {
                        HIA2_Test2_Question22 = true;
                        HIA2.HIA2_Test2_Question22=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 + position - prev22;
                        prev22 = position;
                    } else {
                        HIA2_Test2_Question22 = false;
                        HIA2.HIA2_Test2_Question22=position;
                        HIA2_Test2_Question45 = HIA2_Test2_Question45 - prev22;
                    }
                    Log.v(TAG, "Video Checkbox2: " + position);
                    setFlag();
                    return;

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
