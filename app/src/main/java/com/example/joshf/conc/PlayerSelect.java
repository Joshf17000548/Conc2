package com.example.joshf.conc;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerSelect extends AppCompatActivity{

    String TAG = "PlayerSelect";

    SearchView searchView;
    SearchManager searchManager;
    public ArrayList<Integer> playerCodeList;
    public ArrayList<Player> playerList;
    Boolean  updateRequired=true;
    Boolean firstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //firstRun=true;

        if(savedInstanceState!=null) {

            Log.e(TAG,getIntent().getExtras().toString());
            Bundle extras = getIntent().getExtras();
            updateRequired = extras.getBoolean("database_update");
            Log.e("test1", savedInstanceState.getString("test"));
            Log.e("test2", String.valueOf(updateRequired));

            if(!updateRequired.equals(true)){
                Log.e("put binder", "put binder");
                playerList =(ArrayList<Player>) savedInstanceState.getSerializable("player_list");
                firstRun=false;
            }
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("player_select", "new");
                bundle.putIntegerArrayList("code_list", playerCodeList);
                startActivity(new Intent(PlayerSelect.this, PlayerProfile.class).putExtras(bundle));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((updateRequired.equals(true)) ) {
            new getPlayers().execute();
        }
    }
/*    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("destroy","destory");

    }
    @Override
    public void onPause() {
        super.onPause();
        finish();
        Log.e("pause","pause");

    }*/

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){

        Log.e("test", "save");
       // savedInstanceState.putSerializable("player_list", playerList);
        savedInstanceState.putString("test", "werk asb");



        //ArrayList<Player> list =(ArrayList<Player>) savedInstanceState.getSerializable("player_list");
        //Log.e("put binder", list.get(1).getPlayer_Name());
        super.onSaveInstanceState(savedInstanceState);

    }



/*    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("test", "restore");

        Log.e("test1", savedInstanceState.getString("test"));

*//*        savedInstanceState.getSerializable("player_list");

        ArrayList<Player> list =(ArrayList<Player>) savedInstanceState.getSerializable("player_list");
        Log.e("put binder", list.get(1).getPlayer_Name());*//*


    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerselect_menu, menu);

        searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class getPlayers extends AsyncTask<String, String, JSONArray> {

        private static final String URL = "http://104.198.254.110/ConcApp/getPlayer.php";
        JSONParser jsonParser = new JSONParser();
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            Log.e("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(PlayerSelect.this);
            pDialog.setMessage("Loading Player List");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                // PREPARING PARAMETERS..
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_Team","3");

                Log.e("request", "starting");

                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);

                if (json != null) {
                    Log.e("JSON REQUEST", params.toString());
                    Log.e("JSON result", json.toString());
                    // RETURNING THE ANSWER FROM THE SERVER TO
                    // onPostExecute IN THE MAIN THREAD
                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            playerList = new ArrayList<Player>();
            playerList= Player.fromJson(jsonArray);

            if (!playerList.isEmpty())
            {
                Log.e(TAG,"Opening List View Fragment");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SwipeListViewFragment playerListFragment = SwipeListViewFragment.newInstance();
                Bundle bund = new Bundle();
                bund.putSerializable("player_list", playerList);
                playerListFragment.setArguments(bund);
                transaction.replace(R.id.playerList, playerListFragment);
                transaction.commit();

            } else {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                EmptyListFragment emptyListFragment = EmptyListFragment.newInstance();
                transaction.replace(R.id.playerList, emptyListFragment);
                transaction.commit();
            }
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
}


//TODO Clean up code for dramatic performance increase.Consider Asynch upload
//TODO find a way to compress picture when saving it, so that upload will be much quicker

