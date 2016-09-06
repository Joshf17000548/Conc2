/*

package com.example.joshf.conc;


*/
/**
 * Created by joshf on 2016/07/25.
 *//*


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.joshf.conc.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class CommunicateWithServer{

    public String data;

    public CommunicateWithServer(String dataType, ){
        switch (dataType){
            case "getPlayers": data = dataType;
            case "getTeams": data = dataType;
            case "insertPlayer": data = dataType;
            case "insertTeam": data = dataType;
        }
    }

    private class DataAsync extends AsyncTask<String, String, JSONArray> {

        final String URL =
                "http://10.0.2.2/ConcApp/" + data;

        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                // PREPARING PARAMETERS..
                HashMap<String, String> args = new HashMap<>();
                args.put("Label_Param1", "param1");
                args.put("Label_Param2", "param2");

                Log.d("request", "starting");

                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);

                if (json != null) {
                    Log.d("JSON REQUEST", params.toString());
                    Log.d("JSON result", json.toString());
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
            if (jsonArray != null) {
                ArrayList<Player> arrayOfTeams = Team.fromJson(jsonArray);
                adapte*/
/**//*
r.clear();
                adapter.addAll(arrayOfTeams);
            }
        }
    }

    public JSONArray makeHttpRequest(String url, String method,
                                     HashMap<String, String> params) {

        String charset = "UTF-8";
        HttpURLConnection conn;
        DataOutputStream wr;
        StringBuilder result;
        URL urlObj;
        JSONArray jObj = null; //Not used..
        StringBuilder sbParams;
        String paramsString;
        String answer="";

        sbParams = new StringBuilder();

        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), charset));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        if (method.equals("POST")) {
            // request method is POST
            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Accept-Charset", charset);

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                paramsString = sbParams.toString();

                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("GET")){
            // request method is GET

            if (sbParams.length() != 0) {
                url += "?" + sbParams.toString();
            }

            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(false);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Accept-Charset", charset);

                conn.setConnectTimeout(15000);

                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            //Receive the response from the server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("JSON Parser", "result: " + result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.disconnect();

        // try parse the string to a JSON object
        try {
            jObj = new JSONArray(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString() +result);
        }

        // return JSON Object
        return jObj;
    }

}


*/
