package com.example.nested;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nested.models.RentedRoom;
import com.example.nested.models.Room;
import com.example.nested.profileactivities.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewSelectedRoom extends AppCompatActivity {

    private static final String TAG = "ViewSelectedRoom";
    @BindView(R.id.txtUsername)
    TextView mUsername;
    @BindView(R.id.txtContact)
    TextView mContact;
    @BindView(R.id.txtUserType)
    TextView mUserType;
    @BindView(R.id.txtRoomTitle)
    TextView mRoomTitle;
    @BindView(R.id.txtRoomPrice)
    TextView mRoomPrice;
    @BindView(R.id.txtRoomType)
    TextView mRoomType;
    @BindView(R.id.txtRoomState)
    TextView mRoomState;
    @BindView(R.id.roomImage)
    ImageView mRoomImage;
    @BindView(R.id.btnEnquiry)
    Button mEnquiry;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnRent)
    Button btnRent;
    @BindView(R.id.et_dateStart)
    EditText mStartDate;
    @BindView(R.id.et_dateEnd)
    EditText mEndDate;
    @BindView(R.id.documentID)
    TextView mDocumentID;
    @BindView(R.id.roomRL)
    RelativeLayout relativeLayout;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    private DatePickerDialog.OnDateSetListener date2;
    private int year, month, day;

    private static ArrayList<Room> roomArrayList = new ArrayList<>();

    private String UserType, PropertyType, RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, UID, getDocumentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_room);

        ButterKnife.bind(this);
        Calendar myCalendar = Calendar.getInstance();
        init();
        getIncomingIntent();
        getData();
        getStartEndDate();
        getDatePickerValue();
        rentRoom();


    }

    private void init(){

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void rentRoom(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){
                    UID = firebaseAuth.getCurrentUser().getUid();
                    db.collection("rented_rooms").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                            String getUID = documentSnapshot.getString("user_ID");
                            if(UID.equals(getUID)){
                                Snackbar.make(relativeLayout, "You cannot rent more than one unit or room", Snackbar.LENGTH_LONG).show();
                            } else {
                                db.collection("rooms").document(getDocumentID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                        final String LandlordID = documentSnapshot.getString("user_ID");
                                        if (UID.equals(LandlordID)){
                                            Log.d(TAG, "tt " + LandlordID);
                                            Snackbar.make(relativeLayout, "You cannot rent your own listing", Snackbar.LENGTH_LONG).show();
                                        }else {
                                            db.collection("users").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                                    String tenantName = documentSnapshot.getString("name");
                                                    String id = db.collection("rented_rooms").document().getId();
                                                    RentedRoom rentedRoom = new RentedRoom();
                                                    rentedRoom.setDocument_ID(getDocumentID);
                                                    rentedRoom.setLandlord_UID(LandlordID);
                                                    rentedRoom.setTenant_name(tenantName);
                                                    rentedRoom.setUser_ID(UID);
                                                    rentedRoom.setRoom_price(RoomPrice);
                                                    rentedRoom.setRoom_title(RoomTitle);
                                                    rentedRoom.setStartRent(mStartDate.getText().toString());
                                                    rentedRoom.setEndRent(mEndDate.getText().toString());
                                                    rentedRoom.setCurrent_docID(id);
                                                    Log.d(TAG, "start " +mStartDate);
                                                    if(mStartDate.getText().toString().isEmpty() && mEndDate.getText().toString().isEmpty()){
                                                        Toast.makeText(ViewSelectedRoom.this, "Start and End date required to rent", Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        DocumentReference newRentedRoomRef = db.collection("rented_rooms").document(UID);
                                                        newRentedRoomRef.set(rentedRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){
                                                                    Toast.makeText(ViewSelectedRoom.this, "Room successfully rented", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ViewSelectedRoom.this, "error", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                }else {

                    try{

                        if(firebaseAuth.getCurrentUser().getUid().isEmpty() && firebaseUser == null){

                            builder.setTitle("Sign in required");
                            builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ViewSelectedRoom.this, LogOnActivity.class);
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
                        }


                    }catch (Exception e){
                        builder.setTitle("Sign in required");
                        builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ViewSelectedRoom.this, LogOnActivity.class);
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

                        Log.d(TAG, "cant find userID "+e.getMessage());
                    }


                }

            }
        });
    }

    private void getDatePickerValue(){
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //+1 to get exact month "January = 0"
                month = month +1;
                String currentDate = day + "/" + month + "/" + year;
                mStartDate.setText(currentDate);


            }
        };

        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //+1 to get exact month "January = 0"
                month = month +1;
                String currentDate = day + "/" + month + "/" + year;
                mEndDate.setText(currentDate);

            }
        };
    }

    private void getStartEndDate(){

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(
                        ViewSelectedRoom.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        date,
                        year, month, day);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(
                        ViewSelectedRoom.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        date2,
                        year, month, day);
                myCalendar.add(Calendar.YEAR, 1);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
    }

    //get data to show on the next activity
    private void getIncomingIntent(){
        if(getIntent().hasExtra("usertype") && getIntent().hasExtra("propertytype") && getIntent().hasExtra("roomprice")){
            UserType = getIntent().getStringExtra("usertype");
            PropertyType = getIntent().getStringExtra("propertytype");
            RoomPrice = getIntent().getStringExtra("roomprice");
            RoomState = getIntent().getStringExtra("roomsstate");
            RoomTitle = getIntent().getStringExtra("roomtitle");
            PostedBy = getIntent().getStringExtra("postedby");
            PostedContact = getIntent().getStringExtra("postedcontact");
            RoomImage = getIntent().getStringExtra("roomimage");
            getDocumentID = getIntent().getStringExtra("documentid");

            setImage(UserType, PropertyType, RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, getDocumentID);
        }
    }

    private void setImage(String UserType, String PropertyType, String RoomPrice, String RoomState, String RoomTitle, String PostedBy, String PostedContact, String RoomImage, String docID) {

        mUsername.setText(PostedBy);
        mContact.setText(PostedContact);
        mUserType.setText(UserType);
        mRoomPrice.setText(RoomPrice);
        mRoomState.setText(RoomState);
        mRoomTitle.setText(RoomTitle);
        mRoomType.setText(PropertyType);
        mDocumentID.setText(docID);
        Glide.with(getApplicationContext())
                .load(RoomImage)
                .centerCrop()
                .into(mRoomImage);

    }

    private void getData(){

        String text = "tel:";
        String no = mContact.getText().toString();
        final String postedContact = text + no;

        mEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(postedContact));
                startActivity(intent);
            }
        });
    }
}
