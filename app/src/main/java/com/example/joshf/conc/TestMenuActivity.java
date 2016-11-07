package com.example.joshf.conc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joshf on 2016/09/30.
 */
public class TestMenuActivity extends AppCompatActivity {
    ArrayAdapter<String> testmenuadapter;
    Player playerInFocus;
    SessionManager session;
    int team_code;

    Button hia1, hia2, hia3, baseline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
        setContentView(R.layout.test_menu_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hia1 = (Button) findViewById(R.id.buttonHIA1);
        hia2 = (Button) findViewById(R.id.buttonHIA2);
        hia3 = (Button) findViewById(R.id.buttonHIA3);
        baseline = (Button) findViewById(R.id.buttonBaseline);

/*        String[] testmenuArray = getResources().getStringArray(R.array.testmenu_array);
        List<String> testMenu = new ArrayList<String>(Arrays.asList(testmenuArray));

        testmenuadapter = new ArrayAdapter<String>(
                this,R.layout.test_select_list_item,
                R.id.list_item_menu_textview,testMenu);

        ListView l_id= (ListView) findViewById(R.id.listview_testmenu);
        l_id.setAdapter(testmenuadapter);*/

        hia1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){

            PreferenceConnector.clear_all_values();
            Intent intent = new Intent(getApplicationContext(),HIA1AActivity.class);
            startActivity(intent);
            }

        });

        hia2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent1 = new Intent(getApplicationContext(),HIA2AActivity.class);
                intent1.putExtra("baseline/hia2", false);
                startActivity(intent1);
            }

        });

        hia3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent2 = new Intent(getApplicationContext(),HIA3AActivity.class);
                startActivity(intent2);
            }
        });

        baseline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent2 = new Intent(getApplicationContext(),HIA2AActivity.class);
                intent2.putExtra("baseline/hia2", true);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        session.checkLogin();
        Bundle extras = getIntent().getExtras();
        playerInFocus =(Player) extras.getSerializable("player_info");
        team_code = extras.getInt("team_code");


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hia, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar .setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                return true;
            case R.id.menuId_logout:
                session.logoutUser();
                return true;
            case android.R.id.home:

                Intent intent = new Intent(TestMenuActivity.this, PlayerProfile.class);
                intent.putExtra("player_select","existing");
                intent.putExtra("player_info",playerInFocus);
                intent.putExtra("team", team_code);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
