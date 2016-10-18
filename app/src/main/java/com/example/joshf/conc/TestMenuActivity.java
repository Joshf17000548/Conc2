package com.example.joshf.conc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joshf on 2016/09/30.
 */
public class TestMenuActivity extends AppCompatActivity {
    ArrayAdapter<String> testmenuadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] testmenuArray = getResources().getStringArray(R.array.testmenu_array);
        List<String> testMenu = new ArrayList<String>(Arrays.asList(testmenuArray));

        testmenuadapter = new ArrayAdapter<String>(
                this,R.layout.test_select_list_item,
                R.id.list_item_menu_textview,testMenu);

        ListView l_id= (ListView) findViewById(R.id.listview_testmenu);
        l_id.setAdapter(testmenuadapter);

        l_id.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int test_menu_sel, long l){

            switch(test_menu_sel){
                case 0:
                    Intent intent = new Intent(getApplicationContext(),HIA1AActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    Intent intent1 = new Intent(getApplicationContext(),HIA2AActivity.class);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(getApplicationContext(),HIA3AActivity.class);
                    startActivity(intent2);
                    break;

            }

        }
    });


    }

/*    public static class TestMenuFragment extends Fragment {


        int testMenuButton=0;

        public static TestMenuFragment newInstance() {
            return new TestMenuFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_testmenu, container, false);
            String[] testmenuArray = getResources().getStringArray(R.array.testmenu_array);


            testmenuadapter = new ArrayAdapter<String>(
                    getActivity(),R.layout.test_select_list_item,
                    R.id.list_item_menu_textview,testMenu);

            // get reference to the listview id
            ListView l_id= (ListView)rootView.findViewById(R.id.listview_testmenu);
            //Binding the adapter to the list view in the fragment xml file
            l_id.setAdapter(testmenuadapter);

            // The Test Menu called via intent.
            // This displays the menu item selected on the preceding activity
            // in the future can use this to maybe set the heading
            //Intent intent = getActivity().getIntent();
            *//*if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.testmenu_text))
                        .setText(forecastStr);
            }*//*
            final String tag = "MENU_SELECTED";
*//*            l_id.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    String test_menu_sel = String.valueOf(i);
                    Log.v(tag,"On Click\nValue of i: " + test_menu_sel );
                    //Toast.makeText(getActivity(),pushed_menu,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), AdministratorActivity.class)
                            .putExtra(Intent.EXTRA_TEXT, test_menu_sel);
                    //testMenuButton=i;
                    startActivity(intent);

                }
            });*//*

            return rootView;
        }
    }*/

}
