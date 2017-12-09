package com.example.joshf.conc;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by joshf on 2016/09/30.
 */
public class TestMenuActivity extends AppCompatActivity {
    ArrayAdapter<String> testmenuadapter;
    Player playerInFocus;
    SessionManager session;
    int team_code;

    Button hia1, hia2, hia3, baseline, VR;

    TextView test1,test2,test3;
    TextView test1date,test2date,test3date;
    TextView test1result,test2result,test3result;
    TextView baselineDate, baselineValid;

    String servicePackage = "com.example.org.gvrfapplication.MainActivity";
    String serviceClass = "com.example.org.gvrfapplication";
    ComponentName serviceComponent;

    FileWriter writer;
    private  String fileNameW = "Player_data";
    private  String dirName = "EyeTrackingData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
        setContentView(R.layout.test_menu_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
       // new ComponentName(servicePackage, serviceClass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hia1 = (Button) findViewById(R.id.buttonHIA1);
        hia2 = (Button) findViewById(R.id.buttonHIA2);
        hia3 = (Button) findViewById(R.id.buttonHIA3);
        baseline = (Button) findViewById(R.id.buttonBaseline);
        VR = (Button) findViewById(R.id.buttonVR);

        test1 = (TextView) findViewById(R.id.test1);
        test1date = (TextView) findViewById(R.id.test1date);
        test1result = (TextView) findViewById(R.id.test1result);

        test2 = (TextView) findViewById(R.id.test2);
        test2date = (TextView) findViewById(R.id.test2date);
        test2result = (TextView) findViewById(R.id.test2result);

        test3 = (TextView) findViewById(R.id.test3);
        test3date = (TextView) findViewById(R.id.test3date);
        test3result = (TextView) findViewById(R.id.test3result);

        baselineDate = (TextView) findViewById(R.id.Basleinedate);
        baselineValid = (TextView) findViewById(R.id.Baselinevalid);



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
            intent.putExtra("player", playerInFocus);
            intent.putExtra("team_code", team_code);
            startActivity(intent);
            }

        });

        hia2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent1 = new Intent(getApplicationContext(),HIA2AActivity.class);
                intent1.putExtra("player", playerInFocus);
                intent1.putExtra("team_code", team_code);
                intent1.putExtra("first_run", true);
                intent1.putExtra("Test_Type", "HIA2");
                startActivity(intent1);
            }

        });

        hia3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent2 = new Intent(getApplicationContext(),HIA3AActivity.class);
                intent2.putExtra("team_code", team_code);
                intent2.putExtra("player", playerInFocus);
                startActivity(intent2);
            }
        });

        baseline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                PreferenceConnector.clear_all_values();
                Intent intent3 = new Intent(getApplicationContext(),BaselineActivity.class);
                intent3.putExtra("player", playerInFocus);
                intent3.putExtra("team_code", team_code);
                intent3.putExtra("Test_Type", "Baseline");
                startActivity(intent3);
            }
        });

        VR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //
                String parentString = Environment.getExternalStorageDirectory()+"/EyeTrackingData"+"/"+playerInFocus.getCode_Player();
                String name ="";
                String surname="";
                Boolean n_s = FALSE;

                for (char ch : playerInFocus.getPlayer_Name().toCharArray()){
                    if((ch!=' ')&(n_s)){
                        surname += ch;
                    }
                    else if((ch!=' ')&(!n_s)){
                        name += ch;
                    }
                    else{
                        n_s=!n_s;
                    }
                }

                try {
                    File root = new File(parentString);
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    File dataWrite = new File(root, (fileNameW+".txt"));
                    writer = new FileWriter(dataWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.append("Player_code=" + String.valueOf(playerInFocus.getCode_Player()) + " ");
                    writer.append("Player_name="+ name+ " ");
                    writer.append("Player_surname="+ surname+ " ");
                    writer.append("DOB="+ playerInFocus.getDOB_player()+ " ");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                final ComponentName cn = new ComponentName(serviceClass, servicePackage);
                intent.setComponent(cn);
                intent.putExtra("code", playerInFocus.getCode_Player());
                startActivity(intent);
                finishAndRemoveTask();
                //send player code over
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        session.checkLogin();
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            playerInFocus =(Player)  extras.getSerializable("player");
            team_code = extras.getInt("team_code");
        }
        new assessment().execute();

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

    public Boolean checkDate(String testdate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Boolean valid=false;

        Date date=null;
        String todayString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date today=null;

        if(testdate!=null) {

            try {
                date = format.parse(testdate);
                today = format.parse(todayString);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            long diff = today.getTime() - date.getTime();
            if ((diff / 86400000) <= 365)
                valid = true;
        }


        return valid;


    }

    private class assessment extends AsyncTask<Void, Void, JSONArray> {

        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "https://www.concussionassessment.net/ConcApp/getAssessment.php"; // Needs to be changed when using different php files.
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
            pDialog.setMessage("Retrieving Concussion History");
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
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {

                    JSONObject baselineObject = json.getJSONObject(0);
                    String test = baselineObject.getString("Type");
                    if(test.equals("Baseline")){
                        String date = baselineObject.getString("Date");
                        baselineDate.setText(date);

                        if(checkDate(date)){
                            baselineValid.setText("Valid");
                            new baseline().execute();
                            baselineValid.setTextColor(getResources().getColor(R.color.startTest));
                        }else{
                            baselineValid.setText("Invalid");
                            baselineValid.setTextColor(getResources().getColor(R.color.reset));
                        }

                    }else{
                        int result = baselineObject.getInt("ColumnAlt");
                        test1.setText(test);
                        test1date.setText(baselineObject.getString("Date"));
                        if(test.equalsIgnoreCase("HIA1")){
                            switch(result){
                                case 0:
                                    test1result.setText("Not Removed");
                                    break;
                                case 1:
                                    test1result.setText("Removed");
                                    break;

                            }
                        }
                        if(test.equalsIgnoreCase("HIA2")){
                            switch(result){
                                case 0:
                                    test1result.setText("CNC");
                                    break;
                                case 1:
                                    test1result.setText("CC");
                                    break;

                            }
                        }
                        if(test.equalsIgnoreCase("HIA3")){
                            switch(result){
                                case 0:
                                    test1result.setText("Concussed");
                                    break;
                                case 1:
                                    test1result.setText("Cleared");
                                    break;

                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject baselineObject = json.getJSONObject(0);
                    JSONObject object1 = json.getJSONObject(1);
                    String test = object1.getString("Type");

                    int result = object1.getInt("ColumnAlt");
                    Log.e("testt", object1.getString("Date"));
                    Log.e("testt", test);

                    if (baselineObject.getString("Type").equals("Baseline")) {
                        test1.setText(test);
                        test1date.setText(object1.getString("Date"));
                        if (test.equalsIgnoreCase("HIA1")) {
                            switch (result) {
                                case 0:
                                    test1result.setText("Not Removed");
                                    break;
                                case 1:
                                    test1result.setText("Removed");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA2")) {
                            switch (result) {
                                case 0:
                                    test1result.setText("CNC");
                                    break;
                                case 1:
                                    test1result.setText("CC");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA3")) {
                            switch (result) {
                                case 0:
                                    test1result.setText("Concussed");
                                    break;
                                case 1:
                                    test1result.setText("Cleared");
                                    break;

                            }
                        }
                    }else{
                        test2.setText(test);
                        test2date.setText(object1.getString("Date"));
                        if (test.equalsIgnoreCase("HIA1")) {



                            switch (result) {
                                case 0:
                                    test2result.setText("Not Removed");
                                    break;
                                case 1:
                                    test2result.setText("Removed");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA2")) {
                            switch (result) {
                                case 0:
                                    test2result.setText("CNC");
                                    break;
                                case 1:
                                    test2result.setText("CC");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA3")) {
                            switch (result) {
                                case 0:
                                    test2result.setText("Concussed");
                                    break;
                                case 1:
                                    test2result.setText("Cleared");
                                    break;

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                try {
                    JSONObject baselineObject = json.getJSONObject(0);
                    JSONObject object1 = json.getJSONObject(2);
                    String test = object1.getString("Type");


                    int result = object1.getInt("ColumnAlt");
                    if (baselineObject.getString("Type").equals("Baseline")) {
                        test2.setText(test);
                        test2date.setText(object1.getString("Date"));
                        if (test.equalsIgnoreCase("HIA1")) {
                            switch (result) {
                                case 0:
                                    test2result.setText("Not Removed");
                                    break;
                                case 1:
                                    test2result.setText("Removed");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA2")) {
                            switch (result) {
                                case 0:
                                    test2result.setText("CNC");
                                    break;
                                case 1:
                                    test2result.setText("CC");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA3")) {
                            switch (result) {
                                case 0:
                                    test2result.setText("Concussed");
                                    break;
                                case 1:
                                    test2result.setText("Cleared");
                                    break;

                            }
                        }
                    }else{
                        test3.setText(test);
                        test3date.setText(object1.getString("Date"));
                        if (test.equalsIgnoreCase("HIA1")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("Not Removed");
                                    break;
                                case 1:
                                    test3result.setText("Removed");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA2")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("CNC");
                                    break;
                                case 1:
                                    test3result.setText("CC");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA3")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("Concussed");
                                    break;
                                case 1:
                                    test3result.setText("Cleared");
                                    break;

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject baselineObject = json.getJSONObject(0);
                    JSONObject object1 = json.getJSONObject(3);
                    String test = object1.getString("Type");
                    test3.setText(test);
                    test3date.setText(object1.getString("Date"));
                    int result = object1.getInt("ColumnAlt");

                    if (baselineObject.getString("Type").equals("Baseline")) {

                        if (test.equalsIgnoreCase("HIA1")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("Not Removed");
                                    break;
                                case 1:
                                    test3result.setText("Removed");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA2")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("CNC");
                                    break;
                                case 1:
                                    test3result.setText("CC");
                                    break;

                            }
                        }
                        if (test.equalsIgnoreCase("HIA3")) {
                            switch (result) {
                                case 0:
                                    test3result.setText("Concussed");
                                    break;
                                case 1:
                                    test3result.setText("Cleared");
                                    break;
                            }
                        }
                    }
                    }catch(JSONException e){
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

        private static final String URL = "https://www.concussionassessment.net/ConcApp/getBaseline.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public baseline(){

        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonGetBase", "Start");
            pDialog = new ProgressDialog(TestMenuActivity.this);
            pDialog.setMessage("Retrieving Baseline");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.e("JSonInsTeamBase", "Background");
            try {

                // PREPARING PARAMETERS..
                Log.e("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                Log.e("Baseline get", String.valueOf(playerInFocus.getCode_Player()));

                args.put("Code_Player", Integer.toString(playerInFocus.getCode_Player()));
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));

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
            Log.e("JSonGetBaseline", "Finish");
          ///  Log.e("GetBaselineR", json.getJSONObject(0).toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            int success = 0;
            String message = "";

            if (json != null) {

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);

                    Log.e("Baseline_Total_Symptoms",String.valueOf(Baseline.Baseline_Total_Symptoms) );
                    JSONObject object = json.getJSONObject(0);
                    Baseline.Baseline_Total_Symptoms = object.getInt("Baseline_Total_Symptoms");
                    Log.e("Baseline_Total_Symptoms",String.valueOf(Baseline.Baseline_Total_Symptoms) );


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                JSONObject object = json.getJSONObject(0);

                Baseline.Baseline_Number_of_Symptoms = object.getInt("Baseline_Number_of_Symptoms");
                Baseline.Baseline_Total_Symptoms = object.getInt("Baseline_Total_Symptoms");
                Baseline.Baseline_Immediate_Memory = object.getInt("Baseline_Immediate_Memory");
                Baseline.Baseline_Digits_Backwards = object.getInt("Baseline_Digits_Backwards");
                Baseline.Baseline_Months_in_Reverse = object.getInt("Baseline_Months_in_Reverse");
                Baseline.Baseline_Delayed_Memory = object.getInt("Baseline_Delayed_Memory");

                Baseline.Baseline_Tandem_Time = object.getInt("Baseline_Tandem_Time");
                Baseline.Baseline_Tandem_ML = object.getDouble("Baseline_Tandem_ML");
                Baseline.Baseline_Tandem_AP = object.getDouble("Baseline_Tandem_AP");

                Baseline.Baseline_DL_PTP = object.getDouble("Baseline_DL_PTP");
                Baseline.Baseline_DL_ML = object.getDouble("Baseline_DL_ML");
                Baseline.Baseline_DL_AP = object.getDouble("Baseline_DL_AP");

                Baseline.Baseline_SL_PTP = object.getDouble("Baseline_SL_PTP");
                Baseline.Baseline_SL_ML =object.getDouble("Baseline_SL_ML");
                Baseline.Baseline_SL_AP = object.getDouble("Baseline_SL_AP");

                Baseline.Baseline_TS_PTP = object.getDouble("Baseline_TS_PTP");
                Baseline.Baseline_TS_ML =object.getDouble("Baseline_TS_ML");
                Baseline.Baseline_TS_AP =object.getDouble("Baseline_TS_AP");

                Log.e("Baseline_TS_AP",String.valueOf(Baseline.Baseline_TS_AP) );


            }catch (JSONException e) {
            e.printStackTrace();
        }

            if (success == 1) {
                Log.e("SuccessBase!", message);


/*                    Baseline.Baseline_Tandem_Time = object.getInt("Baseline_Tandem_Time");
                    Baseline.Baseline_Tandem_ML = object.getDouble("Baseline_Tandem_ML");
                    Baseline.Baseline_Tandem_AP = object.getDouble("Baseline_Tandem_AP");

                    Baseline.Baseline_DL_PTP = object.getDouble("Baseline_DL_PTP");
                    Baseline.Baseline_DL_ML = object.getDouble("Baseline_DL_ML");
                    Baseline.Baseline_DL_AP = object.getDouble("Baseline_DL_AP");

                    Baseline.Baseline_SL_PTP = object.getDouble("Baseline_SL_PTP");
                    Baseline.Baseline_SL_ML =object.getDouble("Baseline_SL_ML");
                    Baseline.Baseline_SL_AP = object.getDouble("Baseline_SL_AP");

                    Baseline.Baseline_TS_PTP = object.getDouble("Baseline_TS_PTP");
                    Baseline.Baseline_TS_ML =object.getDouble("Baseline_TS_ML");
                    Baseline.Baseline_TS_AP =object.getDouble("Baseline_TS_AP");

                    Log.e("Baseline_Total_Symptoms",String.valueOf(Baseline.Baseline_Total_Symptoms) );


                }catch (JSONException e) {
                    e.printStackTrace();
                }*/

            } else {
                // Problems SQL
                // alert.showAlertDialog(TestMenuActivity.this, "Download failed..", "Something went wrong, see Failure Log", false);
                Log.e("Failure", message);

            }


        }


    }
}
