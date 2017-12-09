package com.example.joshf.conc;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
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
import java.lang.ref.WeakReference;
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
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
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
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
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
import android.view.GestureDetector;
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
import com.google.android.gms.common.server.converter.StringToIntConverter;
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

import static android.R.transition.move;


/**
 * Created by joshf on 2016/07/25.
 */
public class PlayerProfile extends AppCompatActivity implements PhotoFragment.CheckSelectedCallback, View.OnClickListener{

    String TAG = "PlayerProfile";

    EditText name;
    EditText emailText;
    TextView age;
    TextView DOB;
    EditText height;
    EditText weight;
    ImageButton cameraButton;
    ImageView brainStatus;
    TextView brainStatusText;
    View ageLine;
    Bitmap bitmap;

    private GestureDetectorCompat gestureDetector;
    View.OnTouchListener gestureListener;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private float dY;
    private float dX;

    ImageView photoBackground;
    ImageView slideBackground;

    public int team_code;

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
    public String player_select = "existing";
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


        databaseUpdate = false;

        setContentView(R.layout.player_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);



        // photoHolder = (FrameLayout) findViewById(R.id.playerPhoto);
        name = (EditText) findViewById(R.id.name);
        emailText = (EditText) findViewById(R.id.emailText);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        age = (TextView) findViewById(R.id.ageText);
        DOB = (TextView) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.heightText);
        weight = (EditText) findViewById(R.id.weightText);
        ageLine = findViewById(R.id.ageLine);

        photoBackground = (ImageView) findViewById(R.id.photoBackground);
       // slideBackground = (ImageView) findViewById(R.id.startTestHolder);


        startTest = (Button) findViewById(R.id.startTest);
        playerHistory = (Button) findViewById(R.id.playerHistory);

        playerPhoto = (ImageView) findViewById(R.id.playerPhotoLarge);
        brainStatus = (ImageView) findViewById(R.id.playerHealth);
        brainStatusText = (TextView) findViewById(R.id.playerHealthText);


        loadBitmap(R.drawable.photo_background, photoBackground);
        //loadBitmap(R.drawable.slide, slideBackground);


        startTest.setOnClickListener(this);
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
                    .showImageOnLoading(R.drawable.launcher)//display stub image until image is loaded
                    .displayer(new RoundedBitmapDisplayer(400))
                    .build();
            //---------------/IMG----
        } catch (Exception e) {
            e.printStackTrace();
        }

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraDialog = new CameraDialog();
                cameraDialog.show(getFragmentManager(), "dialog");
            }
        });


    }

    public void onClick(View view) {
/*        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in));*/
        Intent intent = new Intent(PlayerProfile.this, TestMenuActivity.class);
        intent.putExtra("player",playerInFocus);
        intent.putExtra("team_code", team_code);
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        startActivity(intent);

    }



    private void setInfoNoEdit() {
        startTest.setVisibility(View.VISIBLE);
        name.setEnabled(false);
        emailText.setEnabled(false);
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

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);

        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];

            return decodeSampledBitmapFromResource(getResources(), data, 200,150);

        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public void loadBitmap(int resId, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        //options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inSampleSize = 0;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        Bundle extras = getIntent().getExtras();
        player_select = extras.getString("player_select");
        Boolean testMenu = extras.getBoolean("parent");
        team_code = extras.getInt("team_code");
        databaseUpdate = extras.getBoolean("update_required");

        Log.e("Team_Code", String.valueOf(team_code));

        if (player_select.equalsIgnoreCase("existing")) {

            playerInFocus = ((Player) extras.getSerializable("player_info"));
            Log.e("team_code", String.valueOf(testMenu));
            if(testMenu)
                addTransitionListener();
            else
                loadImage(false);

            setInfoNoEdit();
            updateViews();

            photoLoaded = true;
            editEnabled = false;
            dateLoaded = true;
        } else {
            JSONObject jsonObject = null;
            playerInFocus = new Player(jsonObject);
            playerInFocus.Code_Team = team_code;
            playerPhoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unknown));

            setInfoEdit();

            photoLoaded = false;
            editEnabled = true;
        }
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
                brainStatusText.setText("Baseline Test Required");
                break;
            case(2):
                brainStatus.setImageResource(R.drawable.injured);
                brainStatusText.setText("Suspected Concussion");
                break;
            case(3):
                brainStatus.setImageResource(R.drawable.injured);
                brainStatusText.setText("Concussion Confirmed");
                break;
            default:
                brainStatus.setImageResource(R.drawable.injured);
                brainStatusText.setText("Rehabilitating");
        }
        age.setText(ageString); //Update all textViews
        name.setText(playerInFocus.getPlayer_Name());
        emailText.setText(playerInFocus.getPlayer_Email());

        DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");
        String weightStr = REAL_FORMATTER.format(playerInFocus.getPlayer_Weight());
        String heightStr = REAL_FORMATTER.format(playerInFocus.getPlayer_Height());
        weightStr = weightStr.replace(",",".");
        heightStr = heightStr.replace(",",".");
        weight.setText(weightStr);
        height.setText(heightStr);
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
                        databaseUpdate = true;
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
            case android.R.id.home:

                Intent intent = new Intent(PlayerProfile.this, PlayerSelect.class);
                intent.putExtra("database_update", databaseUpdate);
                intent.putExtra("team_code", playerInFocus.Code_Team);
                startActivity(intent);
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
        startTest.setVisibility(View.INVISIBLE);
        name.setEnabled(true);
        emailText.setEnabled(true);
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
            age.setTextColor(getResources().getColor(R.color.textPrimary));
            dateLoaded = true;
            // age.setTextColor(getResources().getColor(R.color.textPrimary));
        }
    };

    public boolean updatePlayerInfo() {
        Boolean success = false;

        if (dateLoaded.equals(true) && (name.getText().toString().length() > 0)
                && (photoLoaded.equals(true)) && (emailText.getText().toString().length() > 0))
            try {

                playerInFocus.Player_Name = name.getText().toString();
                playerInFocus.Player_Email = emailText.getText().toString();
                playerInFocus.Player_Height = Double.parseDouble(height.getText().toString());
                playerInFocus.Player_Weight = Double.parseDouble(weight.getText().toString());


                setInfoNoEdit(); //Set edittext views as non editable
                updateViews();

                if (player_select.equals("new")) {
                    new insertPlayer().execute();
                } else {
                    if (newPhotoLoaded) {
                        String photoPath = String.valueOf(playerInFocus.getCode_Player());
                        String path1 = "https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath +"THUMB.png";
                        String path2 = "https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath +"IMG.png";
                        MemoryCacheUtils.removeFromCache(path1, ImageLoader.getInstance().getMemoryCache());
                        MemoryCacheUtils.removeFromCache(path2, ImageLoader.getInstance().getMemoryCache());

                        DiskCacheUtils.removeFromCache(path1, ImageLoader.getInstance().getDiskCache());
                        DiskCacheUtils.removeFromCache(path2, ImageLoader.getInstance().getDiskCache());
                    }
                    new updatePlayer().execute();
                }


            success = true;
        } catch (Exception e) {
            Log.e(TAG, "All fields not completed");
        }

        return success;
    }

    private void loadImage(Boolean large) {


        String photoPath = String.valueOf(playerInFocus.getCode_Player());
        try {
            if (!large) {
               // imageLoader.displayImage("https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath + "THUMB.png", playerPhoto, options);
               // bitmap = imageLoader.loadImageSync("https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath + "IMG.png", options);
                imageLoader.displayImage("https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath + "IMG.png", playerPhoto, options);

            } else {
               // imageLoader.displayImage("https://www.concussionassessment.net/ConcApp/Player_Image/" + photoPath + "IMG.png", playerPhoto, options);
               // playerPhoto.setImageBitmap(bitmap);

            }

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

        private static final String URL = "https://www.concussionassessment.net/ConcApp/insertPlayer.php"; // Needs to be changed when using different php files.
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

                Bitmap scaledBitmapLarge = Bitmap.createScaledBitmap(rotatedBitmap, 250, 250, false);
                Bitmap scaledBitmapThumb = Bitmap.createScaledBitmap(rotatedBitmap, 100, 100, false);

                ByteArrayOutputStream byteArrayOutputStreamLarge = new ByteArrayOutputStream();
                scaledBitmapLarge.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamLarge);
                String encodedImageLarge = Base64.encodeToString(byteArrayOutputStreamLarge.toByteArray(), Base64.DEFAULT);

                ByteArrayOutputStream byteArrayOutputStreamSmall = new ByteArrayOutputStream();
                scaledBitmapThumb.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStreamSmall);
                String encodedImageSmall = Base64.encodeToString(byteArrayOutputStreamSmall.toByteArray(), Base64.DEFAULT);


                Log.e("JSON REQUEST", "Image Done");
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));
                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));

                args.put("Player_Name", String.valueOf(playerInFocus.getPlayer_Name()));
                args.put("Player_Email", String.valueOf(playerInFocus.getPlayer_Email()));
                args.put("Player_Height", String.valueOf(playerInFocus.getPlayer_Height()));
                args.put("Player_Weight", String.valueOf(playerInFocus.getPlayer_Weight()));
                args.put("Player_Photo", encodedImageLarge);
                args.put("Player_Thumb", encodedImageSmall);
                args.put("Player_Surname", "Fischer");
                args.put("Player_DateOfBirth", playerInFocus.getDOB_player());
                args.put("Code_Team",String.valueOf(team_code));
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

        private static final String URL = "https://www.concussionassessment.net/ConcApp/updatePlayer.php";
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

         //   Log.e("JSonInsPlayer", "Background");
            try {
                // PREPARING PARAMETERS..

              //  Log.e("JSON REQUEST", "Preparing Params2 ...");
                HashMap<String, String> args = new HashMap<>();

                if (newPhotoLoaded) {

                    Bitmap myBitmap = BitmapFactory.decodeByteArray(mImage, 0, mImage.length);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);

                    Bitmap scaledBitmapLarge = Bitmap.createScaledBitmap(rotatedBitmap, 250, 250, false);
                    Bitmap scaledBitmapThumb = Bitmap.createScaledBitmap(rotatedBitmap, 100, 100, false);

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

                args.put("Code_UserDoctor", session.getUserDetails().get(SessionManager.KEY_CODEUSERDOCTOR));
                args.put("SecToken", session.getUserDetails().get(SessionManager.KEY_TOKEN));

                args.put("Code_Player", String.valueOf(playerInFocus.getCode_Player()));
                args.put("Player_Name", String.valueOf(playerInFocus.getPlayer_Name()));
                args.put("Player_Email", playerInFocus.getPlayer_Email());
                args.put("Player_Surname", "Fischer");
                args.put("Player_Height", String.valueOf(playerInFocus.getPlayer_Height()));
                args.put("Player_Weight", String.valueOf(playerInFocus.getPlayer_Weight()));
                args.put("Player_DateOfBirth", playerInFocus.getDOB_player());
                args.put("Code_Team", String.valueOf(team_code));

                args.put("Player_Lenses", "1");
                // all args needs to convert to string because the hash map is string, string types.

                //   Log.d("JSON REQUEST", args.toString());
               // Log.e("JSON REQUEST", "Firing Json ...");
                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);
                Log.e("json", "0bject = " + json);

                if (json != null) {
                //    Log.e("I got", "in here?");
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
         //   Log.e("JSonInsTeam", "Finish");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(PlayerProfile.this, PlayerSelect.class);

        intent.putExtra("database_update", databaseUpdate);
        intent.putExtra("team_code",playerInFocus.Code_Team);
        startActivity(intent);
       // finish();

    }

}

