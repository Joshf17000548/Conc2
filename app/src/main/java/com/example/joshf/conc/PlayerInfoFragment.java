package com.example.joshf.conc;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by joshf on 2016/09/14.
 */
/*public class PlayerInfoFragment extends Fragment {


    String TAG = "PlayerInfoFragment";

    public EditText name;
    public TextView age;
    public TextView DOB;
    public EditText height;
    public EditText weight;
    public TextView weightMetric;
    public TextView heightMetric;
    public ImageButton cameraButton;
    private ImageButton check;

    private String player_select;
    private Boolean editEnabled;

    private byte[] mImage;


    private static Player playerInFocus;
    private ArrayList<Integer> playerCodeList;

    private Boolean photoLoaded;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_info, container, false);

        Activity activity = getActivity();
        age = (TextView) view.findViewById(R.id.ageText);
        DOB = (TextView) view.findViewById(R.id.DOB);
        height = (EditText) view.findViewById(R.id.heightText);
        weight = (EditText) view.findViewById(R.id.weightText);
        weightMetric = (TextView) view.findViewById(R.id.weightMetric);
        heightMetric = (TextView) view.findViewById(R.id.heightMetric);
        name = (EditText) activity.findViewById(R.id.name);
        cameraButton = (ImageButton) activity.findViewById(R.id.cameraButton);
        check = (ImageButton) activity.findViewById(R.id.check);

        player_select = getArguments().getString("player_select");
        playerCodeList = getArguments().getIntegerArrayList("code_list");

        setHasOptionsMenu(true);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (player_select.equalsIgnoreCase("new")) {
            JSONObject jsonObject = null;
            playerInFocus = new Player(jsonObject);
            playerInFocus.Code_Player = calculatePlayerCode(playerCodeList);
            setInfoEdit();
            photoLoaded = false;
            editEnabled = true;

        } else {
            playerInFocus = ((ObjectWrapperForBinder) getArguments().getBinder("player_info")).getData();
            setInfoNoEdit();
            updateViews();
            photoLoaded = true;
            editEnabled = false;
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.playerprofile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.action_edit);
        if (player_select.equalsIgnoreCase("existing")) {
            item.setTitle(R.string.appbar_edit);
        } else {
            item.setTitle(R.string.appbar_done);
        }
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
                    if (photoLoaded && updatePlayerInfo()) {
                        item.setTitle(R.string.appbar_edit);
                        editEnabled = false;

                        Bundle bundle = new Bundle();
                        bundle.putString("player_select", "existing");
                        bundle.putBinder("player_info", new ObjectWrapperForBinder(playerInFocus));
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        ThreeButtonsFragment threeButtonsFragment = ThreeButtonsFragment.newInstance();
                        threeButtonsFragment.setArguments(bundle);
                        transaction.replace(R.id.three_buttons, threeButtonsFragment);
                        transaction.commit();

                    } else {
                        Toast toast = Toast.makeText(getActivity(), R.string.empty_field, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updatePlayerLoaded() {
        photoLoaded = true;
    }
    public void updateImageLoaded(byte[] image) {
        mImage = image;

    }

    void setInfoEdit() {

        final int[] DOBdata;

        cameraButton.setVisibility(View.VISIBLE);
        name.setEnabled(true);
        age.setEnabled(true);
        height.setEnabled(true);
        weight.setEnabled(true);
        DOB.setText(R.string.DOB);

        if (!player_select.equalsIgnoreCase("new")) {
            DOBdata = playerInFocus.getDOB();
            String DOBString = String.valueOf(DOBdata[0]) + "-" + String.valueOf(DOBdata[1]) + "-" + String.valueOf(DOBdata[2]);
            age.setText(DOBString);
        } else {
            DOBdata = new int[]{1991, 1, 1};
            age.setText(R.string.enter_DOB);
        }

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialogFragment = new DatePickerDialog(getActivity(), myDateListener,
                        DOBdata[0],
                        DOBdata[1],
                        DOBdata[2]);
                dialogFragment.show();
*//*                String DOBString = String.valueOf(DOBdata[0])+"/"+String.valueOf(DOBdata[1])+"/"+String.valueOf(DOBdata[2]);
                age.setText(DOBString);*//*
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                cameraButton.setVisibility(View.GONE);
                photoLoaded = false;
                Bundle bund = new Bundle();

                bund.putString("player_code", String.valueOf(playerInFocus.getCode_Player()));
                CameraFragment cameraFragment = CameraFragment.newInstance();
                cameraFragment.setArguments(bund);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, cameraFragment).commit();
            }
        });

    }

    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            // TODO: 2016/08/03 ADD ERROR CHECKING FOR DATE SET (EG. < CURRENT DATE)
            playerInFocus.DOB = Calendar.getInstance();
            playerInFocus.getDOB_player().set(arg1, arg2 + 1, arg3);
            if(arg2<10) {
                String DOBString = String.valueOf(arg1) + "-0" + String.valueOf(arg2) + "-" + String.valueOf(arg3);
                age.setText(DOBString);
            }else{
                String DOBString = String.valueOf(arg1) + "-" + String.valueOf(arg2) + "-" + String.valueOf(arg3);
                age.setText(DOBString);
            }
            age.setTextColor(R.color.textPrimary);
            //Log.e("date",String.valueOf( playerInFocus.DOB.get(Calendar.YEAR))+ String.valueOf( playerInFocus.DOB.get(Calendar.MONTH))+String.valueOf( playerInFocus.DOB.get(Calendar.DAY_OF_MONTH)));

        }
    };

    //update all view fields with players information
    public boolean updatePlayerInfo() {
        Boolean success = false;
     //   try {
            playerInFocus.Player_Name = name.getText().toString();
            playerInFocus.Player_Height = Double.parseDouble(height.getText().toString());
            playerInFocus.Player_Weight = Double.parseDouble(weight.getText().toString());
            if (player_select.equalsIgnoreCase("new")) {
                Log.e(TAG, "new");
                new insertPlayer().execute();
                //FileOutputStream outStream = null;
                // playerCodeList.add(playerInFocus.getCode_Player());


*//*                String folder_main = getActivity().getExternalFilesDir(null).toString();
                File fileObj = new File(folder_main, "Player_Library.pl");
                outStream = new FileOutputStream(fileObj);
                ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
                objectOutStream.writeObject(playerCodeList);
                objectOutStream.close();*//*


                player_select = "existing";

            }


            setInfoNoEdit(); //Set edittext views as non editable
            updateViews();
            editEnabled = false;

*//*            FileOutputStream outStream = null;


            String folder_main = getActivity().getExternalFilesDir(null).toString();
            File fileObj = new File(folder_main, "/" + String.valueOf(playerInFocus.getCode_Player()) + ".ply");
            outStream = new FileOutputStream(fileObj);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(playerInFocus);
            objectOutStream.close();*//*

            success = true;
       // } *//*catch () {
           // Log.e(TAG, "Could not write object to file");

        //}*//*
        return success;
    }

    public void setInfoNoEdit() {

        name.setEnabled(false);
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        cameraButton.setVisibility(View.GONE);
        DOB.setText(R.string.Age);
    }

    public void updateViews() {

        int[] DOBdata = playerInFocus.getDOB();//Retrieve players DOB and calculate age
        String ageString = calculateAge(DOBdata[0], DOBdata[1], DOBdata[2]);
        age.setText(ageString); //Update all textViews
        name.setText(playerInFocus.getPlayer_Name());
        weight.setText(String.valueOf(playerInFocus.getPlayer_Weight()));
        height.setText(String.valueOf(playerInFocus.getPlayer_Height()));
    }

    public int calculatePlayerCode(ArrayList<Integer> playerList) {
        Log.e(TAG, "Starting code search");
        int playerCode = 1;

        for (int i = 1; i <= playerList.size(); i++) {
            if (i != playerList.get(i - 1)) {
                playerCode = i;
                break;
            }
            if (i == playerList.size()) {
                playerCode = playerList.size() + 1;
            }
        }
        return playerCode;
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


    public static PlayerInfoFragment newInstance() {
        return new PlayerInfoFragment();
    }

    //---------------------------------------------
    //------------JSON ----------------------------

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
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Inserting Player");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            String DOBString;

            Log.e("JSonInsTeam", "Background");
            try {
                //    Log.d("JSON REQUEST", "Start ...");
                if(playerInFocus.DOB.get(Calendar.MONTH)<10){
                    DOBString = String.valueOf(playerInFocus.DOB.get(Calendar.YEAR)) +
                            "-0" + String.valueOf(playerInFocus.DOB.get(Calendar.MONTH)) +
                            "-" + String.valueOf(playerInFocus.DOB.get(Calendar.DAY_OF_MONTH));
                }else{
                        DOBString = String.valueOf(playerInFocus.DOB.get(Calendar.YEAR)) +
                                "-" + String.valueOf(playerInFocus.DOB.get(Calendar.MONTH)) +
                                "-" + String.valueOf(playerInFocus.DOB.get(Calendar.DAY_OF_MONTH));
                }

                // PREPARING PARAMETERS..

                Log.e("JSON REQUEST", "Preparing Params ...");
                HashMap<String, String> args = new HashMap<>();
               // Log.e("photoData", photoFragment.imageData.toString());
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
                args.put("Player_DateOfBirth", DOBString);
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

        }


    }
}
    //-------END JSON----------------
    //-------------------------------*/


