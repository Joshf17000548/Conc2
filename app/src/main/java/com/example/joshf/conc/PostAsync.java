package com.example.joshf.conc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Rafael on 30/06/2016.
 */
class PostAsync extends AsyncTask<String, String, JSONArray> {


    // variables to store: username, password from EditText
    String username, password , token, admin_Status, User_Code;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Store the Activity from PostAsync was called.
    private Context mContext;

    // Session Manager Class
    SessionManager session;

    public PostAsync(Context context) {
        mContext = context;
    }

    JSONParser jsonParser = new JSONParser();

    private ProgressDialog pDialog;
    private boolean fieldFilled = true;


    // private static final String LOGIN_URL = "http://10.0.2.2/ConcApp/loginTest.php";
    private static final String LOGIN_URL = "https://www.concussionassessment.net/ConcApp/loginTest.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



    @Override
    protected void onPreExecute() {
        // Session Manager
        session = new SessionManager(mContext);
        //Toast.makeText(mContext, "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Attempting login...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected JSONArray doInBackground(String... args) {
        username=args[0];
        password=args[1];
        // Check if username, password is filled
        if(username== null || username.isEmpty() || password.isEmpty() || password==null) {
            fieldFilled =false;
            Log.d("Fields", "Empty");
        }else{
            Log.d("Fields", "FULL");
            fieldFilled =true;
            // Conecting with database and retrieving data
            try {

                // PREPARING PARAMETERS..
                HashMap<String, String> params = new HashMap<>();
                params.put("User_Name", username);
                params.put("User_Password", password);



                Log.d("request", "starting");

                JSONArray json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                Log.d("JSON REQUEST", params.toString());
                Log.d("JSON result", json.toString());

                if (json != null) {
                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("JSON REQUEST", "stack",e);
            }
        }
        return null;
    }

    protected void onPostExecute(JSONArray json) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
        if (fieldFilled) {
            int success = 0;
            String message = "";

            if (json != null) {
              //  Toast.makeText(mContext, json.toString(),
                //        Toast.LENGTH_LONG).show();

                try {
                    success = json.getJSONObject(0).getInt(TAG_SUCCESS);
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);
                    admin_Status = json.getJSONObject(0).getString("UserDoctor_Adm");
                    token = json.getJSONObject(0).getString("UserDoctor_secToken");
                    User_Code = json.getJSONObject(0).getString("Code_UserDoctor");

                    Log.e("Post Async admin", admin_Status);
                    Log.e("Post Async token", token);
                    Log.e("Post Async User_Code", User_Code);
/*                    message = json.getJSONObject(0).getString();
                    message = json.getJSONObject(0).getString(TAG_MESSAGE);*/
                    // HERE WE TAKE THE TOKEN AND THE ADM PARAMETERS.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (success == 1) {
                Log.e("Success!", message);
                session.createLoginSession(username,token,admin_Status, User_Code); // here username, token and adm status

                // Staring MainActivity
                Intent i = new Intent(mContext, TeamSelect.class);
                mContext.startActivity(i);

                ((Activity) mContext).finish();
            } else {
                // username / password doesn't match
                alert.showAlertDialog(mContext, "Login failed..", "Username/Password is incorrect", false);
                Log.d("Failure", message);

            }
        } else {
            // user didn't entered username or password
            // Show alert asking him to enter the details
            alert.showAlertDialog(mContext, "Login failed..", "Please enter username and password", false);

        }
    }
}