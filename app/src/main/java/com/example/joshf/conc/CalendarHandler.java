package com.example.joshf.conc;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by joshf on 2016/10/26.
 */

public class CalendarHandler {

    private static final int REQUEST_CALENDAR_PERMISSION = 1;
    private static final String CALENDAR_DIALOG = "calendar";
    private static final String TAG = "Calendar Handler";

    public void getCalendarHandler(Context context) {

        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016,9, 5, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, 9, 5, 8, 45);
        endMillis = endTime.getTimeInMillis();

        // Insert Event
        try {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            TimeZone timeZone = TimeZone.getDefault();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
            values.put(CalendarContract.Events.TITLE, "Walk The Dog");
            values.put(CalendarContract.Events.DESCRIPTION, "My dog is bored, so we're going on a really long walk!");
            values.put(CalendarContract.Events.CALENDAR_ID, 3);
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);




// Retrieve ID for new event
            String eventID = uri.getLastPathSegment();
        }catch (SecurityException e){
            Log.e(TAG,"Security");
        }catch (NullPointerException f){
            Log.e(TAG,"nullpoint");
        }

/*        String[] projection =
                new String[]{
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.NAME,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.ACCOUNT_TYPE};
        try {
            Cursor calCursor =
                    context.getContentResolver().
                            query(CalendarContract.Calendars.CONTENT_URI,
                                    projection,
                                    CalendarContract.Calendars.VISIBLE + " = 1",
                                    null,
                                    CalendarContract.Calendars._ID + " ASC");

            if (calCursor.moveToFirst()) {
                do {
                    long id = calCursor.getLong(0);
                    String displayName = calCursor.getString(1);
                    // …
                } while (calCursor.moveToNext());
            }
        }catch (SecurityException e){
            Log.e(TAG,"Security");
    }*/
}


 /*   ContentValues values = new ContentValues();
    values.put(
    CalendarContract.Calendars.ACCOUNT_NAME,
            "JOSHUA");
    values.put(
    CalendarContract.Calendars.ACCOUNT_TYPE,
    CalendarContract.ACCOUNT_TYPE_LOCAL);
    values.put(
    CalendarContract.Calendars.NAME,
            "GrokkingAndroid Calendar");
    values.put(
    CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            "GrokkingAndroid Calendar");
    values.put(
    CalendarContract.Calendars.CALENDAR_COLOR,
            0xffff0000);
    values.put(
    CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
    CalendarContract.Calendars.CAL_ACCESS_OWNER);
    values.put(
    CalendarContract.Calendars.OWNER_ACCOUNT,
            "some.account@googlemail.com");
    values.put(
    CalendarContract.Calendars.CALENDAR_TIME_ZONE,
            "Europe/Berlin");
    values.put(
    CalendarContract.Calendars.SYNC_EVENTS,
            1);
    Uri.Builder builder =
            CalendarContract.Calendars.CONTENT_URI.buildUpon();
    builder.appendQueryParameter(
    CalendarContract.Calendars.ACCOUNT_NAME,
            "com.grokkingandroid");
    builder.appendQueryParameter(
    CalendarContract.Calendars.ACCOUNT_TYPE,
    CalendarContract.ACCOUNT_TYPE_LOCAL);
    builder.appendQueryParameter(
    CalendarContract.CALLER_IS_SYNCADAPTER,
            "true");
    Uri uri =
            getContentResolver().insert(builder.build(), values);

    try {

        long calId = getCalendarId();
        if (calId == -1) {
            // no calendar account; react meaningfully
            return;
        }
        GregorianCalendar cal = new GregorianCalendar(2012, 11, 14);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long start = cal.getTimeInMillis();
        ContentValues values1 = new ContentValues();
        values1.put(CalendarContract.Events.DTSTART, start);
        values1.put(CalendarContract.Events.DTEND, start);
        values1.put(CalendarContract.Events.RRULE,
                "FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST=MO");
        values1.put(CalendarContract.Events.TITLE, "Some title");
        values1.put(CalendarContract.Events.EVENT_LOCATION, "Münster");
        values1.put(CalendarContract.Events.CALENDAR_ID, calId);
        values1.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Berlin");
        values1.put(CalendarContract.Events.DESCRIPTION,
                "The agenda or some description of the event");
// reasonable defaults exist:
        values1.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        values1.put(CalendarContract.Events.SELF_ATTENDEE_STATUS,
                CalendarContract.Events.STATUS_CONFIRMED);
        values1.put(CalendarContract.Events.ALL_DAY, 1);
        values1.put(CalendarContract.Events.ORGANIZER, "some.mail@some.address.com");
        values1.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, 1);
        values1.put(CalendarContract.Events.GUESTS_CAN_MODIFY, 1);
        values1.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        Uri uri1 =
                getContentResolver().
                        insert(CalendarContract.Events.CONTENT_URI, values);
        long eventId = new Long(uri1.getLastPathSegment());
    }catch (SecurityException e){
        Log.e(TAG, "Security");
    }
    private long getCalendarId() {

        try {
            String[] projection = new String[]{CalendarContract.Calendars._ID};
            String selection =
                    CalendarContract.Calendars.ACCOUNT_NAME +
                            " = ? AND " +
                            CalendarContract.Calendars.ACCOUNT_TYPE +
                            " = ? ";
            // use the same values as above:
            String[] selArgs =
                    new String[]{
                            "JOSHUA",
                            CalendarContract.ACCOUNT_TYPE_LOCAL};
            Cursor cursor =
                    getContentResolver().
                            query(
                                    CalendarContract.Calendars.CONTENT_URI,
                                    projection,
                                    selection,
                                    selArgs,
                                    null);
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
        } catch (SecurityException e) {

        }
        return -1;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALENDAR_PERMISSION) {
            if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                CameraFragment.ErrorDialog.newInstance(getString(R.string.request_permission))
                        .show(getChildFragmentManager(), CALENDAR_DIALOG);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public static class ErrorDialog extends DialogFragment {

        private static final String ARG_MESSAGE = "message";

        public static CameraFragment.ErrorDialog newInstance(String message) {
            CameraFragment.ErrorDialog dialog = new CameraFragment.ErrorDialog();
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
                            activity.finish();

                        }
                    })
                    .create();
        }

    }

    *//**
     * Shows OK/Cancel confirmation dialog about camera permission.
     *//*
    public static class ConfirmationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Fragment parent = getParentFragment();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.request_permission)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentCompat.requestPermissions(parent,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA_PERMISSION);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Activity activity = parent.getActivity();
                                    if (activity != null) {
                                        activity.finish();
                                    }
                                }
                            })
                    .create();
        }
    }*/


}
