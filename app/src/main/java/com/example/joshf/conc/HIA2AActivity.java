package com.example.joshf.conc;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
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

        import android.widget.ArrayAdapter;
        import android.widget.RadioGroup;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;

        import java.util.HashMap;
        import java.util.Map;

public class HIA2AActivity extends AppCompatActivity
        implements HIA2DFragment.OnOrienSelectedListener, HIA2GFragment.OnConcSelectedListener
{
    testResults attemp1 = new testResults();
    HIA2EFragment articleFrag;
    private static final String TAG = "Tag Check";
    SectionsPagerAdapter mSectionsPagerAdapter;
    HIA2 objHIA2=new HIA2();
    static Spinner spinner;
    public int tabbed_pos;
    AlertDialogManager alert = new AlertDialogManager();

    static int Code_HIA1;


    ViewPager mViewPager;

    //This function calls AsyncTask [insertHIA1], which submit the HIA1 data to insertHIA1.php file.
    public void submitHIA2(View view) {
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
        new HIA2AActivity.HIA2insertAsync(objHIA2).execute(); //Call async Task
    }
    //---------------------------------------------
    //------------JSON ----------------------------

    private class HIA2insertAsync extends AsyncTask<Void, Void, JSONArray> {

        HIA2 objectHIA2=new HIA2();
        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/insertHIA2.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public HIA2insertAsync(HIA2 objectHIA2Param){
            Log.d("JSONCONSTRUCTOR", "Start");
            Toast.makeText(getApplicationContext(), "Starting JSON", Toast.LENGTH_SHORT).show();
            this.objectHIA2=objectHIA2Param;
        }

        @Override
        protected void onPreExecute() {
            Log.d("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(HIA2AActivity.this);
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

                args.put("HIA2_Test2_Question1", Integer.toString(this.objectHIA2.getHIA2_Test2_Question1()));
                args.put("HIA2_Test2_Question2", Integer.toString(this.objectHIA2.getHIA2_Test2_Question2()));
                args.put("HIA2_Test2_Question3", Integer.toString(this.objectHIA2.getHIA2_Test2_Question3()));
                args.put("HIA2_Test2_Question4", Integer.toString(this.objectHIA2.getHIA2_Test2_Question4()));
                args.put("HIA2_Test2_Question5", Integer.toString(this.objectHIA2.getHIA2_Test2_Question5()));
                args.put("HIA2_Test2_Question6", Integer.toString(this.objectHIA2.getHIA2_Test2_Question6()));
                args.put("HIA2_Test2_Question7", Integer.toString(this.objectHIA2.getHIA2_Test2_Question7()));
                args.put("HIA2_Test2_Question8", Integer.toString(this.objectHIA2.getHIA2_Test2_Question8()));
                args.put("HIA2_Test2_Question9", Integer.toString(this.objectHIA2.getHIA2_Test2_Question9()));
                args.put("HIA2_Test2_Question10", Integer.toString(this.objectHIA2.getHIA2_Test2_Question10()));
                args.put("HIA2_Test2_Question11", Integer.toString(this.objectHIA2.getHIA2_Test2_Question11()));
                args.put("HIA2_Test2_Question12", Integer.toString(this.objectHIA2.getHIA2_Test2_Question12()));
                args.put("HIA2_Test2_Question13", Integer.toString(this.objectHIA2.getHIA2_Test2_Question13()));
                args.put("HIA2_Test2_Question14", Integer.toString(this.objectHIA2.getHIA2_Test2_Question14()));
                args.put("HIA2_Test2_Question15", Integer.toString(this.objectHIA2.getHIA2_Test2_Question15()));
                args.put("HIA2_Test2_Question16", Integer.toString(this.objectHIA2.getHIA2_Test2_Question16()));
                args.put("HIA2_Test2_Question17", Integer.toString(this.objectHIA2.getHIA2_Test2_Question17()));
                args.put("HIA2_Test2_Question18", Integer.toString(this.objectHIA2.getHIA2_Test2_Question18()));
                args.put("HIA2_Test2_Question19", Integer.toString(this.objectHIA2.getHIA2_Test2_Question19()));
                args.put("HIA2_Test2_Question20", Integer.toString(this.objectHIA2.getHIA2_Test2_Question20()));
                args.put("HIA2_Test2_Question21", Integer.toString(this.objectHIA2.getHIA2_Test2_Question21()));
                args.put("HIA2_Test2_Question22", Integer.toString(this.objectHIA2.getHIA2_Test2_Question22()));

                args.put("HIA2_Test3_Question1", this.objectHIA2.getHIA2_Test3_Question1());

                args.put("HIA2_Test4_Question1", Integer.toString(this.objectHIA2.getHIA2_Test4_Question1()));
                args.put("HIA2_Test4_Question2", Integer.toString(this.objectHIA2.getHIA2_Test4_Question2()));
                args.put("HIA2_Test4_Question3", Integer.toString(this.objectHIA2.getHIA2_Test4_Question3()));
                args.put("HIA2_Test4_Question4", Integer.toString(this.objectHIA2.getHIA2_Test4_Question4()));
                args.put("HIA2_Test4_Question5", Integer.toString(this.objectHIA2.getHIA2_Test4_Question5()));
                args.put("HIA2_Test4_Question6", Integer.toString(this.objectHIA2.getHIA2_Test4_Question6()));
                args.put("HIA2_Test4_Question7", Integer.toString(this.objectHIA2.getHIA2_Test4_Question7()));

                args.put("HIA2_Test5_Question1", Integer.toString(this.objectHIA2.getHIA2_Test5_Question1()));
                args.put("HIA2_Test5_Question2", Integer.toString(this.objectHIA2.getHIA2_Test5_Question2()));

                args.put("HIA2_Test6_Question1", Integer.toString(this.objectHIA2.getHIA2_Test6_Question1()));

                args.put("HIA2_Test7_Question1", Integer.toString(this.objectHIA2.getHIA2_Test7_Question1()));

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
                Toast.makeText(HIA2AActivity.this, json.toString(), Toast.LENGTH_LONG).show();

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
                alert.showAlertDialog(HIA2AActivity.this, "Insert failed..", "Something went wrong, see Failure Log", false);
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
       // AlertDialogManager alert = new AlertDialogManager();

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
            pDialog = new ProgressDialog(HIA2AActivity.this);
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
                Toast.makeText(HIA2AActivity.this, json.toString(), Toast.LENGTH_LONG).show();

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
                alert.showAlertDialog(HIA2AActivity.this, "Insert failed..", "Something went wrong, see Failure Log", false);
                Log.d("Failure", message);
                finish();
            }
        }


    }
    //-------END JSON----------------
    //-------------------------------
    // end second Async

    public void setSpinner(){
        spinner.setSelection(Code_HIA1);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

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

        Log.d("Before", Integer.toString(Code_HIA1));
        new HIA2AActivity.get_HIA1_Test7_Question5(1).execute();
        Log.d("After", Integer.toString(Code_HIA1));

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
            AlertDialog.Builder builder = new AlertDialog.Builder(HIA2AActivity.this);
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
        AlertDialog.Builder backbut = new AlertDialog.Builder(HIA2AActivity.this);
        backbut.setMessage(R.string.dialog_back)
                .setTitle(R.string.dialog_back_title);
        // 3. Get the AlertDialog from create()
        // Add the buttons


        backbut.setPositiveButton(R.string.dialog_back_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                HIA2AActivity.super.onBackPressed();
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
    public static class PlaceholderFragment extends Fragment {
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
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hia2_a, container, false);


            spinner = (Spinner) rootView.findViewById(R.id.spinner4);

            this.adapter=ArrayAdapter.createFromResource(this.getActivity(),R.array.hia2_1_spinner,android.R.layout.simple_spinner_dropdown_item);
            this.adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.hia2_1_spinner,R.layout.multiline_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            Log.d("Spinner Value Test:",Integer.toString(Code_HIA1));
            spinner.setSelection(Code_HIA1);


            return rootView;
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
                case 1:
                    return HIA2BFragment.newInstance();
                case 2:
                    return HIA2CFragment.newInstance();
                case 3:
                    return HIA2DFragment.newInstance();
                case 4:
                    return HIA2GFragment.newInstance();
                case 5:
                    return HIA2EFragment.newInstance();
                case 6:
                    return Balance.newInstance();
                case 7:
                    return Gait.newInstance();
                case 8:
                    return UpperLimbCoordination.newInstance();
                case 9:
                    return HIA2FFragment.newInstance();

            }

            return null;
        }

        @Override
        public int getCount() {

            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "HIA2 (" + (position + 1) + "/10)";
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

