package com.example.joshf.conc;


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;


public class HIA3CFragment extends Fragment {

    public static HIA3CFragment newInstance() {
        HIA3CFragment fragment = new HIA3CFragment();
        return fragment;
    }

    public HIA3CFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hia3_c, container, false);
        return rootView;
    }
}
