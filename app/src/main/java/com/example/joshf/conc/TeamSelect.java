package com.example.joshf.conc;

import android.Manifest;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by joshf on 2016/10/30.
 */
public class TeamSelect extends AppCompatActivity implements SwipeListTeam.ListSelected {

    String TAG = "TeamSelect";
    SearchView searchView;
    SearchManager searchManager;
    public ArrayList<Team> teamList;
    Boolean updateRequired = true;
    SessionManager session;
    private Team selectedTeam;

    public void listItemSelected(Team listSelectedTeam, View view) {
        selectedTeam = listSelectedTeam;
        teamSelected(view);
    }

    public TeamSelect() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

/*        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);*/

    }

    @Override
    public void onResume() {
        super.onResume();

        Team team = new Team();
        teamList = new ArrayList<Team>();
        team.Team_Name = "Maties";
        team.Code_Team = 3;
        teamList.add(0, team);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        SwipeListTeam teamListFragment = SwipeListTeam.newInstance();
        Bundle bund = new Bundle();
        bund.putSerializable("team_list", teamList);
        teamListFragment.setArguments(bund);
        transaction.replace(R.id.teamList, teamListFragment);
        transaction.commitAllowingStateLoss();
        if ((updateRequired.equals(true))) {
            //new getTeams().execute();

        }/* else {
            try {
                teamList = (ArrayList<Team>) InternalStorage.readObject(this, "teamList");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SwipeListTeam teamListFragment = SwipeListTeam.newInstance();
                Bundle bund = new Bundle();
                bund.putSerializable("team_list", teamList);
                teamListFragment.setArguments(bund);
                transaction.replace(R.id.teamList, teamListFragment);
                transaction.commit();

            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not find class in cache");
            } catch (IOException f) {
                Log.e(TAG, "Could not read object from cache");

            }
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();

/*        try {
            InternalStorage.writeObject(this, "teamList", teamList);
            //   Log.e("player_select", "inserted");
        } catch (IOException e) {
            Log.e(TAG, "Could not write object to cache");
        }*/
    }


    private void teamSelected(View view) {

        Intent intent = new Intent(this, PlayerSelect.class);

/*        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.playerPhoto),"photo"));
*//*                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.name), "name"),
                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.playerHealth), "brain"));*/

        //Bundle bundle = options.toBundle();
        intent.putExtra("team",selectedTeam);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerselect_menu, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);


        searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // newGame();
                return true;
            case R.id.menuId_logout:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*    private class getTeams extends AsyncTask<String, String, JSONArray> {

        private static final String URL = "http://104.198.254.110/ConcApp/getTeams.php";
        JSONParser jsonParser = new JSONParser();
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            Log.e("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(TeamSelect.this);
            pDialog.setMessage("Loading Team List");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                // PREPARING PARAMETERS..
                HashMap<String, String> args = new HashMap<>();

                //args.put("Code_Team","3");

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

            teamList = new ArrayList<Team>();
            teamList = Team.fromJson(jsonArray);

            if (!teamList.isEmpty()) {
                Log.e(TAG, "Opening List View Fragment");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SwipeListTeam teamListFragment = SwipeListTeam.newInstance();
                Bundle bund = new Bundle();
                bund.putSerializable("team_list", teamList);
                teamListFragment.setArguments(bund);
                transaction.replace(R.id.teamList, teamListFragment);
                transaction.commitAllowingStateLoss();

            } else {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                NoTeams emptyListFragment = NoTeams.newInstance();
                transaction.replace(R.id.teamList, emptyListFragment);
                transaction.commit();
            }
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }*/
}


