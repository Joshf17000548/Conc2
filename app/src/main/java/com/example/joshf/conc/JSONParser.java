package com.example.joshf.conc;

/*
 *Taken from http://stackoverflow.com/questions/31345614/how-to-implement-login-with-httpurlconnection-and-php-server-in-android
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class JSONParser {

    String charset = "UTF-8";
    HttpURLConnection conn;
    DataOutputStream wr;
    StringBuilder result;
    URL urlObj;
    JSONArray jObj = null; //Not used..
    StringBuilder sbParams;
    String paramsString;
    String answer="";
    public JSONArray makeHttpRequest(String url, String method,
                                     HashMap<String, String> params) {
     //   Log.e("Parser","Parse start ");
        sbParams = new StringBuilder();

        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
               // Log.e("Parser", key);
               /// Log.e("Parser", key + " " +params.get(key));
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

                conn = (HttpsURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Accept-Charset", charset);

               // conn.setReadTimeout(10000);
              //  conn.setConnectTimeout(15000);

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
            // request method is
            Log.e("GET", "start");

            if (sbParams.length() != 0) {
                url += "?" + sbParams.toString();
            }

            try {
                urlObj = new URL(url);

                conn = (HttpsURLConnection) urlObj.openConnection();

                conn.setDoOutput(false);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Accept-Charset", charset);

                conn.setConnectTimeout(15000);

                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GET", "error");
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

            Log.e("JSON Parser", "result: " + result.toString());

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