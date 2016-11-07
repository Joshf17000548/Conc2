package com.example.joshf.conc;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
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

        import java.util.HashMap;
        import java.util.Map;


public class HIA3AActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public int tabbed_pos;
    AlertDialogManager alert = new AlertDialogManager();



    //database
    public HIA3AActivity hia3test;
    HIA3 objHIA3=new HIA3();
    static Spinner spinner1;

    static int Code_HIA1;

    //This function calls AsyncTask [insertHIA1], which submit the HIA1 data to insertHIA1.php file.
    public void submitHIA3(View view) {
        //HIA1 objHIA1=new HIA1();
        // int param; // Used to convert YES ->1 and NO ->0. Should change the value of radio groups to integers.
        //Find radio group
        //radioGroup = (RadioGroup) findViewById(R.id.Radio_checkBoxAA);
        // get selected radio button from radioGroup
        // int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
       /* radioButton = (RadioButton) findViewById(selectedId);
        //if(radioButton.getText()=="Yes"){
        if(radioButton.getText().equals("Yes")){
            param=1;
        }else{
            param=0;
        }
        */
        //objHIA1.setHIA1_Test1_Question1(databasetest.HIA1_Test1_Question1);
        //Toast.makeText(getApplicationContext(), "You selected :"+ radioButton.getText(), Toast.LENGTH_SHORT).show();
        //Log.v("AGAIN:", "Check Check Ckeck: " + fragObjHia1.objHIA1.HIA1_Test1_Question1);
        //objHIA1.setHIA1_Test1_Question1(param);
        new HIA3AActivity.HIA3insertAsync(objHIA3).execute(); //Call async Task
    }

    //---------------------------------------------
    //------------JSON ----------------------------

    private class HIA3insertAsync extends AsyncTask<Void, Void, JSONArray> {

        HIA3 objectHIA3=new HIA3();
        // Alert Dialog Manager
       // AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/insertHIA3.php"; // Needs to be changed when using different php files.
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
                //    Log.d("JSON REQUEST", "Start ...");

                // PREPARING PARAMETERS..
                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                //args.put("HIA2_Test3_Question1", "test");

                args.put("HIA3_Test1_Question1", Integer.toString(this.objectHIA3.getHIA3_Test1_Question1()));

                args.put("HIA3_Test2_Question1", Integer.toString(this.objectHIA3.getHIA3_Test2_Question1()));
                args.put("HIA3_Test2_Question2", this.objectHIA3.getHIA3_Test2_Question2());
                args.put("HIA3_Test2_Question3", Integer.toString(this.objectHIA3.getHIA3_Test2_Question3()));
                args.put("HIA3_Test2_Question4", this.objectHIA3.getHIA3_Test2_Question4());
                args.put("HIA3_Test2_Question5", Integer.toString(this.objectHIA3.getHIA3_Test2_Question5()));
                args.put("HIA3_Test2_Question6", this.objectHIA3.getHIA3_Test2_Question6());
                args.put("HIA3_Test2_Question7", Integer.toString(this.objectHIA3.getHIA3_Test2_Question7()));
                args.put("HIA3_Test2_Question8", this.objectHIA3.getHIA3_Test2_Question8());

                args.put("HIA3_Test3_Question1", Integer.toString(this.objectHIA3.getHIA3_Test3_Question1()));
                args.put("HIA3_Test3_Question2", this.objectHIA3.getHIA3_Test3_Question2());
                args.put("HIA3_Test3_Question3", Integer.toString(this.objectHIA3.getHIA3_Test3_Question3()));
                args.put("HIA3_Test3_Question4", this.objectHIA3.getHIA3_Test3_Question4());
                args.put("HIA3_Test3_Question5", Integer.toString(this.objectHIA3.getHIA3_Test3_Question5()));
                args.put("HIA3_Test3_Question6", Integer.toString(this.objectHIA3.getHIA3_Test3_Question6()));
                args.put("HIA3_Test3_Question7", Integer.toString(this.objectHIA3.getHIA3_Test3_Question7()));
                args.put("HIA3_Test3_Question8", Integer.toString(this.objectHIA3.getHIA3_Test3_Question8()));
                args.put("HIA3_Test3_Question9", Integer.toString(this.objectHIA3.getHIA3_Test3_Question9()));
                args.put("HIA3_Test3_Question10", Integer.toString(this.objectHIA3.getHIA3_Test3_Question10()));
                args.put("HIA3_Test3_Question11", Integer.toString(this.objectHIA3.getHIA3_Test3_Question11()));
                args.put("HIA3_Test3_Question12", Integer.toString(this.objectHIA3.getHIA3_Test3_Question12()));
                args.put("HIA3_Test3_Question13", Integer.toString(this.objectHIA3.getHIA3_Test3_Question13()));
                args.put("HIA3_Test3_Question14", Integer.toString(this.objectHIA3.getHIA3_Test3_Question14()));
                args.put("HIA3_Test3_Question15", Integer.toString(this.objectHIA3.getHIA3_Test3_Question15()));
                args.put("HIA3_Test3_Question16", Integer.toString(this.objectHIA3.getHIA3_Test3_Question16()));
                args.put("HIA3_Test3_Question17", Integer.toString(this.objectHIA3.getHIA3_Test3_Question17()));
                args.put("HIA3_Test3_Question18", Integer.toString(this.objectHIA3.getHIA3_Test3_Question18()));
                args.put("HIA3_Test3_Question19", Integer.toString(this.objectHIA3.getHIA3_Test3_Question19()));
                args.put("HIA3_Test3_Question20", Integer.toString(this.objectHIA3.getHIA3_Test3_Question20()));
                args.put("HIA3_Test3_Question21", Integer.toString(this.objectHIA3.getHIA3_Test3_Question21()));
                args.put("HIA3_Test3_Question22", Integer.toString(this.objectHIA3.getHIA3_Test3_Question22()));
                args.put("HIA3_Test3_Question23", Integer.toString(this.objectHIA3.getHIA3_Test3_Question23()));
                args.put("HIA3_Test3_Question24", Integer.toString(this.objectHIA3.getHIA3_Test3_Question24()));
                args.put("HIA3_Test3_Question25", Integer.toString(this.objectHIA3.getHIA3_Test3_Question25()));
                args.put("HIA3_Test3_Question26", Integer.toString(this.objectHIA3.getHIA3_Test3_Question26()));
                args.put("HIA3_Test3_Question27", Integer.toString(this.objectHIA3.getHIA3_Test3_Question27()));
                args.put("HIA3_Test3_Question28", Integer.toString(this.objectHIA3.getHIA3_Test3_Question28()));
                args.put("HIA3_Test3_Question29", Integer.toString(this.objectHIA3.getHIA3_Test3_Question29()));
                args.put("HIA3_Test3_Question30", Integer.toString(this.objectHIA3.getHIA3_Test3_Question30()));
                args.put("HIA3_Test3_Question31", Integer.toString(this.objectHIA3.getHIA3_Test3_Question31()));
                args.put("HIA3_Test3_Question32", Integer.toString(this.objectHIA3.getHIA3_Test3_Question32()));
                args.put("HIA3_Test3_Question33", Integer.toString(this.objectHIA3.getHIA3_Test3_Question33()));
                args.put("HIA3_Test3_Question34", Integer.toString(this.objectHIA3.getHIA3_Test3_Question34()));
                args.put("HIA3_Test3_Question35", Integer.toString(this.objectHIA3.getHIA3_Test3_Question35()));
                args.put("HIA3_Test3_Question36", Integer.toString(this.objectHIA3.getHIA3_Test3_Question36()));
                args.put("HIA3_Test3_Question37", Integer.toString(this.objectHIA3.getHIA3_Test3_Question37()));
                args.put("HIA3_Test3_Question38", Integer.toString(this.objectHIA3.getHIA3_Test3_Question38()));
                args.put("HIA3_Test3_Question39", Integer.toString(this.objectHIA3.getHIA3_Test3_Question39()));
                args.put("HIA3_Test3_Question40", Integer.toString(this.objectHIA3.getHIA3_Test3_Question40()));
                args.put("HIA3_Test3_Question41", Integer.toString(this.objectHIA3.getHIA3_Test3_Question41()));
                args.put("HIA3_Test3_Question42", Integer.toString(this.objectHIA3.getHIA3_Test3_Question42()));
                args.put("HIA3_Test3_Question43", Integer.toString(this.objectHIA3.getHIA3_Test3_Question43()));
                args.put("HIA3_Test3_Question44", Integer.toString(this.objectHIA3.getHIA3_Test3_Question44()));
                args.put("HIA3_Test3_Question45", Integer.toString(this.objectHIA3.getHIA3_Test3_Question45()));
                args.put("HIA3_Test3_Question46", Integer.toString(this.objectHIA3.getHIA3_Test3_Question46()));
                args.put("HIA3_Test3_Question47", Integer.toString(this.objectHIA3.getHIA3_Test3_Question47()));
                args.put("HIA3_Test3_Question48", Integer.toString(this.objectHIA3.getHIA3_Test3_Question48()));
                args.put("HIA3_Test3_Question49", Integer.toString(this.objectHIA3.getHIA3_Test3_Question49()));
                args.put("HIA3_Test3_Question50", Integer.toString(this.objectHIA3.getHIA3_Test3_Question50()));
                args.put("HIA3_Test3_Question51", Integer.toString(this.objectHIA3.getHIA3_Test3_Question51()));
                args.put("HIA3_Test3_Question52", Integer.toString(this.objectHIA3.getHIA3_Test3_Question52()));
                args.put("HIA3_Test3_Question53", Integer.toString(this.objectHIA3.getHIA3_Test3_Question53()));
                args.put("HIA3_Test3_Question54", Integer.toString(this.objectHIA3.getHIA3_Test3_Question54()));
                args.put("HIA3_Test3_Question55", Integer.toString(this.objectHIA3.getHIA3_Test3_Question55()));
                args.put("HIA3_Test3_Question56", Integer.toString(this.objectHIA3.getHIA3_Test3_Question56()));
                args.put("HIA3_Test3_Question57", Integer.toString(this.objectHIA3.getHIA3_Test3_Question57()));
                args.put("HIA3_Test3_Question58", Integer.toString(this.objectHIA3.getHIA3_Test3_Question58()));
                args.put("HIA3_Test3_Question59", Integer.toString(this.objectHIA3.getHIA3_Test3_Question59()));
                args.put("HIA3_Test3_Question60", Integer.toString(this.objectHIA3.getHIA3_Test3_Question60()));
                args.put("HIA3_Test3_Question61", Integer.toString(this.objectHIA3.getHIA3_Test3_Question61()));
                args.put("HIA3_Test3_Question62", Integer.toString(this.objectHIA3.getHIA3_Test3_Question62()));
                args.put("HIA3_Test3_Question63", Integer.toString(this.objectHIA3.getHIA3_Test3_Question63()));
                args.put("HIA3_Test3_Question64", Integer.toString(this.objectHIA3.getHIA3_Test3_Question64()));
                args.put("HIA3_Test3_Question65", Integer.toString(this.objectHIA3.getHIA3_Test3_Question65()));
                args.put("HIA3_Test3_Question66", Integer.toString(this.objectHIA3.getHIA3_Test3_Question66()));
                args.put("HIA3_Test3_Question67", Integer.toString(this.objectHIA3.getHIA3_Test3_Question67()));
                args.put("HIA3_Test3_Question68", Integer.toString(this.objectHIA3.getHIA3_Test3_Question68()));
                args.put("HIA3_Test3_Question69", Integer.toString(this.objectHIA3.getHIA3_Test3_Question69()));
                args.put("HIA3_Test3_Question70", Integer.toString(this.objectHIA3.getHIA3_Test3_Question70()));
                args.put("HIA3_Test3_Question71", Integer.toString(this.objectHIA3.getHIA3_Test3_Question71()));
                args.put("HIA3_Test3_Question72", Integer.toString(this.objectHIA3.getHIA3_Test3_Question72()));
                args.put("HIA3_Test3_Question73", Integer.toString(this.objectHIA3.getHIA3_Test3_Question73()));
                args.put("HIA3_Test3_Question74", Integer.toString(this.objectHIA3.getHIA3_Test3_Question74()));
                args.put("HIA3_Test3_Question75", Integer.toString(this.objectHIA3.getHIA3_Test3_Question75()));
                args.put("HIA3_Test3_Question76", Integer.toString(this.objectHIA3.getHIA3_Test3_Question76()));
                args.put("HIA3_Test3_Question77", Integer.toString(this.objectHIA3.getHIA3_Test3_Question77()));
                args.put("HIA3_Test3_Question78", Integer.toString(this.objectHIA3.getHIA3_Test3_Question78()));
                args.put("HIA3_Test3_Question79", Integer.toString(this.objectHIA3.getHIA3_Test3_Question79()));
                args.put("HIA3_Test3_Question80", Integer.toString(this.objectHIA3.getHIA3_Test3_Question80()));
                args.put("HIA3_Test3_Question81", Integer.toString(this.objectHIA3.getHIA3_Test3_Question81()));
                args.put("HIA3_Test3_Question82", Integer.toString(this.objectHIA3.getHIA3_Test3_Question82()));
                args.put("HIA3_Test3_Question83", Integer.toString(this.objectHIA3.getHIA3_Test3_Question83()));
                args.put("HIA3_Test3_Question84", Integer.toString(this.objectHIA3.getHIA3_Test3_Question84()));
                args.put("HIA3_Test3_Question85", Integer.toString(this.objectHIA3.getHIA3_Test3_Question85()));
                args.put("HIA3_Test3_Question86", Integer.toString(this.objectHIA3.getHIA3_Test3_Question86()));
                args.put("HIA3_Test3_Question87", Integer.toString(this.objectHIA3.getHIA3_Test3_Question87()));
                args.put("HIA3_Test3_Question88", Integer.toString(this.objectHIA3.getHIA3_Test3_Question88()));
                args.put("HIA3_Test3_Question89", Integer.toString(this.objectHIA3.getHIA3_Test3_Question89()));
                args.put("HIA3_Test3_Question90", Integer.toString(this.objectHIA3.getHIA3_Test3_Question90()));
                args.put("HIA3_Test3_Question91", Integer.toString(this.objectHIA3.getHIA3_Test3_Question91()));
                args.put("HIA3_Test3_Question92", Integer.toString(this.objectHIA3.getHIA3_Test3_Question92()));

                //args.put("HIA2_Test3_Question1", "test");

                args.put("HIA3_Test4_Question1", Integer.toString(this.objectHIA3.getHIA3_Test4_Question1()));
                args.put("HIA3_Test4_Question2", Integer.toString(this.objectHIA3.getHIA3_Test4_Question2()));
                args.put("HIA3_Test4_Question3", Integer.toString(this.objectHIA3.getHIA3_Test4_Question3()));
                args.put("HIA3_Test4_Question4", Integer.toString(this.objectHIA3.getHIA3_Test4_Question4()));
                args.put("HIA3_Test4_Question5", Integer.toString(this.objectHIA3.getHIA3_Test4_Question5()));
                args.put("HIA3_Test4_Question6", this.objectHIA3.getHIA3_Test4_Question6());

                args.put("HIA3_Test5_Question1", Integer.toString(this.objectHIA3.getHIA3_Test5_Question1()));
                args.put("HIA3_Test5_Question2", Integer.toString(this.objectHIA3.getHIA3_Test5_Question2()));
                args.put("HIA3_Test5_Question3", Integer.toString(this.objectHIA3.getHIA3_Test5_Question3()));
                args.put("HIA3_Test5_Question4", Integer.toString(this.objectHIA3.getHIA3_Test5_Question4()));
                args.put("HIA3_Test5_Question5", Integer.toString(this.objectHIA3.getHIA3_Test5_Question5()));




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

    private class get_HIA1_Test7_Question5 extends AsyncTask<Void, Void, JSONArray> {

        int getHIA1T7Q5;
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/getHIA1_Test7_Question5.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public get_HIA1_Test7_Question5(int CodeHIA1){
            Log.d("JSONCONSTRUCTOR", "Start");
            Toast.makeText(getApplicationContext(), "Starting JSON", Toast.LENGTH_SHORT).show();
            this.getHIA1T7Q5=CodeHIA1;
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
                //    Log.d("JSON REQUEST", "Start ...");

                // PREPARING PARAMETERS..
                Log.d("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_HIA1", Integer.toString(this.getHIA1T7Q5));

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
            int success = 0;
            String message = "";

            if (json != null) {
                Toast.makeText(HIA3AActivity.this, json.toString(), Toast.LENGTH_LONG).show();

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);

                    if (success==1){
                        Code_HIA1 = json.getJSONObject(0).getInt("HIA1_Test7_Question5");
                        Log.d("Retrieved",Integer.toString(Code_HIA1));
                        //test spinner
                        //Code_HIA1=1;
                        //onResume();
                        setSpinner();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                Log.d("Success!", message);

                //finish();
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
    // end second Async

    public void setSpinner(){
        spinner1.setSelection(Code_HIA1);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

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

        Log.d("Before", Integer.toString(Code_HIA1));
        new HIA3AActivity.get_HIA1_Test7_Question5(1).execute();
        Log.d("After", Integer.toString(Code_HIA1));

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
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                HIA3AActivity.super.onBackPressed();
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

            spinner1 = (Spinner) rootView.findViewById(R.id.spinner29);
            this.adapter1=ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner2,android.R.layout.simple_spinner_dropdown_item);
            this.adapter1 = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner2,R.layout.multiline_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);


            Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner30);
            this.adapter2=ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner3,android.R.layout.simple_spinner_dropdown_item);
            this.adapter2 = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia3_1_spinner3,R.layout.multiline_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);

            spinner.setOnItemSelectedListener(this);
            spinner1.setOnItemSelectedListener(this);
            spinner2.setOnItemSelectedListener(this);


            return rootView;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            Activity c = getActivity();
            if(c instanceof HIA3AActivity) {
                hia3test = (HIA3AActivity) getActivity();
                switch(parent.getId()){
                    case R.id.spinner27:
                        hia3test.objHIA3.setHIA3_Test1_Question1(position);
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
                    return Balance.newInstance();
                case 4:
                    return Gait.newInstance();
                /*case 3:
                    return HIA3CFragment.newInstance();*/
                case 5:
                    return HIA3DFragment.newInstance();
                case 6:
                    return HIA3EFragment.newInstance();
                /*case 5:
                    return HIA2FFragment.newInstance();
                */

            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
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
            return "HIA3 (" + (position + 1) + "/7)";
        }

        public Fragment getFragment(int position){
            String tag = mFragmentTags.get(position);
            if(tag==null)
                return null;
            return mFragmentManager.findFragmentByTag(tag);
        }

    }
}
