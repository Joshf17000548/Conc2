package com.example.joshf.conc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

    TextView test1,test2,test3;
    TextView test1date,test2date,test3date;
    TextView test1result,test2result,test3result;

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

        test1 = (TextView) findViewById(R.id.test1);
        test1date = (TextView) findViewById(R.id.test1date);
        test1result = (TextView) findViewById(R.id.test1result);

        test2 = (TextView) findViewById(R.id.test2);
        test2date = (TextView) findViewById(R.id.test2date);
        test2result = (TextView) findViewById(R.id.test2result);

        test3 = (TextView) findViewById(R.id.test3);
        test3date = (TextView) findViewById(R.id.test3date);
        test3result = (TextView) findViewById(R.id.test3result);

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
            intent.putExtra("player_code", playerInFocus.getCode_Player());
            startActivity(intent);
            }

        });

        hia2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent1 = new Intent(getApplicationContext(),HIA2AActivity.class);
                intent1.putExtra("player_code", playerInFocus.getCode_Player());
                intent1.putExtra("first_run", true);
                startActivity(intent1);
            }

        });

        hia3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent2 = new Intent(getApplicationContext(),HIA3AActivity.class);
                intent2.putExtra("player_code", playerInFocus.getCode_Player());
                startActivity(intent2);
            }
        });

        baseline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent3 = new Intent(getApplicationContext(),BaselineActivity.class);
                intent3.putExtra("player_code", playerInFocus.getCode_Player());
                startActivity(intent3);
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
        new assessment().execute();
       // new baseline().execute();


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

    private class assessment extends AsyncTask<Void, Void, JSONArray> {

        HIA2 objectHIA2=new HIA2();
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/getAssessment.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public assessment(){

        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(TestMenuActivity.this);
            pDialog.setMessage("Attempting register...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.e("JSonInsTeam", "Background");
            try {

                // PREPARING PARAMETERS..
                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_Player", Integer.toString(playerInFocus.getCode_Player()));

                // all args needs to convert to string because the hash map is string, string types.

                //   Log.d("JSON REQUEST", args.toString());
                Log.e("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.e("json", "0bject = " + json);

                if (json != null) {
                    Log.e("I got", "in here?");
                    Log.e("JSON REQUEST", params.toString());
                    Log.e("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                Log.e("BaselineInsert", "exception",e);

                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            Log.e("JSonInsTeam", "Finish");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            int success = 0;
            String message = "";

            if (json != null) {

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);

/*                    if(json.getJSONObject(0)!=null){
                        test1.setText("HIA"+json.getJSONObject(0).getString(""));
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                Log.e("Success!", message);


            } else {
                // Problems SQL
               // alert.showAlertDialog(TestMenuActivity.this, "Download failed..", "Something went wrong, see Failure Log", false);
                Log.e("Failure", message);

            }


        }


    }

    private class baseline extends AsyncTask<Void, Void, JSONArray> {

        HIA2 objectHIA2=new HIA2();
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/getBaseline.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public baseline(){

        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(TestMenuActivity.this);
            pDialog.setMessage("Attempting register...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.e("JSonInsTeam", "Background");
            try {

                // PREPARING PARAMETERS..
                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_Player", Integer.toString(playerInFocus.getCode_Player()));

                // all args needs to convert to string because the hash map is string, string types.

                //   Log.d("JSON REQUEST", args.toString());
                Log.e("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.e("json", "0bject = " + json);

                if (json != null) {
                    Log.e("I got", "in here?");
                    Log.e("JSON REQUEST", params.toString());
                    Log.e("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                Log.e("BaselineInsert", "exception",e);

                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            Log.e("JSonInsTeam", "Finish");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            int success = 0;
            String message = "";

            if (json != null) {

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);

/*                    if(json.getJSONObject(0)!=null){
                        test1.setText("HIA"+json.getJSONObject(0).getString(""));
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                Log.e("Success!", message);
                try {
                    JSONObject object = json.getJSONObject(0);

                    Baseline.Baseline_Number_of_Symptoms = object.getInt("Baseline_Number_of_Symptoms");
                    Baseline.Baseline_Total_Symptoms = object.getInt("Baseline_Total_Symptoms");
                    Baseline.Baseline_Immediate_Memory =object.getInt("Baseline_Immediate_Memory");
                    Baseline.Baseline_Digits_Backwards = object.getInt("Baseline_Digits_Backwards");
                    Baseline.Baseline_Months_in_Reverse = object.getInt("Baseline_Months_in_Reverse");
                    Baseline.Baseline_Delayed_Memory = object.getInt("Baseline_Delayed_Memory");

                    Baseline.Baseline_Tandem_Time = object.getInt("Baseline_Tandem_Time");
                    Baseline.Baseline_Tandem_ML = object.getInt("Baseline_Tandem_ML");
                    Baseline.Baseline_Tandem_AP = object.getInt("Baseline_Tandem_AP");

                    Baseline.Baseline_DL_PTP = object.getInt("Baseline_DL_PTP");
                    Baseline.Baseline_DL_ML = object.getInt("Baseline_DL_ML");
                    Baseline.Baseline_DL_AP = object.getInt("Baseline_DL_AP");

                    Baseline.Baseline_SL_PTP = object.getInt("Baseline_SL_PTP");
                    Baseline.Baseline_SL_ML =object.getInt("Baseline_SL_ML");
                    Baseline.Baseline_SL_AP = object.getInt("Baseline_SL_AP");

                    Baseline.Baseline_TS_PTP = object.getInt("Baseline_TS_PTP");
                    Baseline.Baseline_TS_ML =object.getInt("Baseline_TS_ML");
                    Baseline.Baseline_TS_AP =object.getInt("Baseline_TS_AP");


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                // Problems SQL
                // alert.showAlertDialog(TestMenuActivity.this, "Download failed..", "Something went wrong, see Failure Log", false);
                Log.e("Failure", message);

            }


        }


    }
}
