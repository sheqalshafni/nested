package com.example.nested;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nested.mainmenuactivities.ListingQuickLink;
import com.example.nested.mainmenuactivities.PaymentHistoryActivity;
import com.example.nested.mainmenuactivities.RentalActivity;
import com.example.nested.profileactivities.ProfileActivity;
import com.example.nested.profileactivities.accountactivities.MyListings;
import com.example.nested.searchactivities.FindRoomActivity;
import com.example.nested.searchactivities.FindRoommateActivity;
import com.example.nested.searchactivities.FindUnitActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.btnListing)
    ImageView btnListing;
    @BindView(R.id.btnRental)
    ImageView btnRental;
    @BindView(R.id.btnPaymentRecords)
    ImageView btnPaymentRecords;
    @BindView(R.id.imgUser)
    ImageView btnProfile;
    @BindView(R.id.imgRoom)
    RoundedImageView imgRoom;
    @BindView(R.id.imgRoommate)
    RoundedImageView imgRoommate;
    @BindView(R.id.imgUnit)
    RoundedImageView imgUnit;
    @BindView(R.id.txtGreetings)
    TextView txtGreetings;
    @BindView(R.id.txtCurrentTime)
    TextView txtCurrentTime;
    @BindView(R.id.btnRoomList)
    View btnRoom;
    @BindView(R.id.btnRoommateList)
    View btnRoommate;
    @BindView(R.id.btnUnitList)
    View btnUnit;
    @BindView(R.id.parent_layout)
    ScrollView parentLayout;

    private String greeting, currentTime;

    FirebaseStorage mStorage;
    StorageReference mStorageReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        fullscreen();
        init();
        openIntent();
        currentTime();

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //set the home screen view to fullscreen base on android version
    public void fullscreen (){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(HomeActivity.this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(HomeActivity.this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //initialize firebase and load image from firestore
    private void init(){
        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference("HomeImg/room.jpg");
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        String roomURL = "https://firebasestorage.googleapis.com/v0/b/nested-1c965.appspot.com/o/HomeImg%2Froom.jpg?alt=media&token=411f72fc-e893-470c-a34a-62ec8215c2f7";
        String roommateURL = "https://firebasestorage.googleapis.com/v0/b/nested-1c965.appspot.com/o/HomeImg%2Froommate.jpg?alt=media&token=eb987c8f-55f2-402d-b1dc-ffb2a906be88";
        String unitURL = "https://firebasestorage.googleapis.com/v0/b/nested-1c965.appspot.com/o/HomeImg%2Funit.jpg?alt=media&token=e887dd6e-e1d2-445a-86d5-7ce603690742";
        Glide.with(getApplicationContext())
                .load(roommateURL)
                .centerCrop()
                .into(imgRoommate);
        Glide.with(getApplicationContext())
                .load(roomURL)
                .centerCrop()
                .into(imgRoom);
        Glide.with(getApplicationContext())
                .load(unitURL)
                .centerCrop()
                .into(imgUnit);
    }

    //open activity
    private void openIntent(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

                if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(HomeActivity.this, LogOnActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }else{

                    Intent intent = new Intent(HomeActivity.this, MyListings.class);
                    startActivity(intent);
                }

            }
        });

        btnRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RentalActivity.class);
                startActivity(intent);
            }
        });

        btnPaymentRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);
            }
        });

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FindRoomActivity.class);
                startActivity(intent);
            }
        });

        btnRoommate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FindRoommateActivity.class);
                startActivity(intent);
            }
        });

        btnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FindUnitActivity.class);
                startActivity(intent);
            }
        });

    }

    //get current time and date and display text accordingly
    private void currentTime(){
        currentTime = DateFormat.getDateInstance().format(new Date());
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24){
            greeting = "Good Night";
        } else {
            greeting = "Good Morning";
        }

        txtGreetings.setText(greeting);

        CountDownTimer newTimer = new CountDownTimer(1000000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (greeting.equals("Good Morning")){
                    Calendar cal = Calendar.getInstance();
                    txtCurrentTime.setText(currentTime + ", " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " AM" );
                }else{
                    Calendar cal = Calendar.getInstance();
                    txtCurrentTime.setText(currentTime + ", " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " PM" );
                }
            }

            @Override
            public void onFinish() {

            }
        };

        newTimer.start();
    }

}
