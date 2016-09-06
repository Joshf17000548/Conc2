package com.example.joshf.conc;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.HashMap;

import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

/**
 * Created by joshf on 2016/07/25.
 */
public class PlayerProfile extends AppCompatActivity{
    String TAG = "Conc";
    public EditText name;
    public TextView age;
    public TextView DOB;
    public EditText height;
    public EditText weight;
    public ImageView playerPhoto;
    public TextView weightMetric;
    public TextView heightMetric;
    public ImageButton cameraButton;
    public Toolbar toolbar;
    public MenuItem item;
    public int[] DOBdata = new int[3];
    public String player_select;
    public int numberOfPlayers;
    public int playerCode;



    //private DatePicker datePicker;

    private static Player playerInFocus = new Player();

    boolean editEnabled;

    public PlayerProfile() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        //set all view items used
        name = (EditText) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.ageText);
        DOB = (TextView) findViewById(R.id.DOB);
        height = (EditText) findViewById(R.id.heightText);
        weight = (EditText) findViewById(R.id.weightText);
        playerPhoto = (ImageView) findViewById(R.id.playerFinalPhoto);
        weightMetric = (TextView) findViewById(R.id.weightMetric);
        heightMetric = (TextView) findViewById(R.id.heightMetric);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);

        Bundle extras = getIntent().getExtras();
        player_select= extras.getString("player_select");

        //New or existing player?
        if (player_select.equalsIgnoreCase("existing")) {

            playerInFocus = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("player_info")).getData();
            //Dummy Data
/*            playerInFocus.weight= 86.6;
            playerInFocus.height= 1.83;
            playerInFocus.DOB = Calendar.getInstance();
            playerInFocus.DOB.set(1993,8,26);*/
            //playerInFocus.Code_Player = ;
            updateViews();
            setInfoNoEdit();
            editEnabled = false;
        }
        else {

            playerInFocus.Code_Player = getNumberOfPlayers()+1;
            player_select = "update";
            String folder_main = String.valueOf(playerInFocus.getCode_Player());
            File file = new File(this.getExternalFilesDir(null), folder_main);
            //Log.e("File 1",file.toString());
            if (!file.exists()) {

                file.mkdirs();
            }
            setInfoEdit();
            editEnabled = true;
        }


        Bundle bund = new Bundle();
        bund.putString("player_select", player_select);
        bund.putString("run_mode", "first");
        bund.putString("player_code", String.valueOf(playerInFocus.getCode_Player()));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        PhotoFragment photoFragment = PhotoFragment.newInstance();
        photoFragment.setArguments(bund);
        transaction.replace(R.id.frameLayout, photoFragment);
        transaction.commit();
    }



    //update all view fields with players information
    public void updatePlayerInfo() {

        playerInFocus.Player_Name = name.getText().toString();
        playerInFocus.height = Double.parseDouble(height.getText().toString());
        playerInFocus.weight = Double.parseDouble(weight.getText().toString());

        setInfoNoEdit(); //Set edittext views as non editable
        updateViews();
        editEnabled=false;

/*        if (player_select.equalsIgnoreCase("new")){


        }*/
        FileOutputStream outStream = null;
        FileInputStream inStream = null;
        try {

            String folder_main = this.getExternalFilesDir(null)+"/"+String.valueOf(playerInFocus.getCode_Player());
            File fileTxt = new File(folder_main, "/"+String.valueOf(playerInFocus.getCode_Player())+".ply");
            outStream = new FileOutputStream(fileTxt);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(playerInFocus);
            objectOutStream.close();


            inStream = new FileInputStream(fileTxt);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            Player simpleClass = (Player) objectInStream.readObject();
            objectInStream.close();

        }catch (IOException e){
            Log.e(TAG,"Could not write object to file");
        }catch (ClassNotFoundException e){
            Log.e(TAG,"Could not find class");
        }

    }

    public void updateViews(){

        int[] DOBdata = playerInFocus.getDOB();//Retrieve players DOB and calculate age
        String ageString = calculateAge(DOBdata[0], DOBdata[1], DOBdata[2]);
        age.setText(ageString); //Update all textViews
        name.setText(playerInFocus.getPlayer_Name());
        weight.setText(String.valueOf(playerInFocus.getPlayer_Weight()));
        height.setText(String.valueOf(playerInFocus.getPlayer_Height()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerprofile_menu, menu);
        item = menu.findItem(R.id.action_edit);
        if (player_select.equalsIgnoreCase("existing")){
            item.setTitle(R.string.appbar_edit);
        }else{
            item.setTitle(R.string.appbar_done);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                if (!editEnabled)
                {
                    setInfoEdit();
                    item.setTitle(R.string.appbar_done);
                }
                else{
                    try {
                        updatePlayerInfo();
                        editEnabled=true;
                        item.setTitle(R.string.appbar_edit);
                        //new insertPlayer(playerInFocus).execute();

                    }   catch(Exception e){
                        Toast toast = Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setInfoEdit(){
        cameraButton.setVisibility(View.VISIBLE);
        name.setEnabled(true);
        age.setEnabled(true);
        height.setEnabled(true);
        weight.setEnabled(true);

        DOB.setText(R.string.DOB);

        if (playerInFocus.DOB != null){
            DOBdata = playerInFocus.getDOB();
            String DOBString = String.valueOf(DOBdata[0])+"/"+String.valueOf(DOBdata[1])+"/"+String.valueOf(DOBdata[2]);
            age.setText(DOBString);
        }else{
            DOBdata = new int[]{1991,1,1};
            age.setText(R.string.enter_DOB);}

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialogFragment = new DatePickerDialog(PlayerProfile.this, myDateListener,
                        DOBdata[0],
                        DOBdata[1],
                        DOBdata[2]);
                dialogFragment.show();
                String DOBString = String.valueOf(DOBdata[0])+"/"+String.valueOf(DOBdata[1])+"/"+String.valueOf(DOBdata[2]);
                age.setText(DOBString);
            }
        });

        weightMetric.setVisibility(View.GONE);
        heightMetric.setVisibility(View.GONE);



        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    cameraButton.setVisibility(View.GONE);
                    Bundle bund = new Bundle();

                    bund.putString("player_code", String.valueOf(playerInFocus.getCode_Player()));
                    CameraFragment cameraFragment = CameraFragment.newInstance();
                    cameraFragment.setArguments(bund);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, cameraFragment).addToBackStack(null).commit();
            }
        });
    }

    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            // TODO: 2016/08/03 ADD ERROR CHECKING FOR DATE SET (EG. < CURRENT DATE)
                playerInFocus.DOB = Calendar.getInstance();
                playerInFocus.getDOB_player().set(arg1, arg2 + 1, arg3);

                String DOBString = String.valueOf(arg1) + "/" + String.valueOf(arg2) + "/" + String.valueOf(arg3);
                age.setText(DOBString);
        }
    };

    public void setInfoNoEdit(){

        name.setEnabled(false);
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        cameraButton.setVisibility(View.GONE);
        DOB.setText(R.string.Age);
        //weightMetric.setVisibility(View.VISIBLE);
        //heightMetric.setVisibility(View.VISIBLE);
    }

    /*private class insertPlayer extends AsyncTask<Void, Void, JSONArray> {

        int code_player;
        int code_team;
        double height;
        double weight;
        boolean lenses;
        String name;

       // Bitmap image;


        // Alert Dialog Manager
        //AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://10.0.2.2/ConcApp/insertPlayer.php";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        public insertPlayer (Player player){

            //this.image=player.
            this.name=player.Player_Name;
            this.height=player.height;
            this.weight=player.weight;
            this.lenses=player.lenses;
        }

        @Override
        protected void onPreExecute() {
            Log.e("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(PlayerProfile.this);
            pDialog.setMessage("Attempting register...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray json) {
            Log.e("JSonInsTeam", "Finish");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (true) {
                int success = 0;
                String message = "";

                if (json != null) {
                    Toast.makeText(PlayerProfile.this, json.toString(),
                            Toast.LENGTH_LONG).show();

                    try {
                        success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                        message = json.getJSONObject(0).getString(TAG_MESSAGE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (success == 1) {
                    Log.e("Success!", message);

                    finish();
                } else {
                    // username / password doesn't match
                    //alert.showAlertDialog(PlayerProfile.this, "Login failed..", "Username/Password is incorrect", false);
                    Log.e("Failure", message);
                    finish();
                }
            } else {
                // user didn't entered username or password
                // Show alert asking him to enter the details
               // alert.showAlertDialog(PlayerProfile.this, "Login failed..", "Please enter username and password", false);
            }
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            Log.e("JSonInsTeam", "Background");
            try {
                //    Log.d("JSON REQUEST", "Start ...");
                //Converting image to String
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                // PREPARING PARAMETERS..
                Log.e("JSON REQUEST", "Preparing Params ...");
                //HashMap<String, Player> args = new HashMap<>();
                ContentValues args=new ContentValues(4);

                args.put("Player_Height", height);
                args.put("Player_Lenses", lenses);
                args.put("Player_Name", name);
                args.put("Player_Weight", weight);


                //args.put("Team_Logo", encodedImage);
                Log.e("JSON REQUEST", args.toString());
                Log.e("JSON REQUEST", "Firing Json ...");

*//*                for (String key : args.keySet()) {
                    Log.e("Key", key);
                    Log.e("KeyValue",args.getAsString(key));

                }*//*
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.e(TAG, json.toString());


                if (json != null) {
                    Log.e("JSON REQUEST", params.toString());
                    Log.e("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/

    public String calculateAge(int _year, int _month, int _day) {
        //DOES NOT WORK RIGHT. Only looks at year
        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(_year, _month, _day);
        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if (a < 0)
            throw new IllegalArgumentException("Age < 0");
        String ageStr = Long.toString(a);
        return ageStr;
    }

    public int getNumberOfPlayers() {

        File file = new File(this.getExternalFilesDir(null).toString());
        File[] list = file.listFiles();
        numberOfPlayers = list.length;

/*        for (File f : list) {
            String name = f.getName();
            if (!name.endsWith(".jpg"))
                numberOfPlayers++;
        }*/
        // Log.e("Files",String.valueOf(count));
        return numberOfPlayers;
    }
}

//TODO Deal with back button better. Before and after taking a photo. Maybe add a home button
//TODO Rather than creating a new file with each entree, just append to a single object file
