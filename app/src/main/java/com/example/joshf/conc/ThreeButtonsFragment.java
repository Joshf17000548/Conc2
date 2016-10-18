package com.example.joshf.conc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by joshf on 2016/09/14.
 */
/*
public class ThreeButtonsFragment extends Fragment{

    private Player playerInFocus;


    String TAG = "ThreeButtonsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three_buttons, container, false);
        Button playerInfo =(Button) view.findViewById(R.id.playerInfo);
        Button startTest =(Button) view.findViewById(R.id.startTest);
        playerInFocus = ((ObjectWrapperForBinder) getArguments().getBinder("player_info")).getData();

        playerInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                */
/* Raise view on ACTION_DOWN and lower it on ACTION_UP. *//*

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ACTION_DOWN on view.");
                        view.setTranslationZ(8);
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP on view.");
                        view.setTranslationZ(0);
                        Bundle bund =new Bundle();
                        bund.putString("player_select", "existing");
                        bund.putBinder("player_info", new ObjectWrapperForBinder(playerInFocus));
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        PlayerInfoFragment playerInfoFragment = PlayerInfoFragment.newInstance();
                        playerInfoFragment.setArguments(bund);
                        transaction.replace(R.id.three_buttons, playerInfoFragment, "PlayerInfoFragment");
                        transaction.addToBackStack(null);
                        view.setPressed(false);
                        transaction.commit();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        startTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                */
/* Raise view on ACTION_DOWN and lower it on ACTION_UP. *//*

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ACTION_DOWN on view.");
                        view.setTranslationZ(8);
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP on view.");
                        view.setTranslationZ(0);
                        view.setPressed(false);
                        startActivity(new Intent(getActivity(), TestMenuActivity.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        return view;
    }

    public static ThreeButtonsFragment newInstance() {
        return new ThreeButtonsFragment();
    }
}
*/
