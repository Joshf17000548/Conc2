package com.example.joshf.conc;

/**
 * Created by joshf on 2016/10/31.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Josh_PC on 2016/09/12.
 */
public class NoTeams extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_teams, container, false);
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public static NoTeams newInstance() {
        return new NoTeams();
    }
}
