package com.example.joshf.conc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class BaselineActivity extends AppCompatActivity
        implements HIA2DFragment.OnOrienSelectedListener, HIA2GFragment.OnConcSelectedListener
{
    testResults attemp1 = new testResults();
    HIA2EFragment articleFrag;
    private static final String TAG = "Tag Check";
    SectionsPagerAdapter mSectionsPagerAdapter;
    HIA2 objHIA2=new HIA2();
    static Spinner spinner;
    Bundle save;
    SessionManager session;
    Player player;
    int team_code;
    public static String HIA1_result_string = "/0";
    static TextView title;
    static TextView result, resultText;

    public int tabbed_pos;
    AlertDialogManager alert = new AlertDialogManager();

    static int Code_HIA1;


    ViewPager mViewPager;

    //This function calls AsyncTask [insertHIA1], which submit the HIA1 data to insertHIA1.php file.
    public void submitBaseline(View view) {

        int totalSymp = 0;

        if (HIA2.HIA2_Test2_Question1 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question2 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question3 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question4 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question5 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question6 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question7 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question8 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question9 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question10 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question11 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question12 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question13 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question14 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question15 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question16 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question17 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question18 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question19 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question20 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question21 != 0) {
            totalSymp = totalSymp + 1;
        }
        if (HIA2.HIA2_Test2_Question22 != 0) {
            totalSymp = totalSymp + 1;
        }

        Baseline.Baseline_Number_of_Symptoms=totalSymp;

        Baseline.Baseline_Total_Symptoms=HIA2.HIA2_Test2_Question1 + HIA2.HIA2_Test2_Question2 + HIA2.HIA2_Test2_Question3 + HIA2.HIA2_Test2_Question4
                + HIA2.HIA2_Test2_Question5 + HIA2.HIA2_Test2_Question6 + HIA2.HIA2_Test2_Question7 + HIA2.HIA2_Test2_Question8
                + HIA2.HIA2_Test2_Question9 + HIA2.HIA2_Test2_Question10 + HIA2.HIA2_Test2_Question11 + HIA2.HIA2_Test2_Question12
                + HIA2.HIA2_Test2_Question13 + HIA2.HIA2_Test2_Question14 + HIA2.HIA2_Test2_Question15 + HIA2.HIA2_Test2_Question16
                + HIA2.HIA2_Test2_Question17 + HIA2.HIA2_Test2_Question18 + HIA2.HIA2_Test2_Question19 + HIA2.HIA2_Test2_Question20
                + HIA2.HIA2_Test2_Question21 + HIA2.HIA2_Test2_Question22;;

        Log.e("Baseline", String.valueOf(HIA2.HIA2_Test4_Question7));
        Baseline.Baseline_Immediate_Memory=HIA2.HIA2_Test4_Question7;
        Baseline.Baseline_Digits_Backwards=HIA2.HIA2_Test5_Question1;
        Baseline.Baseline_Months_in_Reverse=HIA2.HIA2_Test5_Question2;
        Baseline.Baseline_Delayed_Memory=HIA2.HIA2_Test6_Question1;


        switch (PreferenceConnector.test_status) {
            case 2:
                Baseline.Baseline_Tandem_Time=PreferenceConnector.tandem_t1;
                Baseline.Baseline_Tandem_ML=PreferenceConnector.tandem_t1_MLRMS;
                Baseline.Baseline_Tandem_AP=PreferenceConnector.tandem_t1_APRMS;
                break;
            case 3:
                Baseline.Baseline_Tandem_Time=PreferenceConnector.tandem_t2;
                Baseline.Baseline_Tandem_ML=PreferenceConnector.tandem_t2_MLRMS;
                Baseline.Baseline_Tandem_AP=PreferenceConnector.tandem_t2_APRMS;
                break;
            case 4:
                Baseline.Baseline_Tandem_Time=PreferenceConnector.tandem_t3;
                Baseline.Baseline_Tandem_ML=PreferenceConnector.tandem_t3_MLRMS;
                Baseline.Baseline_Tandem_AP=PreferenceConnector.tandem_t3_APRMS;
                break;
            case 5:
                Baseline.Baseline_Tandem_Time=PreferenceConnector.tandem_t4;
                Baseline.Baseline_Tandem_ML=PreferenceConnector.tandem_t4_MLRMS;
                Baseline.Baseline_Tandem_AP=PreferenceConnector.tandem_t4_APRMS;
                break;
            default:
                Baseline.Baseline_Tandem_Time=0;
                Baseline.Baseline_Tandem_ML=0;
                Baseline.Baseline_Tandem_AP=0;
        }

        Baseline.Baseline_DL_PTP=PreferenceConnector.balance_dl_PTP;
        Baseline.Baseline_DL_ML=PreferenceConnector.balance_dl_APRMS;
        Baseline.Baseline_DL_AP=PreferenceConnector.balance_dl_MLRMS;

        Baseline.Baseline_SL_PTP=PreferenceConnector.balance_sl_PTP;
        Baseline.Baseline_SL_ML=PreferenceConnector.balance_sl_APRMS;
        Baseline.Baseline_SL_AP=PreferenceConnector.balance_sl_MLRMS;

        Baseline.Baseline_TS_PTP=PreferenceConnector.balance_ts_PTP;
        Baseline.Baseline_TS_ML=PreferenceConnector.balance_ts_APRMS;
        Baseline.Baseline_TS_AP=PreferenceConnector.balance_ts_MLRMS;

        Baseline.Baseline_Upper=PreferenceConnector.upper_score;

        new BaselineInsert(objHIA2).execute(); //Call async Task

    }
    //---------------------------------------------
    //------------JSON ----------------------------

    private class BaselineInsert extends AsyncTask<Void, Void, JSONArray> {

        HIA2 objectHIA2=new HIA2();
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "https://www.concussionassessment.net/ConcApp/insertBaseline.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public BaselineInsert(HIA2 objectHIA2Param){
            Log.d("JSONCONSTRUCTOR", "Start");
            Toast.makeText(getApplicationContext(), "Starting JSON", Toast.LENGTH_SHORT).show();
            this.objectHIA2=objectHIA2Param;
        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(BaselineActivity.this);
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

                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));

                args.put("Code_Player", Integer.toString(player.getCode_Player()));
                args.put("Baseline_Date", date);

                args.put("Baseline_Number_of_Symptoms", Integer.toString(Baseline.Baseline_Number_of_Symptoms));
                args.put("Baseline_Total_Symptoms", Integer.toString(Baseline.Baseline_Total_Symptoms));
                args.put("Baseline_Immediate_Memory", Integer.toString(Baseline.Baseline_Immediate_Memory));
                args.put("Baseline_Digits_Backwards", Integer.toString(Baseline.Baseline_Digits_Backwards));
                args.put("Baseline_Months_in_Reverse", Integer.toString(Baseline.Baseline_Months_in_Reverse));
                args.put("Baseline_Delayed_Memory", Integer.toString(Baseline.Baseline_Delayed_Memory));

                args.put("Baseline_Tandem_Time", Double.toString(Baseline.Baseline_Tandem_Time));
                args.put("Baseline_Tandem_ML", Double.toString(Baseline.Baseline_Tandem_ML));
                args.put("Baseline_Tandem_AP", Double.toString(Baseline.Baseline_Tandem_AP));

                args.put("Baseline_DL_PTP", Double.toString(Baseline.Baseline_DL_PTP));
                args.put("Baseline_DL_ML", Double.toString(Baseline.Baseline_DL_ML));
                args.put("Baseline_DL_AP", Double.toString(Baseline.Baseline_DL_AP));

                args.put("Baseline_SL_PTP", Double.toString(Baseline.Baseline_SL_PTP));
                args.put("Baseline_SL_ML", Double.toString(Baseline.Baseline_SL_ML));
                args.put("Baseline_SL_AP", Double.toString(Baseline.Baseline_SL_AP));

                args.put("Baseline_TS_PTP", Double.toString(Baseline.Baseline_TS_PTP));
                args.put("Baseline_TS_ML", Double.toString(Baseline.Baseline_TS_ML));
                args.put("Baseline_TS_AP", Double.toString(Baseline.Baseline_TS_AP));

                args.put("Baseline_Upper", Integer.toString(Baseline.Baseline_Upper));


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
                Log.e("HIA1insert", "exception",e);

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
                Toast.makeText(BaselineActivity.this, json.toString(), Toast.LENGTH_LONG).show();

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
                Baseline.clearBaseline();
                finish();

            } else {
                // Problems SQL
/*                alert.showAlertDialog(BaselineActivity.this, "Insert failed..", "Something went wrong, see Failure Log", false);
                Log.d("Failure", message);
                finish();*/
            }


        }


    }

    public Boolean checkDate(String date){

        return (date.equalsIgnoreCase(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

    }
/*    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("first_run", first_run);
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if (savedInstanceState != null) {
            first_run = savedInstanceState.getBoolean("first_run");

        }else{
            first_run = getIntent().getExtras().getBoolean("first_run");
        }*/
        setContentView(R.layout.viewpager_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
        session.checkLogin();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());//,this.getApplicationContext());

        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // mSectionsPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),this.getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        articleFrag = (HIA2EFragment)
                getSupportFragmentManager().findFragmentById(R.id.hia2Efrag);

        //new
        //mViewPager = new ViewPager(this);
        //mViewPager.setId(R.id.pager);
        //setContentView(mViewPager);
        Bundle extras = getIntent().getExtras();
        player =(Player) extras.getSerializable("player");
        team_code = extras.getInt("team_code");




        //Log.e("HIA2first", String.valueOf(first_run));





        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());//,this.getApplicationContext());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position){
                Fragment fragment = ((SectionsPagerAdapter)mViewPager.getAdapter()).getFragment(position);
                tabbed_pos = position;

                if (position ==5 && fragment != null)
                {
                    Log.v(TAG, "On resume");
                    fragment.onResume();
                    //fragment.updateText();
                }
                if (position ==6 && fragment != null)
                {
                    Log.v("CHECK!", "On resume2");
                    fragment.onResume();
                    //fragment.updateText();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }




    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        Log.e("ONR HIA2","on Save");
        icicle.putString("HIA_String", HIA1_result_string);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(BaselineActivity.this);
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
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialogue_symp)
                            .setTitle(R.string.dialog_title);
                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                case 2:
                    dialogd1.show();
                    break;
                case 3:
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialogue_imed_mem)
                            .setTitle(R.string.dialog_title);
                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog1 = builder.create();
                    dialog1.show();
                    break;
                case 4:
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialogue_digback_months_rev)
                            .setTitle(R.string.dialog_title);
                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog2 = builder.create();
                    dialog2.show();
                    break;
                case 5:
                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage(R.string.dialogue_delayed_mem_2)
                            .setTitle(R.string.dialog_title);
                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog3 = builder.create();
                    dialog3.show();
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
        AlertDialog.Builder backbut = new AlertDialog.Builder(BaselineActivity.this);
        backbut.setMessage(R.string.dialog_back)
                .setTitle(R.string.dialog_back_title);
        // 3. Get the AlertDialog from create()
        // Add the buttons


        backbut.setPositiveButton(R.string.dialog_back_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                HIA2.clearHIA2();
                PreferenceConnector.clear_all_values();
                Intent intent = new Intent(BaselineActivity.this, TestMenuActivity.class);
                intent.putExtra("player", player);
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

    @Override
    public void onOrienSelected(int position) {

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            attemp1.orientation = position;
            //articleFrag.updateOrienScore(position);
            String pos =Integer.toString(position);
            Log.v(TAG, "Purple Monkeys again again " + pos);
            // changeFragmentTextView(pos);

        }
    }

    @Override
    public void onImedMemSelected(int position) {

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            attemp1.imedmem = position;
            //articleFrag.updateOrienScore(position);
            String pos =Integer.toString(position);
            Log.v(TAG, "Purple Monkeys again again " + pos);
            // changeFragmentTextView(pos);

        }
    }

    @Override
    public void onDigitBackSelected(int position) {

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            attemp1.digback = position;
            //articleFrag.updateOrienScore(position);
            String pos =Integer.toString(position);
            Log.v(TAG, "Purple Monkeys again again " + pos);
            // changeFragmentTextView(pos);

        }
    }

    @Override
    public void onMonthBackSelected(int score) {

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            attemp1.monthback = score;
            //articleFrag.updateOrienScore(position);
            String pos =Integer.toString(score);
            Log.v(TAG, "Purple Monkeys again again " + pos);
            // changeFragmentTextView(pos);

        }

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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



        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ArrayAdapter<CharSequence> adapter;

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
            Log.e("ONR HIA2","on instance");
            return fragment;
        }
        @Override
        public void onSaveInstanceState(Bundle icicle) {
            super.onSaveInstanceState(icicle);
            Log.e("ONR HIA2","on Save");
            icicle.putString("HIA_String", HIA1_result_string);

        }


        @Override
        public void onResume() {
            super.onResume();


/*
            if (!HIA2.HIA1_result_string.equals("/0")) {
                result.setText(HIA2.HIA1_result_string);
                title.setText("A HIA1 has been completed on the same day");
                resultText.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
            } else {
                result.setVisibility(View.INVISIBLE);
                resultText.setVisibility(View.INVISIBLE);
                title.setText("A HIA1 has not been completed on the same day");
            }*/

        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hia2_b, container, false);
            //pop up dialog box
            //String s = getResources().getString(R.string.hia2a_section22_header);
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

/*            Activity b = getActivity();
            if(b instanceof HIA2AActivity) {
                hia2test = (HIA2AActivity) getActivity();*/

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

           // }

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
        private Map<Integer, String> mFragmentTags;
        private FragmentManager mFragmentManager;
        private Context mContext;

        public SectionsPagerAdapter(FragmentManager fm){//, Context context) {

            super(fm);
            mFragmentManager = fm;
            mFragmentTags = new HashMap<Integer, String>();
            // mContext= context;

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position)
            {
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);

/*                case 2:
                    return HIA2CFragment.newInstance();*/
                case 1:
                    return HIA2DFragment.newInstance();
                case 2:
                    return HIA2GFragment.newInstance();
                case 3:
                    return HIA2EFragment.newInstance();
                case 4:
                    return Gait.newInstance();
                case 5:
                    return Balance.newInstance();
                case 6:
                    return UpperLimbCoordination.newInstance("Baseline");


            }

            return null;
        }

        @Override
        public int getCount() {

            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "HIA2 (" + (position + 1) + "/7)";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment)
            {
                //record fragment tag here
                Fragment f = (Fragment) obj;
                String tag = f.getTag();
                mFragmentTags.put(position,tag);
            }
            return obj;
        }

        public Fragment getFragment(int position){
            String tag = mFragmentTags.get(position);
            if(tag==null)
                return null;
            return mFragmentManager.findFragmentByTag(tag);
        }


    }
}

