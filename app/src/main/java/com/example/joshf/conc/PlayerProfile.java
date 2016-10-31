package com.example.joshf.conc;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
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
import java.util.concurrent.RunnableFuture;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.transition.Transition;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


/**
 * Created by joshf on 2016/07/25.
 */
public class PlayerProfile extends AppCompatActivity implements PhotoFragment.CheckSelectedCallback{

    String TAG = "PlayerProfile";

    EditText name;
    TextView age;
    TextView DOB;
    EditText height;
    EditText weight;
    ImageButton cameraButton;
    ImageView brainStatus;
    TextView brainStatusText;
    View ageLine;

    Button startTest;
    Button playerHistory;
    Boolean databaseUpdate;
    Boolean photoLoaded;
    Boolean newPhotoLoaded = false;
    Boolean editEnabled;
    Boolean dateLoaded = false;
    byte[] mImage;
    SessionManager session;

    DisplayImageOptions options;
    ImageLoader imageLoader;
    ImageView playerPhoto;

    public Toolbar toolbar;
    public String player_select;
    private static Player playerInFocus;

    private CameraDialog cameraDialog;

    public void onCheckSelected(byte[] bit) {
        photoLoaded = true;
        newPhotoLoaded = true;
        cameraDialog.dismiss();
        mImage = bit;

        Bitmap myBitmap = BitmapFactory.decodeByteArray(mImage, 0, mImage.length);
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(rotatedBitmap, 800, 800, false);

        final Bitmap output = Bitmap.createBitmap(scaledBitmap.getWidth(),
                scaledBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, rect, rect, paint);

        scaledBitmap.recycle();

        playerPhoto.setImageBitmap(output);
    }


    public PlayerProfile() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(this);
        session.checkLogin();

        databaseUpdate = false;

        setContentView(R.layout.player_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);


        // photoHolder = (FrameLayout) findViewById(R.id.playerPhoto);
        name = (EditText) findViewById(R.id.name);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        age = (TextView) findViewById(R.id.ageText);
        DOB = (TextView) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.heightText);
        weight = (EditText) findViewById(R.id.weightText);
        ageLine = (View) findViewById(R.id.ageLine);


        startTest = (Button) findViewById(R.id.startTest);
        playerHistory = (Button) findViewById(R.id.playerHistory);

        playerPhoto = (ImageView) findViewById(R.id.playerPhotoLarge);
        brainStatus = (ImageView) findViewById(R.id.playerHealth);
        brainStatusText = (TextView) findViewById(R.id.playerHealthText);



        //age.setFactory(mFactory);
        // DOB.setFactory(mFactory);


/*        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        DOB.setInAnimation(in);
        DOB.setOutAnimation(out);
        age.setInAnimation(in);
        age.setOutAnimation(out);*/

        Bundle extras = getIntent().getExtras();

        player_select = extras.getString("player_select");
        //New or existing player?

        try {
            // Get singletone instance of ImageLoader
            imageLoader = ImageLoader.getInstance();
            // Create configuration for ImageLoader (all options are optional)
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(PlayerProfile.this).build();
            // Initialize ImageLoader with created configuration. Do it once.
            imageLoader.init(config);
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .showImageOnLoading(R.mipmap.ic_launcher)//display stub image until image is loaded
                    .displayer(new RoundedBitmapDisplayer(400))
                    .build();
            //---------------/IMG----
        } catch (Exception e) {
            e.printStackTrace();
        }




        if (player_select.equalsIgnoreCase("existing")) {
            addTransitionListener();
            playerInFocus = ((Player) getIntent().getExtras().getSerializable("player_info"));

            setInfoNoEdit();
            updateViews();

            photoLoaded = true;
            editEnabled = false;
            dateLoaded = true;
        } else {
            JSONObject jsonObject = null;
            playerInFocus = new Player(jsonObject);
            playerInFocus.Code_Team=extras.getInt("team");
            playerPhoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unknown));

            setInfoEdit();

            photoLoaded = false;
            editEnabled = true;
        }

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

                cameraDialog = new CameraDialog();
                cameraDialog.show(getFragmentManager(), "dialog");

            }
        });
    }




/*    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(PlayerProfile.this);
            //t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(PlayerProfile.this, android.R.style.TextAppearance);
            return t;
        }
    };*/


    private void setInfoNoEdit() {
        name.setEnabled(false);
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        cameraButton.setVisibility(View.INVISIBLE);
        DOB.setText(getResources().getText(R.string.Age));
        age.setTextColor(getResources().getColor(R.color.textPrimary));
        ageLine.setBackgroundColor(getResources().getColor(R.color.lineNoEdit));

    }

    private boolean addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    // As the transition has ended, we can now load the full-size image

                    loadImage(true);
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                    loadImage(false);
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    // No-op
                }
            });
            return true;
        }

        // If we reach here then we have not added a listener
        return false;
    }


    public void updateViews() {
        Integer[] DOBdata = playerInFocus.getDOBInt();//Retrieve players DOB and calculate age
        String ageString = calculateAge(DOBdata[0], DOBdata[1], DOBdata[2]);
        int status = playerInFocus.getPlayer_Status();

        switch (status){
            case(0):
                brainStatus.setImageResource(R.drawable.healthy);
                brainStatusText.setText("Player is Healthy");
                break;
            case(1):
                brainStatus.setImageResource(R.drawable.baseline);
                brainStatusText.setText("Baseline Due");
                break;
            default:
                String test = String.valueOf(status);
                brainStatus.setImageResource(R.drawable.injured);
                brainStatusText.setText("HIA"+test+" Due");
        }
        age.setText(ageString); //Update all textViews
        name.setText(playerInFocus.getPlayer_Name());
        weight.setText(String.valueOf(playerInFocus.getPlayer_Weight()));
        height.setText(String.valueOf(playerInFocus.getPlayer_Height()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.playerprofile_menu, menu);
        MenuItem item = menu.findItem(R.id.action_edit);
        ActionBar actionBar = getSupportActionBar();
        actionBar .setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
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
            case R.id.menuId_logout:
                session.logoutUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setInfoEdit() {

        final Integer[] DOBdata;

        cameraButton.post(new Runnable() {
            public void run() {
                showCameraButton();
            }

        });
        name.setEnabled(true);

        age.setEnabled(true);
        height.setEnabled(true);
        weight.setEnabled(true);
        DOB.setText(getResources().getText(R.string.DOB));

        //age.setTextColor(getResources().getColor(R.color.textPrimary));
        ageLine.setBackgroundColor(getResources().getColor(R.color.textPrimary));

        if (!player_select.equalsIgnoreCase("new")) {
            age.setText(playerInFocus.getDOB_player().toString());
            DOBdata = playerInFocus.getDOBInt();
        } else {
            DOBdata = new Integer[]{1991, 1, 1};
            age.setText(getResources().getText(R.string.enter_DOB));
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

    private void showCameraButton() {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                cameraButton,
                0,
                0,
                0,
                (float) Math.hypot(cameraButton.getWidth(), cameraButton.getHeight()));

        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Finally start the animation
        cameraButton.setVisibility(View.VISIBLE);
        animator.setDuration(500);
        animator.start();

    }


    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            // TODO: 2016/08/03 ADD ERROR CHECKING FOR DATE SET (EG. < CURRENT DATE)
            if (arg2 < 10) {
                playerInFocus.Player_DateOfBirth = String.valueOf(arg1) + "-0" + String.valueOf(arg2);

            } else {
                playerInFocus.Player_DateOfBirth = String.valueOf(arg1) + "-" + String.valueOf(arg2);
            }
            if (arg3 < 10) {
                playerInFocus.Player_DateOfBirth = playerInFocus.Player_DateOfBirth + "-0" + String.valueOf(arg3);

            } else {
                playerInFocus.Player_DateOfBirth = playerInFocus.Player_DateOfBirth + "-" + String.valueOf(arg3);
            }

            age.setText(playerInFocus.Player_DateOfBirth);
            dateLoaded = true;
            // age.setTextColor(getResources().getColor(R.color.textPrimary));
        }
    };

    public boolean updatePlayerInfo() {
        Boolean success = false;

        if (dateLoaded.equals(true) && (name.getText().toString().length() > 0)
                && (photoLoaded.equals(true))) try {
            playerInFocus.Player_Name = name.getText().toString();

            playerInFocus.Player_Height = Double.parseDouble(height.getText().toString());
            playerInFocus.Player_Weight = Double.parseDouble(weight.getText().toString());

            Log.e(TAG, String.valueOf(playerInFocus.getPlayer_Weight()));
            setInfoNoEdit(); //Set edittext views as non editable
            updateViews();

            if (player_select.equals("new")) {
                new insertPlayer().execute();
            } else {
                if (newPhotoLoaded) {
                    String photoPath = String.valueOf(playerInFocus.getCode_Player());
                    String path1 = "http://104.198.254.110/ConcApp/Player_Image/" + photoPath +"THUMB.png";
                    String path2 = "http://104.198.254.110/ConcApp/Player_Image/" + photoPath +"IMG.png";
                    MemoryCacheUtils.removeFromCache(path1, ImageLoader.getInstance().getMemoryCache());
                    MemoryCacheUtils.removeFromCache(path2, ImageLoader.getInstance().getMemoryCache());

                    DiskCacheUtils.removeFromCache(path1, ImageLoader.getInstance().getDiskCache());
                    DiskCacheUtils.removeFromCache(path2, ImageLoader.getInstance().getDiskCache());

                }
                new updatePlayer().execute();
            }

            databaseUpdate = true;

            success = true;
        } catch (Exception e) {
            Log.e(TAG, "All fields not completed");
        }

        return success;
    }

    private void loadImage(Boolean large) {


        String photoPath = String.valueOf(playerInFocus.getCode_Player());
        try {
            Log.e("loadImage", String.valueOf(large));
            if (!large) {
                imageLoader.displayImage("http://104.198.254.110/ConcApp/Player_Image/" + photoPath + "THUMB.png", playerPhoto, options);
            } else
                imageLoader.displayImage("http://104.198.254.110/ConcApp/Player_Image/" + photoPath + "IMG.png", playerPhoto, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
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

                Bitmap scaledBitmapLarge = Bitmap.createScaledBitmap(rotatedBitmap, 300, 300, false);
                Bitmap scaledBitmapThumb = Bitmap.createScaledBitmap(rotatedBitmap, 150, 150, false);

                ByteArrayOutputStream byteArrayOutputStreamLarge = new ByteArrayOutputStream();
                scaledBitmapLarge.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamLarge);
                String encodedImageLarge = Base64.encodeToString(byteArrayOutputStreamLarge.toByteArray(), Base64.DEFAULT);

                ByteArrayOutputStream byteArrayOutputStreamSmall = new ByteArrayOutputStream();
                scaledBitmapThumb.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamSmall);
                String encodedImageSmall = Base64.encodeToString(byteArrayOutputStreamSmall.toByteArray(), Base64.DEFAULT);


                Log.e("JSON REQUEST", "Image Done");

                args.put("Player_Name", String.valueOf(playerInFocus.getPlayer_Name()));
                args.put("Player_Height", String.valueOf(playerInFocus.getPlayer_Height()));
                args.put("Player_Weight", String.valueOf(playerInFocus.getPlayer_Weight()));
                args.put("Player_Photo", encodedImageLarge);
                args.put("Player_Thumb", encodedImageSmall);
                args.put("Player_Surname", "Fischer");
                args.put("Player_DateOfBirth", playerInFocus.getDOB_player());
                args.put("Code_Team", "3");
                //args.put("Player_Status", "1");
                args.put("Player_Lenses", "1");

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
            player_select = "existing";

        }
    }

    private class updatePlayer extends AsyncTask<Void, Void, JSONArray> {

        // Alert Dialog Manager

        private static final String URL = "http://104.198.254.110/ConcApp/updatePlayer.php";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(PlayerProfile.this);
            pDialog.setMessage("Updating Player");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(Void... params) {

            Log.e("JSonInsPlayer", "Background");
            try {
                // PREPARING PARAMETERS..

                Log.e("JSON REQUEST", "Preparing Params2 ...");
                HashMap<String, String> args = new HashMap<>();

                if (newPhotoLoaded) {

                    Bitmap myBitmap = BitmapFactory.decodeByteArray(mImage, 0, mImage.length);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);

                    Bitmap scaledBitmapLarge = Bitmap.createScaledBitmap(rotatedBitmap, 300, 300, false);
                    Bitmap scaledBitmapThumb = Bitmap.createScaledBitmap(rotatedBitmap, 150, 150, false);

                    ByteArrayOutputStream byteArrayOutputStreamLarge = new ByteArrayOutputStream();
                    scaledBitmapLarge.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamLarge);
                    String encodedImageLarge = Base64.encodeToString(byteArrayOutputStreamLarge.toByteArray(), Base64.DEFAULT);

                    ByteArrayOutputStream byteArrayOutputStreamSmall = new ByteArrayOutputStream();
                    scaledBitmapThumb.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamSmall);
                    String encodedImageSmall = Base64.encodeToString(byteArrayOutputStreamSmall.toByteArray(), Base64.DEFAULT);

                    args.put("Player_Photo", encodedImageLarge);
                    args.put("Player_Thumb", encodedImageSmall);
                    args.put("UpdatePhoto", String.valueOf(1));
                } else
                    args.put("UpdatePhoto", String.valueOf(0));



                Log.e("JSON REQUEST", "Image Done");


                args.put("Code_Player", String.valueOf(playerInFocus.getCode_Player()));
                args.put("Player_Name", String.valueOf(playerInFocus.getPlayer_Name()));
                args.put("Player_Surname", "Fischer");
                args.put("Player_Height", String.valueOf(playerInFocus.getPlayer_Height()));
                args.put("Player_Weight", String.valueOf(playerInFocus.getPlayer_Weight()));
                args.put("Player_DateOfBirth", playerInFocus.getDOB_player());
                args.put("Code_Team", "3");

                args.put("Player_Lenses", "1");
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
            newPhotoLoaded = false;
            player_select = "existing";

        }
    }

    public void onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("database_update", databaseUpdate);
        bundle.putInt("team", playerInFocus.Code_Team);
        startActivity(new Intent(PlayerProfile.this, PlayerSelect.class).putExtras(bundle));
        finish();
    }

}

//TODO Deal with back button better. Before and after taking a photo. Maybe add a home button
//TODO Rather than creating a new file with each entree, just append to a single object file
