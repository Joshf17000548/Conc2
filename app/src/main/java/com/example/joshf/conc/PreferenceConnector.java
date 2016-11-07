package com.example.joshf.conc;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.icu.text.SimpleDateFormat;
import android.text.format.Time;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

public class PreferenceConnector{

    public static double [] time_stamp;
    public static double [] event_stamp;
    public static double [] status_stamp;
    public static double [] acc_values0;
    public static double [] acc_values1;
    public static double [] acc_values2;
    public static int acc_cnt;

    public static double [] rot_values0;
    public static double [] rot_values1;
    public static double [] rot_values2;
    public static int rot_cnt;

    public static double [] bal_time_stamp;
    public static double [] bal_event_stamp;
    public static double [] bal_status_stamp;
    public static double [] bal_acc_values0;
    public static double [] bal_acc_values1;
    public static double [] bal_acc_values2;

    public static double [] bal_rot_values0;
    public static double [] bal_rot_values1;
    public static double [] bal_rot_values2;

    public static int max_cnt;

    public static  String PREF_NAME = "PEOPLE_PREFERENCES";
    public static  int MODE = Context.MODE_PRIVATE;

    public static int assessment_type = 0;
    public static int test_type = 0;


    public static Boolean gait_test_completed = false;
    public static int test_status = 1;
    public static float tandem_t1 = 0;
    public static float tandem_t2 = 0;
    public static float tandem_t3 = 0;
    public static float tandem_t4 = 0;
    public static float tandem_t1_MLRMS = 0;
    public static float tandem_t1_APRMS = 0;
    public static float tandem_t2_MLRMS = 0;
    public static float tandem_t2_APRMS = 0;
    public static float tandem_t3_MLRMS = 0;
    public static float tandem_t3_APRMS = 0;
    public static float tandem_t4_MLRMS = 0;
    public static float tandem_t4_APRMS = 0;

    public static Boolean balance_test_completed = false;
    public static int balance_dl = 0;
    public static int balance_sl = 0;
    public static int balance_ts = 0;
    public static int balance_status = 0;//"Not fit";

    public static float balance_dl_MLRMS = 0;
    public static float balance_dl_APRMS = 0;
    public static float balance_sl_MLRMS = 0;
    public static float balance_sl_APRMS = 0;
    public static float balance_ts_MLRMS = 0;
    public static float balance_ts_APRMS = 0;

    public static float balance_dl_PTP = 0;
    public static float balance_sl_PTP = 0;
    public static float balance_ts_PTP = 0;


    public static int upper_score = 0;
//	public static  String SERVERIP = "192.168.119.101";
//	public static  String SERVERPORT = "8080";


/////////////////////

//	public static  String SERVERIP = "192.168.119.101";
//	public static  String SERVERPORT = "8080";


/////////////////////


//	public static  String SERVERIP = "192.168.119.101";
//	public static  String SERVERPORT = "8080";


    ////////////////////

//	public static  String SERVERIP = "192.168.119.101";
//	public static  String SERVERPORT = "8080";


    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString (Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void clear_all_values(){

        gait_test_completed=false;
        test_status=1;
        tandem_t1 = 0;
        tandem_t2 = 0;
        tandem_t3 = 0;
        tandem_t4 = 0;
        tandem_t1_MLRMS = 0;
        tandem_t1_APRMS = 0;
        tandem_t2_MLRMS = 0;
        tandem_t2_APRMS = 0;
        tandem_t3_MLRMS = 0;
        tandem_t3_APRMS = 0;
        tandem_t4_MLRMS = 0;
        tandem_t4_APRMS = 0;


        balance_dl = 0;
        balance_sl = 0;
        balance_ts = 0;
        balance_status = 0;//"Not fit";

        balance_dl_MLRMS = 0;
        balance_dl_APRMS = 0;
        balance_sl_MLRMS = 0;
        balance_sl_APRMS = 0;
        balance_ts_MLRMS = 0;
        balance_ts_APRMS = 0;

        balance_dl_PTP = 0;
        balance_sl_PTP = 0;
        balance_ts_PTP = 0;

        upper_score = 0;

        //////////////////////////


    }



    public void save_in_file(){

			/*
		Context context = App.instance.getApplicationContext();
		try {
			FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
			out.write(fileContents);
			out.close();
		} catch (IOException e) {
			Logger.logError(TAG, e);
		}
*/

//        String filename = "";

    }

    public static void writedata (String filename, double[]x) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < x.length; i++) {
            // Maybe:
            //  outputWriter.write(x[i]+"");
            // Or:
            outputWriter.write(Double.toString(x[i]));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }


}