package com.example.joshf.conc;

/**
 * Created by Josh_PC on 2016/11/02.
 */

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class TeamInsert extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    ImageView teamImage;
    EditText teamName;
    SessionManager session;
    Button teamSendRegister;
    byte[] imageData;
    String nameData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_insert);
        session = new SessionManager(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.insert_player_toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        teamImage = (ImageView) findViewById(R.id.teamImage);
        teamName  = (EditText) findViewById(R.id.teamName);
        teamSendRegister = (Button) findViewById(R.id.teamSendRegister);

        teamSendRegister.setOnClickListener(this);
        teamImage.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        session.checkLogin();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.teamImage:
                try {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                }catch(Exception e){

                    Log.d("ImageParser:", e.toString());
                    e.printStackTrace();
                }

                break;
            case R.id.teamSendRegister:
                try {
                    Log.d("ImageParser:", "Call 1 ");
                    nameData = teamName.getText().toString();
                    new InsertTeam().execute();

                }catch(Exception e){
                   // Toast toast = Toast.makeText(this, R.string.empty_field, Toast.LENGTH_SHORT);
                   // toast.show();
                }

                //Toast.makeText(getApplicationContext(), "Sent!", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ImageParser:", "Return 1 ");
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
            } else {
                //   Bundle extras = data.getExtras();
                //    Bitmap imageBitmap = (Bitmap) extras.get("data");
                try{
/*                    Uri selectedImage = data.getData();
                    teamImage.setImageURI(selectedImage);*/
                    InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                    imageData = getBytes(inputStream);
                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageData,0 , imageData.length);
                    teamImage.setImageBitmap(imageBitmap);
                }catch(Exception e){
                    Log.d("ImageParser:", e.toString());
                }
                // teamImage.setImageBitmap(imageBitmap);
                // Toast.makeText(getApplicationContext(), "Kiff!", Toast.LENGTH_LONG).show();
                Log.d("ImageParser:", "Return 2");
            }
        }else{
            Toast.makeText(getApplicationContext(), "Oopss!", Toast.LENGTH_LONG).show();
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hia, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.menuId_logout:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class InsertTeam extends AsyncTask<Void, Void, JSONArray> {


        private static final String URL = "https://www.concussionassessment.net/ConcApp/insertTeams.php";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            Log.e("JSonInsTeam", "Start");
            pDialog = new ProgressDialog(TeamInsert.this);
            pDialog.setMessage("Inserting Team");
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
            Intent intent = new Intent(TeamInsert.this, TeamSelect.class);
            intent.putExtra("update_required", true);
            startActivity(intent);

            }

        @Override
        protected JSONArray doInBackground(Void... params) {
            Log.e("JSonInsTeam", "Background");
            try {
                //    Log.d("JSON REQUEST", "Start ...");
                //Converting image to String
                Bitmap myBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(myBitmap, 400, 400, false);
                scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                // PREPARING PARAMETERS..
                Log.e("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();
                args.put("Team_Name", nameData);
                args.put("SecToken", session.getUserDetails().get("token"));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));

                args.put("Team_Logo", encodedImage);
                //   Log.d("JSON REQUEST", args.toString());
                Log.e("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);

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
    }




}
