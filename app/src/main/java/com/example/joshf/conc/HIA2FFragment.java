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
        import android.widget.TextView;


public class HIA2FFragment extends Fragment {

    public HIA2AActivity concresult;
    CheckBox chk1;
    CheckBox chk2;
    public static HIA2FFragment newInstance() {
        HIA2FFragment fragment = new HIA2FFragment();
        return fragment;
    }

    public HIA2FFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia2_f, container, false);
        chk1 = (CheckBox)rootView.findViewById(R.id.checkBox_CC);
        chk2 = (CheckBox)rootView.findViewById(R.id.checkBox_CNC);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        Activity a = getActivity();

        if(a instanceof HIA2AActivity)
        {
            concresult = (HIA2AActivity) getActivity();
            if(concresult.attemp1.imedmem<=12){
                chk1.setChecked(true);
                chk2.setChecked(false);

            }
            else if (concresult.attemp1.digback<=2)
            {
                chk1.setChecked(true);
                chk2.setChecked(false);
            }

            else if (concresult.attemp1.delayedRecall<=3)
            {
                Log.v("del:", ""+ concresult.attemp1.delayedRecall);
                chk1.setChecked(true);
                chk2.setChecked(false);
            }

            else if (concresult.attemp1.sacTotal<=26)
            {
                chk1.setChecked(true);
                chk2.setChecked(false);
            }
            else if (concresult.attemp1.symFlag==1){
                chk1.setChecked(true);
                chk2.setChecked(false);
            }
            else
            {
                chk2.setChecked(true);
                chk1.setChecked(false);
            }
        }
    }
}