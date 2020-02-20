package com.example.nested;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.nested.models.RentedUnit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewSelectedUnitActivity extends AppCompatActivity {

    private static final String TAG = "ViewSelectUnit";
    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.txtContact)
    TextView txtContact;
    @BindView(R.id.txtUserType)
    TextView txtUserType;
    @BindView(R.id.txtRoomTitle)
    TextView txtRoomTitle;
    @BindView(R.id.txtPreferredGender)
    TextView txtGender;
    @BindView(R.id.txtPreferredProfession)
    TextView txtProfession;
    @BindView(R.id.txtRoomPrice)
    TextView txtRoomPrice;
    @BindView(R.id.txtRoomType)
    TextView txtRoomType;
    @BindView(R.id.txtRoomState)
    TextView txtRoomState;
    @BindView(R.id.roomImage)
    ImageView mRoomImage;
    @BindView(R.id.btnEnquiry)
    Button btnEnquiry;
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
    @BindView(R.id.unitRL)
    RelativeLayout relativeLayout;

    private String UserType, PropertyType,RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, getDocumentID, preferredGender, preferredProfession, UID;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;

    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    private DatePickerDialog.OnDateSetListener date2;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_unit);

        init();
        getIncomingIntent();
        getData();
        getDatePickerValue();
        getStartEndDate();
        rentUnit();

    }

    private void init(){
        ButterKnife.bind(this);

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
            preferredGender = getIntent().getStringExtra("preferredgender");
            preferredProfession = getIntent().getStringExtra("preferredprofession");
            getDocumentID = getIntent().getStringExtra("documentid");

            setImage(UserType, PropertyType, RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, preferredGender, preferredProfession, getDocumentID);
        }
    }

    private void setImage(String userType, String propertyType, String roomPrice,
                          String roomState, String roomTitle, String postedBy,
                          String postedContact, String roomImage,
                          String preferredGender, String preferredProfession, String docID) {

        txtUsername.setText(postedBy);
        txtContact.setText(postedContact);
        txtUserType.setText(userType);
        txtRoomTitle.setText(roomTitle);
        txtGender.setText(preferredGender);
        txtProfession.setText(preferredProfession);
        txtRoomPrice.setText(roomPrice);
        txtRoomType.setText(propertyType);
        txtRoomState.setText(roomState);
        mDocumentID.setText(docID);
        Glide.with(getApplicationContext())
                .load(roomImage)
                .centerCrop()
                .into(mRoomImage);
    }

    private void getData(){

        String text = "tel:";
        String no = txtContact.getText().toString();
        final String postedContact = text + no;

        btnEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(postedContact));
                startActivity(intent);
            }
        });
    }

    private void rentUnit(){

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

                            if(UID == getUID){

                                Snackbar.make(relativeLayout, "You cannot rent more than one unit or room", Snackbar.LENGTH_LONG).show();

                            }else {

                                db.collection("unit").document(getDocumentID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                                        final String LandlordID = documentSnapshot.getString("user_ID");

                                        if (UID.equals(LandlordID)){

                                            Log.d(TAG, "tt " + LandlordID);

                                            Snackbar.make(relativeLayout, "You cannot your own listing", Snackbar.LENGTH_LONG).show();

                                        }else {

                                            db.collection("users").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                                                    String tenantName = documentSnapshot.getString("name");

                                                    String id = db.collection("rented_rooms").document().getId();

                                                    RentedUnit rentedUnit = new RentedUnit();
                                                    rentedUnit.setDocument_ID(getDocumentID);
                                                    rentedUnit.setLandlord_UID(LandlordID);
                                                    rentedUnit.setTenant_name(tenantName);
                                                    rentedUnit.setUser_ID(UID);
                                                    rentedUnit.setRoom_price(RoomPrice);
                                                    rentedUnit.setRoom_title(RoomTitle);
                                                    rentedUnit.setStartRent(mStartDate.getText().toString());
                                                    rentedUnit.setEndRent(mEndDate.getText().toString());
                                                    rentedUnit.setCurrent_docID(id);

                                                    Log.d(TAG, "start " + mStartDate);

                                                    if(mStartDate.getText().toString().isEmpty() && mEndDate.getText().toString().isEmpty()){

                                                        Toast.makeText(ViewSelectedUnitActivity.this, "Start and End date required to rent", Toast.LENGTH_SHORT).show();

                                                    }else {

                                                        DocumentReference newRentedRoomRef = db.collection("rented_rooms").document(UID);
                                                        newRentedRoomRef.set(rentedUnit).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()){
                                                                    Toast.makeText(ViewSelectedUnitActivity.this, "Unit successfully rented", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ViewSelectedUnitActivity.this, "error", Toast.LENGTH_SHORT).show();
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

                }else if (firebaseAuth.getCurrentUser().getUid() == null){
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ViewSelectedUnitActivity.this, LogOnActivity.class);
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
                        ViewSelectedUnitActivity.this,
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
                        ViewSelectedUnitActivity.this,
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
}
