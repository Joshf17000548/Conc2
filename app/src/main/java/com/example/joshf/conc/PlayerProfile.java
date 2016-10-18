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
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;



/**
 * Created by joshf on 2016/07/25.
 */
public class PlayerProfile extends AppCompatActivity implements PhotoFragment.ImageCallback, PhotoFragment.CheckSelectedCallback{


    String TAG = "PlayerProfile";

    EditText name;
    TextView age;
    TextView DOB;
    EditText height;
    EditText weight;
    ImageButton cameraButton;
    Button startTest;
    Button playerHistory;
    Boolean databaseUpdate;

    Boolean photoLoaded;
    Boolean editEnabled;
    Boolean dateLoaded = false;
    byte[] mImage;

    public Toolbar toolbar;
    public String player_select;
    private static Player playerInFocus;


    public void onCheckSelected(){
        photoLoaded=true;
    }

    public void imageCallback(byte[] bit){
        mImage=bit;

    }
    public PlayerProfile() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "started");
        databaseUpdate=false;

        setContentView(R.layout.player_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        name  = (EditText) findViewById(R.id.name);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        age = (TextView) findViewById(R.id.ageText);
        DOB = (TextView) findViewById(R.id.DOB);
        height = (EditText) findViewById(R.id.heightText);
        weight = (EditText) findViewById(R.id.weightText);

        //Button playerInfo =(Button) findViewById(R.id.playerInfo);
        startTest =(Button) findViewById(R.id.startTest);
        playerHistory =(Button) findViewById(R.id.playerHistory);

        Bundle extras = getIntent().getExtras();

        Bundle bund = new Bundle();
        player_select= extras.getString("player_select");
        //New or existing player?
        if (player_select.equalsIgnoreCase("existing")) {
            playerInFocus = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("player_info")).getData();
            bund.putBinder("player_info", new ObjectWrapperForBinder(playerInFocus));
            setInfoNoEdit();
            updateViews();

            photoLoaded = true;
            editEnabled = false;

        }
        else {
            JSONObject jsonObject = null;
            playerInFocus = new Player(jsonObject);
            setInfoEdit();

            photoLoaded = false;
            editEnabled = true;
        }
        bund.putString("player_select", player_select);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        PhotoFragment photoFragment = PhotoFragment.newInstance();
        photoFragment.setArguments(bund);
        transaction.replace(R.id.playerPhoto, photoFragment, "PhotoFragment");
        transaction.commit();

        playerHistory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                /* Raise view on ACTION_DOWN and lower it on ACTION_UP. */
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ACTION_DOWN on view.");
                        view.setTranslationZ(8);
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP on view.");
                        view.setTranslationZ(0);
                        view.setPressed(false);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        startTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                /* Raise view on ACTION_DOWN and lower it on ACTION_UP. */
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ACTION_DOWN on view.");
                        view.setTranslationZ(8);
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP on view.");
                        view.setTranslationZ(0);
                        view.setPressed(false);
                        startActivity(new Intent(PlayerProfile.this, TestMenuActivity.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                cameraButton.setVisibility(View.GONE);
                photoLoaded = false;
                CameraFragment cameraFragment = CameraFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.playerPhoto, cameraFragment).commit();
            }
        });
    }

    private void setInfoNoEdit(){
        name.setEnabled(false);
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        cameraButton.setVisibility(View.GONE);
        DOB.setText(R.string.Age);
        age.setTextColor(getResources().getColor(R.color.textPrimary));
}

    public void updateViews() {
        Integer[] DOBdata = playerInFocus.getDOBInt();//Retrieve players DOB and calculate age
        String ageString = calculateAge(DOBdata[0], DOBdata[1], DOBdata[2]);
        age.setText(ageString); //Update all textViews
        name.setText(playerInFocus.getPlayer_Name());
        weight.setText(String.valueOf(playerInFocus.getPlayer_Weight()));
        height.setText(String.valueOf(playerInFocus.getPlayer_Height()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.playerprofile_menu, menu);
        MenuItem item = menu.findItem(R.id.action_edit);
        if (player_select.equalsIgnoreCase("existing")) {
            item.setTitle(R.string.appbar_edit);
        } else {
            item.setTitle(R.string.appbar_done);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                if (!editEnabled) {
                    setInfoEdit();
                    item.setTitle(R.string.appbar_done);
                    editEnabled = true;
                } else {

                    if (updatePlayerInfo()) {
                        item.setTitle(R.string.appbar_edit);
                        editEnabled = false;
                        setInfoNoEdit();

                    } else {
                        Toast toast = Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setInfoEdit() {

        final Integer[] DOBdata;

        cameraButton.setVisibility(View.VISIBLE);
        name.setEnabled(true);
        age.setEnabled(true);
        height.setEnabled(true);
        weight.setEnabled(true);
        DOB.setText(R.string.DOB);

        if (!player_select.equalsIgnoreCase("new")) {
            age.setText(playerInFocus.getDOB_player().toString());
            DOBdata = playerInFocus.getDOBInt();
        } else {
            DOBdata = new Integer[]{1991, 1, 1};
            age.setText(R.string.enter_DOB);
        }

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialogFragment = new DatePickerDialog(PlayerProfile.this, myDateListener,
                        DOBdata[0],
                        DOBdata[1],
                        DOBdata[2]);
                dialogFragment.show();
            }
        });
    }

    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            // TODO: 2016/08/03 ADD ERROR CHECKING FOR DATE SET (EG. < CURRENT DATE)
            if(arg2<10) {
                playerInFocus.Player_DateOfBirth = String.valueOf(arg1) + "-0" + String.valueOf(arg2);

            }else{
                playerInFocus.Player_DateOfBirth = String.valueOf(arg1) + "-" + String.valueOf(arg2);
            }
            if(arg3<10) {
                playerInFocus.Player_DateOfBirth = playerInFocus.Player_DateOfBirth + "-0" + String.valueOf(arg3);

            }else{
                playerInFocus.Player_DateOfBirth = playerInFocus.Player_DateOfBirth + "-" + String.valueOf(arg3);
            }

            age.setText(playerInFocus.Player_DateOfBirth);
            dateLoaded = true;
            age.setTextColor(getResources().getColor(R.color.textPrimary));
        }
    };
    public boolean updatePlayerInfo() {
        Boolean success = false;

        if (dateLoaded.equals(true) && (name.getText().toString().length()>0) && (photoLoaded.equals(true)))
        {
            try {
                playerInFocus.Player_Name = name.getText().toString();

                playerInFocus.Player_Height = Double.parseDouble(height.getText().toString());
                playerInFocus.Player_Weight = Double.parseDouble(weight.getText().toString());


                setInfoNoEdit(); //Set edittext views as non editable
                updateViews();

               // if (player_select.equals("new"))
                new insertPlayer().execute();
/*                        else
                            new updatePlayer().execute();*/
                databaseUpdate=true;

                success = true;
            } catch (Exception e) {
                Log.e(TAG, "All fields not completed");
            }
        }

        return success;
    }

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

    private class insertPlayer extends AsyncTask<Void, Void, JSONArray> {

        // Alert Dialog Manager
        AlertDialogManager alert = new AlertDialogManager();

        private static final String URL = "http://104.198.254.110/ConcApp/insertPlayer.php"; // Needs to be changed when using different php files.
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(PlayerProfile.this);
            pDialog.setMessage("Inserting Player");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.e("JSonInsPlayer", "Background");
            try {
                // PREPARING PARAMETERS..

                Log.e("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();

                Bitmap myBitmap = BitmapFactory.decodeByteArray(mImage, 0, mImage.length);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                Log.e("JSON REQUEST", "Image Done");

                args.put("Player_Name",String.valueOf(playerInFocus.getPlayer_Name()));
                args.put("Player_Height", String.valueOf(playerInFocus.getPlayer_Height()));
                args.put("Player_Weight", String.valueOf(playerInFocus.getPlayer_Weight()));
                args.put("Player_DateOfBirth", playerInFocus.getDOB_player());
                args.put("Code_Team", "3");
                args.put("Player_Lenses", "1");
                args.put("Player_Photo", encodedImage);
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
            }
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            player_select="existing";

        }
    }

    public void onBackPressed(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("database_update", databaseUpdate);
        startActivity(new Intent(PlayerProfile.this, PlayerSelect.class).putExtras(bundle));
        finish();
    }

}

//TODO Deal with back button better. Before and after taking a photo. Maybe add a home button
//TODO Rather than creating a new file with each entree, just append to a single object file
