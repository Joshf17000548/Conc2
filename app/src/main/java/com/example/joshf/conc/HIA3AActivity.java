package com.example.joshf.conc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class HIA3AActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public int tabbed_pos;
    AlertDialogManager alert = new AlertDialogManager();

    static TextView HIA1result;
    static TextView HIA2result;
    SessionManager session;

    Player playerInFocus;
    int team_code;

    //database
    public HIA3AActivity hia3test;
    HIA3 objHIA3=new HIA3();
    static Spinner spinner1;

    static int Code_HIA1;

    static int Code_HIA2;
    static int Code_HIA3;
    static int Code_Player;
    int getHIA1T7Q5;
    int getHIA2T7Q1;
    int getHIA3T4Q4;


    //This function calls AsyncTask [insertHIA1], which submit the HIA1 data to insertHIA1.php file.
    public void submitHIA3(View view) {

        if(HIA3.HIA3_Result_Chosen){

            new HIA3AActivity.HIA3insertAsync(objHIA3).execute();
        }
        else{
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setMessage("Diagnoses not Selected")
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();

                }
            });
            android.app.AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        }


    }

    //---------------------------------------------
    //------------JSON ----------------------------

    private class HIA3insertAsync extends AsyncTask<Void, Void, JSONArray> {

        HIA3 objectHIA3=new HIA3();
        // Alert Dialog Manager
        // AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "https://www.concussionassessment.net/ConcApp/insertHIA3.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public HIA3insertAsync(HIA3 objectHIA3Param){
            Log.d("JSONCONSTRUCTOR", "Start");
            Toast.makeText(getApplicationContext(), "Starting JSON", Toast.LENGTH_SHORT).show();
            this.objectHIA3=objectHIA3Param;
        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(HIA3AActivity.this);
            pDialog.setMessage("Attempting register...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.d("JSonInsTeam", "Background");
            try {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                //    Log.d("JSON REQUEST", "Start ...");

                // PREPARING PARAMETERS..
                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                //args.put("HIA2_Test3_Question1", "test");

                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));

                args.put("Code_Player", Integer.toString(playerInFocus.getCode_Player()));
                args.put("HIA3_Date", date);

                args.put("HIA3_Test1_Question1", Integer.toString(HIA3.HIA3_Test1_Question1));

                args.put("HIA3_Test2_Question1", Integer.toString(HIA3.HIA3_Test2_Question1));
                args.put("HIA3_Test2_Question2", HIA3.HIA3_Test2_Question2);
                args.put("HIA3_Test2_Question3", Integer.toString(HIA3.HIA3_Test2_Question3));
                args.put("HIA3_Test2_Question4", HIA3.HIA3_Test2_Question4);
                args.put("HIA3_Test2_Question5", Integer.toString(HIA3.HIA3_Test2_Question5));
                args.put("HIA3_Test2_Question6", HIA3.HIA3_Test2_Question6);
                args.put("HIA3_Test2_Question7", Integer.toString(HIA3.HIA3_Test2_Question7));
                args.put("HIA3_Test2_Question8", HIA3.HIA3_Test2_Question8);

                args.put("HIA3_Test3_Question1", Integer.toString(HIA3.HIA3_Test3_Question1));
                args.put("HIA3_Test3_Question2", HIA3.HIA3_Test3_Question2);
                args.put("HIA3_Test3_Question3", Integer.toString(HIA3.HIA3_Test3_Question3));
                args.put("HIA3_Test3_Question4", HIA3.HIA3_Test3_Question4);
                args.put("HIA3_Test3_Question5", Integer.toString(HIA3.HIA3_Test3_Question5));
                args.put("HIA3_Test3_Question6", Integer.toString(HIA3.HIA3_Test3_Question6));
                args.put("HIA3_Test3_Question7", Integer.toString(HIA3.HIA3_Test3_Question7));
                args.put("HIA3_Test3_Question8", Integer.toString(HIA3.HIA3_Test3_Question8));
                args.put("HIA3_Test3_Question9", Integer.toString(HIA3.HIA3_Test3_Question9));
                args.put("HIA3_Test3_Question10", Integer.toString(HIA3.HIA3_Test3_Question10));
                args.put("HIA3_Test3_Question11", Integer.toString(HIA3.HIA3_Test3_Question11));
                args.put("HIA3_Test3_Question12", Integer.toString(HIA3.HIA3_Test3_Question12));
                args.put("HIA3_Test3_Question13", Integer.toString(HIA3.HIA3_Test3_Question13));
                args.put("HIA3_Test3_Question14", Integer.toString(HIA3.HIA3_Test3_Question14));
                args.put("HIA3_Test3_Question15", Integer.toString(HIA3.HIA3_Test3_Question15));
                args.put("HIA3_Test3_Question16", Integer.toString(HIA3.HIA3_Test3_Question16));
                args.put("HIA3_Test3_Question17", Integer.toString(HIA3.HIA3_Test3_Question17));
                args.put("HIA3_Test3_Question18", Integer.toString(HIA3.HIA3_Test3_Question18));
                args.put("HIA3_Test3_Question19", Integer.toString(HIA3.HIA3_Test3_Question19));
                args.put("HIA3_Test3_Question20", Integer.toString(HIA3.HIA3_Test3_Question20));
                args.put("HIA3_Test3_Question21", Integer.toString(HIA3.HIA3_Test3_Question21));
                args.put("HIA3_Test3_Question22", Integer.toString(HIA3.HIA3_Test3_Question22));
                args.put("HIA3_Test3_Question23", Integer.toString(HIA3.HIA3_Test3_Question23));
                args.put("HIA3_Test3_Question24", Integer.toString(HIA3.HIA3_Test3_Question24));
                args.put("HIA3_Test3_Question25", Integer.toString(HIA3.HIA3_Test3_Question25));
                args.put("HIA3_Test3_Question26", Integer.toString(HIA3.HIA3_Test3_Question26));
                args.put("HIA3_Test3_Question27", Integer.toString(HIA3.HIA3_Test3_Question27));
                args.put("HIA3_Test3_Question28", Integer.toString(HIA3.HIA3_Test3_Question28));
                args.put("HIA3_Test3_Question29", Integer.toString(HIA3.HIA3_Test3_Question29));
                args.put("HIA3_Test3_Question30", Integer.toString(HIA3.HIA3_Test3_Question30));
                args.put("HIA3_Test3_Question31", Integer.toString(HIA3.HIA3_Test3_Question31));
                args.put("HIA3_Test3_Question32", Integer.toString(HIA3.HIA3_Test3_Question32));
                args.put("HIA3_Test3_Question33", Integer.toString(HIA3.HIA3_Test3_Question33));
                args.put("HIA3_Test3_Question34", Integer.toString(HIA3.HIA3_Test3_Question34));
                args.put("HIA3_Test3_Question35", Integer.toString(HIA3.HIA3_Test3_Question35));
                args.put("HIA3_Test3_Question36", Integer.toString(HIA3.HIA3_Test3_Question36));
                args.put("HIA3_Test3_Question37", Integer.toString(HIA3.HIA3_Test3_Question37));
                args.put("HIA3_Test3_Question38", Integer.toString(HIA3.HIA3_Test3_Question38));
                args.put("HIA3_Test3_Question39", Integer.toString(HIA3.HIA3_Test3_Question39));
                args.put("HIA3_Test3_Question40", Integer.toString(HIA3.HIA3_Test3_Question40));
                args.put("HIA3_Test3_Question41", Integer.toString(HIA3.HIA3_Test3_Question41));
                args.put("HIA3_Test3_Question42", Integer.toString(HIA3.HIA3_Test3_Question42));
                args.put("HIA3_Test3_Question43", Integer.toString(HIA3.HIA3_Test3_Question43));
                args.put("HIA3_Test3_Question44", Integer.toString(HIA3.HIA3_Test3_Question44));
                args.put("HIA3_Test3_Question45", Integer.toString(HIA3.HIA3_Test3_Question45));
                args.put("HIA3_Test3_Question46", Integer.toString(HIA3.HIA3_Test3_Question46));
                args.put("HIA3_Test3_Question47", Integer.toString(HIA3.HIA3_Test3_Question47));
                args.put("HIA3_Test3_Question48", Integer.toString(HIA3.HIA3_Test3_Question48));
                args.put("HIA3_Test3_Question49", Integer.toString(HIA3.HIA3_Test3_Question49));
                args.put("HIA3_Test3_Question50", Integer.toString(HIA3.HIA3_Test3_Question50));
                args.put("HIA3_Test3_Question51", Integer.toString(HIA3.HIA3_Test3_Question51));
                args.put("HIA3_Test3_Question52", Integer.toString(HIA3.HIA3_Test3_Question52));
                args.put("HIA3_Test3_Question53", Integer.toString(HIA3.HIA3_Test3_Question53));
                args.put("HIA3_Test3_Question54", Integer.toString(HIA3.HIA3_Test3_Question54));
                args.put("HIA3_Test3_Question55", Integer.toString(HIA3.HIA3_Test3_Question55));
                args.put("HIA3_Test3_Question56", Integer.toString(HIA3.HIA3_Test3_Question56));
                args.put("HIA3_Test3_Question57", Integer.toString(HIA3.HIA3_Test3_Question57));
                args.put("HIA3_Test3_Question58", Integer.toString(HIA3.HIA3_Test3_Question58));
                args.put("HIA3_Test3_Question59", Integer.toString(HIA3.HIA3_Test3_Question59));
                args.put("HIA3_Test3_Question60", Integer.toString(HIA3.HIA3_Test3_Question60));
                args.put("HIA3_Test3_Question61", Integer.toString(HIA3.HIA3_Test3_Question61));
                args.put("HIA3_Test3_Question62", Integer.toString(HIA3.HIA3_Test3_Question62));
                args.put("HIA3_Test3_Question63", Integer.toString(HIA3.HIA3_Test3_Question63));
                args.put("HIA3_Test3_Question64", Integer.toString(HIA3.HIA3_Test3_Question64));
                args.put("HIA3_Test3_Question65", Integer.toString(HIA3.HIA3_Test3_Question65));
                args.put("HIA3_Test3_Question66", Integer.toString(HIA3.HIA3_Test3_Question66));
                args.put("HIA3_Test3_Question67", Integer.toString(HIA3.HIA3_Test3_Question67));
                args.put("HIA3_Test3_Question68", Integer.toString(HIA3.HIA3_Test3_Question68));
                args.put("HIA3_Test3_Question69", Integer.toString(HIA3.HIA3_Test3_Question69));
                args.put("HIA3_Test3_Question70", Integer.toString(HIA3.HIA3_Test3_Question70));
                args.put("HIA3_Test3_Question71", Integer.toString(HIA3.HIA3_Test3_Question71));
                args.put("HIA3_Test3_Question72", Integer.toString(HIA3.HIA3_Test3_Question72));
                args.put("HIA3_Test3_Question73", Integer.toString(HIA3.HIA3_Test3_Question73));
                args.put("HIA3_Test3_Question74", Integer.toString(HIA3.HIA3_Test3_Question74));
                args.put("HIA3_Test3_Question75", Integer.toString(HIA3.HIA3_Test3_Question75));
                args.put("HIA3_Test3_Question76", Integer.toString(HIA3.HIA3_Test3_Question76));
                args.put("HIA3_Test3_Question77", Integer.toString(HIA3.HIA3_Test3_Question77));
                args.put("HIA3_Test3_Question78", Integer.toString(HIA3.HIA3_Test3_Question78));
                args.put("HIA3_Test3_Question79", Integer.toString(HIA3.HIA3_Test3_Question79));
                args.put("HIA3_Test3_Question80", Integer.toString(HIA3.HIA3_Test3_Question80));
                args.put("HIA3_Test3_Question81", Integer.toString(HIA3.HIA3_Test3_Question81));
                args.put("HIA3_Test3_Question82", Integer.toString(HIA3.HIA3_Test3_Question82));
                args.put("HIA3_Test3_Question83", Integer.toString(HIA3.HIA3_Test3_Question83));
                args.put("HIA3_Test3_Question84", Integer.toString(HIA3.HIA3_Test3_Question84));
                args.put("HIA3_Test3_Question85", Integer.toString(HIA3.HIA3_Test3_Question85));
                args.put("HIA3_Test3_Question86", Integer.toString(HIA3.HIA3_Test3_Question86));
                args.put("HIA3_Test3_Question87", Integer.toString(HIA3.HIA3_Test3_Question87));
                args.put("HIA3_Test3_Question88", Integer.toString(HIA3.HIA3_Test3_Question88));
                args.put("HIA3_Test3_Question89", Integer.toString(HIA3.HIA3_Test3_Question89));
                args.put("HIA3_Test3_Question90", Integer.toString(HIA3.HIA3_Test3_Question90));
                args.put("HIA3_Test3_Question91", Integer.toString(HIA3.HIA3_Test3_Question91));
                args.put("HIA3_Test3_Question92", Integer.toString(HIA3.HIA3_Test3_Question92));

                //args.put("HIA2_Test3_Question1", "test");

                args.put("HIA3_Test4_Question1", Integer.toString(HIA3.HIA3_Test4_Question1));
                args.put("HIA3_Test4_Question2", Integer.toString(HIA3.HIA3_Test4_Question2));
                args.put("HIA3_Test4_Question3", Integer.toString(HIA3.HIA3_Test4_Question3));
                args.put("HIA3_Test4_Question4", Integer.toString(HIA3.HIA3_Test4_Question4));
                args.put("HIA3_Test4_Question5", Integer.toString(HIA3.HIA3_Test4_Question5));
                args.put("HIA3_Test4_Question6", HIA3.HIA3_Test4_Question6);

                args.put("HIA3_Test5_Question1", Integer.toString(HIA3.HIA3_Test5_Question1));
                args.put("HIA3_Test5_Question2", Integer.toString(HIA3.HIA3_Test5_Question2));
                args.put("HIA3_Test5_Question3", Integer.toString(HIA3.HIA3_Test5_Question3));
                args.put("HIA3_Test5_Question4", Integer.toString(HIA3.HIA3_Test5_Question4));
                args.put("HIA3_Test5_Question5", Integer.toString(HIA3.HIA3_Test5_Question5));

                args.put("Gait_Completed", Integer.toString(PreferenceConnector.gait_test_completed));
                args.put("Test_Status", Float.toString(PreferenceConnector.test_status));

                args.put("Tandem_t1", Float.toString(PreferenceConnector.tandem_t1));
                args.put("Tandem_t1_MLRMS", Float.toString(PreferenceConnector.tandem_t1_MLRMS));
                args.put("Tandem_t1_APRMS", Float.toString(PreferenceConnector.tandem_t1_APRMS));

                args.put("Tandem_t2", Float.toString(PreferenceConnector.tandem_t2));
                args.put("Tandem_t2_MLRMS", Float.toString(PreferenceConnector.tandem_t2_MLRMS));
                args.put("Tandem_t2_APRMS", Float.toString(PreferenceConnector.tandem_t2_APRMS));

                args.put("Tandem_t3", Float.toString(PreferenceConnector.tandem_t3));
                args.put("Tandem_t3_MLRMS", Float.toString(PreferenceConnector.tandem_t3_MLRMS));
                args.put("Tandem_t3_APRMS", Float.toString(PreferenceConnector.tandem_t3_APRMS));

                args.put("Tandem_t4", Float.toString(PreferenceConnector.tandem_t4));
                args.put("Tandem_t4_MLRMS", Float.toString(PreferenceConnector.tandem_t4_MLRMS));
                args.put("Tandem_t4_APRMS", Float.toString(PreferenceConnector.tandem_t4_APRMS));

                args.put("Balance_test_complete", Integer.toString(PreferenceConnector.balance_test_completed));

                args.put("Balance_dl", Float.toString(PreferenceConnector.balance_dl));
                args.put("Balance_dl_MLRMS", Float.toString(PreferenceConnector.balance_dl_MLRMS));
                args.put("Balance_dl_APRMS", Float.toString(PreferenceConnector.balance_dl_APRMS));
                args.put("Balance_dl_PTP", Float.toString(PreferenceConnector.balance_dl_PTP));

                args.put("Balance_sl", Float.toString(PreferenceConnector.balance_dl));
                args.put("Balance_sl_MLRMS", Float.toString(PreferenceConnector.balance_dl_MLRMS));
                args.put("Balance_sl_APRMS", Float.toString(PreferenceConnector.balance_dl_APRMS));
                args.put("Balance_sl_PTP", Float.toString(PreferenceConnector.balance_dl_PTP));

                args.put("Balance_ts", Float.toString(PreferenceConnector.balance_ts));
                args.put("Balance_ts_MLRMS", Float.toString(PreferenceConnector.balance_ts_MLRMS));
                args.put("Balance_ts_APRMS", Float.toString(PreferenceConnector.balance_ts_APRMS));
                args.put("Balance_ts_PTP", Float.toString(PreferenceConnector.balance_ts_PTP));


                // all args needs to convert to string because the hash map is string, string types.

                //   Log.d("JSON REQUEST", args.toString());
                Log.d("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.d("json", "0bject = " + json);

                if (json != null) {
                    Log.d("I got", "in here?");
                    Log.d("JSON REQUEST", params.toString());
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            Log.d("JSonInsTeam", "Finish");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            Intent intent = new Intent(HIA3AActivity.this, PlayerProfile.class);

            switch (HIA3.HIA3_Test4_Question5){
                case 0:
                    playerInFocus.Player_Status = 0;
                    intent.putExtra("update_required", true);
                    break;
                case 1:
                    intent.putExtra("update_required", true);
                    if(playerInFocus.getPlayer_Status()<3)
                        playerInFocus.Player_Status=3;
                    else
                        playerInFocus.Player_Status = playerInFocus.Player_Status+1;
                    break;

            }


            intent.putExtra("player_info", playerInFocus);
            intent.putExtra("team_code", team_code);
            intent.putExtra("player_select", "existing");
            startActivity(intent);
            int success = 0;
            String message = "";

            if (json != null) {
                Toast.makeText(HIA3AActivity.this, json.toString(), Toast.LENGTH_LONG).show();

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                Log.d("Success!", message);
                PreferenceConnector.clear_all_values();
                HIA3.clearHIA3();

                finish();
            } else {
                // Problems SQL
                alert.showAlertDialog(HIA3AActivity.this, "Insert failed..", "Something went wrong, see Failure Log", false);
                Log.d("Failure", message);
                finish();
            }
        }


    }
    //-------END JSON----------------
    //-------------------------------

    // second Async start

    private class get_HIA123 extends AsyncTask<Void, Void, JSONArray> {


        int CodePlayer;
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        //private static final String URL = "http://104.198.254.110/ConcApp/getHIA1_Test7_Question5.php"; // Needs to be changed when using different php files.
        private static final String URL = "https://www.concussionassessment.net/ConcApp/getHIA123Fields.php";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public get_HIA123(int Code_Player){
            Log.d("JSONCONSTRUCTOR", "Start");
            Toast.makeText(getApplicationContext(), "Starting JSON", Toast.LENGTH_SHORT).show();
            this.CodePlayer=Code_Player;
        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(HIA3AActivity.this);
            pDialog.setMessage("Attempting retrieve...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.d("JSonInsTeam", "Background");
            try {

                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_Player", Integer.toString(this.CodePlayer));
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));


                Log.e("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.e("json", "0bject = " + json);

                if (json != null) {
                    Log.d("I got", "in here?");
                    Log.d("JSON REQUEST", params.toString());
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            Log.d("JSonInsTeam", "Finish");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            int success = 0;
            String message = "";

            if (json != null) {
                Toast.makeText(HIA3AActivity.this, json.toString(), Toast.LENGTH_LONG).show();

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);
                    Boolean HIA1_Done=false;
                    String HIA1_date = json.getJSONObject(0).getString("HIA1_Date");
                    String HIA2_date = json.getJSONObject(0).getString("HIA2_Date");
                    //String HIA3_date = json.getJSONObject(0).getString("HIA3_Date");

                    if(HIA1_date!=null)
                        getHIA1T7Q5 = json.getJSONObject(0).getInt("HIA1_Test7_Question5");
                    if(HIA2_date!=null)
                        getHIA2T7Q1 = json.getJSONObject(0).getInt("HIA2_Result");
/*                    if(HIA3_date!=null)
                        getHIA3T4Q4 = json.getJSONObject(0).getInt("HIA3_Test4_Question4");*/

                    Log.e("Retrieved",Integer.toString(getHIA1T7Q5));
                    Log.e("Retrieved",Integer.toString(getHIA2T7Q1));
                    //Log.e("Retrieved",Integer.toString(getHIA3T4Q4));

                    Log.e("HIA1_date", HIA1_date);
                    Log.e("HIA2_date", HIA2_date);
                  //  Log.e("HIA3_date", HIA3_date);

                    if (success==1){
                        if(checkDate(HIA1_date)){
                            switch(getHIA1T7Q5){
                                case 0:
                                    HIA3.HIA1_result_string = "Yes, player immediately and permanently removed";
                                    HIA1_Done=true;
                                    break;
                                case 1:
                                    HIA3.HIA1_result_string = "Yes, pitch-side HIA abnormal and player removed";
                                    HIA1_Done=true;
                                    break;
                                case 2:
                                    HIA3.HIA1_result_string = "Yes, pitch-side HIA normal and player removed";
                                    HIA1_Done=true;
                                    break;
                                case 3:
                                    HIA3.HIA1_result_string = "Yes, pitch-side HIA normal and player returned to play";
                                    HIA1_Done=true;
                                    break;
                                case 4:
                                    HIA3.HIA1_result_string = "Yes, HIA normal and player removed for another injury";
                                    HIA1_Done=true;
                                    break;
                            }
                        }else
                            HIA3.HIA1_result_string = "No, Not Completed";

                        if(checkDate(HIA2_date)){
                            switch(getHIA2T7Q1){
                                case 0:
                                    if(HIA1_Done)
                                        HIA3.HIA2_result_string = "Yes, HIA2 completed as follow up to HIA1, concussion not confirmed";
                                    else
                                        HIA3.HIA2_result_string = "Yes, HIA2 completed, symptoms developed post-match, same day, concussion not confirmed";
                                    break;
                                case 1:
                                    if(HIA1_Done)
                                        HIA3.HIA2_result_string = "Yes, HIA2 completed as follow up to HIA1, concussion confirmed";
                                    else
                                        HIA3.HIA2_result_string = "Yes, HIA2 completed, symptoms developed post-match, same day, concussion confirmed";
                                    break;
                            }
                        }else
                            HIA3.HIA2_result_string = "No, HIA2 not completed, symptoms developed during the day(s) following the match";


                    }

                    HIA1result = (TextView) findViewById(R.id.HIA3_HIA1_result);
                    HIA2result = (TextView) findViewById(R.id.HIA3_HIA2_result);

                    HIA1result.setText(HIA3.HIA1_result_string);
                    HIA2result.setText(HIA3.HIA2_result_string);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }


    }
    //-------END JSON----------------
    //-------------------------------
    // end second Async

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
            if ((diff / 86400000) <= 2)
                valid = true;
        }


        return valid;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
        session.checkLogin();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Bundle extras = getIntent().getExtras();
        playerInFocus =(Player) extras.getSerializable("player");
        team_code = extras.getInt("team_code");


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position){
                Fragment fragment = ((SectionsPagerAdapter)mViewPager.getAdapter()).getFragment(position);

                tabbed_pos = position;
                Log.d("Position = ",Integer.toString(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        if(HIA3.HIA3_first) {
            new get_HIA123(playerInFocus.getCode_Player()).execute();
            HIA3.HIA3_first=false;
        }

    }

    @Override
    protected void onPause(){
        alert.dismissAlertDialog();
        super.onPause();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hia1, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar .setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_help)
        {
            Log.d("check","in action help");
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(HIA3AActivity.this);
            builder.setMessage(R.string.dialog_default)
                    .setTitle(R.string.dialog_title);
            // 3. Get the AlertDialog from create()
            AlertDialog dialogd1 = builder.create();
            switch (tabbed_pos){
                case 0:
                    // 2. Chain together various setter methods to set the dialog characteristics
                    dialogd1.show();
                    break;
                case 1:
                    dialogd1.show();
                    break;
                case 2:
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialogue_symp_HIA3)
                            .setTitle(R.string.dialog_title);
                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                case 3:
                    dialogd1.show();
                    break;
                case 4:
                    dialogd1.show();
                    break;
                case 5:
                    dialogd1.show();
                    break;
                case 6:
                    dialogd1.show();
                    break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        //super.onBackPressed();  // optional depending on your needs
        //Log.d("Back pressed","Yes");
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder backbut = new AlertDialog.Builder(HIA3AActivity.this);
        backbut.setMessage(R.string.dialog_back)
                .setTitle(R.string.dialog_back_title);
        // 3. Get the AlertDialog from create()
        // Add the buttons


        backbut.setPositiveButton(R.string.dialog_back_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                PreferenceConnector.clear_all_values();
                HIA3.clearHIA3();
                Intent intent = new Intent(HIA3AActivity.this, TestMenuActivity.class);
                intent.putExtra("player", playerInFocus);
                intent.putExtra("team_code", team_code);
                startActivity(intent);
            }
        });
        backbut.setNegativeButton(R.string.dialog_back_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                //dialogback.dismiss();
            }
        });

        final AlertDialog dialogback = backbut.create();
        dialogback.show();


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemSelectedListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ArrayAdapter<CharSequence> adapter2;
        private ArrayAdapter<CharSequence> adapter1;
        private ArrayAdapter<CharSequence> adapter;

        //database
        public HIA3AActivity hia3test;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hia3_a, container, false);


            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner27);
            this.adapter=ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner,android.R.layout.simple_spinner_dropdown_item);
            this.adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner,R.layout.multiline_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            HIA1result = (TextView) rootView.findViewById(R.id.HIA3_HIA1_result);
            HIA2result = (TextView) rootView.findViewById(R.id.HIA3_HIA2_result);



            spinner.setOnItemSelectedListener(this);



            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();

            HIA1result.setText(HIA3.HIA1_result_string);
            HIA2result.setText(HIA3.HIA2_result_string);

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            Activity c = getActivity();
            if(c instanceof HIA3AActivity) {
                hia3test = (HIA3AActivity) getActivity();
                switch(parent.getId()){
                    case R.id.spinner27:
                        HIA3.HIA3_Test1_Question1=position;
                        Log.d("1","check"+ position);
                        return;
                }

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
            mFragmentTags = new HashMap<Integer, String>();
            // mContext= context;
        }
        private Map<Integer, String> mFragmentTags;
        private FragmentManager mFragmentManager;
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position)
            {
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return HIA3BFragment.newInstance();
                case 2:
                    return HIA3FFragment.newInstance();
                case 3:
                    return Gait.newInstance();
                case 4:
                    return Balance.newInstance();
                case 5:
                    return HIA3DFragment.newInstance();
/*                case 6:
                    return HIA3EFragment.newInstance();*/
                /*case 5:
                    return HIA2FFragment.newInstance();
                */

            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /*switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
            */
            return "HIA3 (" + (position + 1) + "/6)";
        }

        public Fragment getFragment(int position){
            String tag = mFragmentTags.get(position);
            if(tag==null)
                return null;
            return mFragmentManager.findFragmentByTag(tag);
        }

    }
}
