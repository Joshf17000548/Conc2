package com.example.joshf.conc;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.joshf.conc.R.id.playerList;
import static com.example.joshf.conc.R.id.playerPhoto;
import static com.example.joshf.conc.R.id.up;

public class PlayerSelect extends AppCompatActivity implements SwipeListViewFragment.ListSelected {

    String TAG = "PlayerSelect";

    private static final String DIALOG = "dialog";
    private static final int REQUEST_CALENDAR_READ_PERMISSION = 2;
    private static final int REQUEST_CALENDAR_WRITE_PERMISSION = 3;

    SearchView searchView;
    SearchManager searchManager;
    ArrayList<Player> playerList;
    Boolean  updateRequired=true;
    CalendarHandler calendarHandler;
    Player selectedPlayer;
    int team_code;
    SessionManager session;

    public PlayerSelect() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        session = new SessionManager(this);
        session.checkLogin();

        Bundle extras = getIntent().getExtras();
        team_code = extras.getInt("team");



/*        if (checkSelfPermission(Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            requestCalendarReadPermission(Manifest.permission.READ_CALENDAR);
        }

        if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            requestCalendarWritePermission(Manifest.permission.WRITE_CALENDAR);

            return;
        }
        calendarHandler = new CalendarHandler();
        calendarHandler.getCalendarHandler(this);*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("player_select", "new");
                startActivity(new Intent(PlayerSelect.this, PlayerProfile.class).putExtras(bundle));
            }
        });
    }

    public void listItemSelected(Player listSelectedPlayer, View view){
        selectedPlayer=listSelectedPlayer;
        Intent intent = new Intent(this, PlayerProfile.class);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.playerPhoto),"photo"));
/*                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.name), "name"),
                new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.playerHealth), "brain"));*/

        Bundle bundle = options.toBundle();
        intent.putExtra("player_select", "existing");
        intent.putExtra("player_info", selectedPlayer);
        startActivity(intent,bundle);

    }

    @Override
    public void onResume() {

        updateRequired = getIntent().getExtras().getBoolean("database_update");
        super.onResume();
        if ((updateRequired.equals(true)) ) {
            new getPlayers().execute();
        }
        else{
            try {
                playerList = (ArrayList<Player>) InternalStorage.readObject(this, "playerList");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SwipeListViewFragment playerListFragment = SwipeListViewFragment.newInstance();
                Bundle bund = new Bundle();
                bund.putSerializable("player_list", playerList);
                playerListFragment.setArguments(bund);
                transaction.replace(R.id.playerList, playerListFragment);
                transaction.commit();

            }catch(ClassNotFoundException e){
                Log.e(TAG, "Could not find class in cache");
            }catch (IOException f){
                Log.e(TAG, "Could not read object from cache");

            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            InternalStorage.writeObject(this, "playerList", playerList);
            Log.e("player_select", "inserted");
        }catch (IOException e){
            Log.e(TAG, "Could not write object to cache");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerselect_menu, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar .setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

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

                args.put("Code_Team", String.valueOf(team_code));

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


    private void requestCalendarReadPermission(String permission) {
        if (shouldShowRequestPermissionRationale(permission)) {
            new ConfirmationDialog().show(getFragmentManager(), DIALOG);
        } else {
            requestPermissions(new String[]{permission},
                    REQUEST_CALENDAR_READ_PERMISSION);
        }
    }

    private void requestCalendarWritePermission(String permission) {
        if (shouldShowRequestPermissionRationale(permission)) {
            new ConfirmationDialog().show(getFragmentManager(), DIALOG);
        } else {
            requestPermissions(new String[]{permission},
                    REQUEST_CALENDAR_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CALENDAR_READ_PERMISSION: {

                if (requestCode == REQUEST_CALENDAR_READ_PERMISSION) {
                    if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                        ErrorDialog.newInstance(getString(R.string.request_calendar_read_permission))
                                .show(getFragmentManager(), DIALOG);
                    }
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
            case REQUEST_CALENDAR_WRITE_PERMISSION: {

                if (requestCode == REQUEST_CALENDAR_WRITE_PERMISSION) {
                    if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                        ErrorDialog.newInstance(getString(R.string.request_calendar_write_permission))
                                .show(getFragmentManager(), DIALOG);
                    }
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }

    public static class ErrorDialog extends DialogFragment {

        private static final String ARG_MESSAGE = "message";

        public static ErrorDialog newInstance(String message) {
            ErrorDialog dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, message);
            dialog.setArguments(args);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Activity activity = getActivity();
            return new AlertDialog.Builder(activity)
                    .setMessage(getArguments().getString(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    })
                    .create();
        }
    }

    /**
     * Shows OK/Cancel confirmation dialog about camera permission.
     */
    public static class ConfirmationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.request_calendar_read_permission)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(
                                    new String[]{Manifest.permission.READ_CALENDAR},
                                    REQUEST_CALENDAR_READ_PERMISSION);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .create();
        }
    }
}


//TODO Clean up code for dramatic performance increase.Consider Asynch upload
//TODO find a way to compress picture when saving it, so that upload will be much quicker

