package com.example.nested.mainmenuactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.nested.R;
import com.example.nested.payment.MakePaymentActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentalActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.txtRoomTitle)
    TextView txtTitle;
    @BindView(R.id.rl_currentrental)
    RelativeLayout rentalLayout;
    @BindView(R.id.rental_anim_notfound)
    LottieAnimationView notFound;
    @BindView(R.id.txtInfo)
    TextView txtInfo;
    @BindView(R.id.txtRoomPrice)
    TextView txtRoomPrice;
    @BindView(R.id.currentRentPlaceholder)
    View btnCurrentRent;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;

    private String UID, roomTitle, getUserID, getRoomPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        ButterKnife.bind(this);
        init();
        loadData();
    }

    private void init(){

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        rentalLayout.setVisibility(View.GONE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCurrentRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RentalActivity.this, MakePaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){

        if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

            UID = firebaseAuth.getInstance().getCurrentUser().getUid();

            db.collection("rented_rooms").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                    getUserID = documentSnapshot.getString("user_ID");

                    if(UID.equals(getUserID)){

                        roomTitle = documentSnapshot.getString("room_title");
                        getRoomPrice = documentSnapshot.getString("room_price");

                        notFound.setVisibility(View.GONE);
                        txtInfo.setVisibility(View.GONE);
                        rentalLayout.setVisibility(View.VISIBLE);

                        txtTitle.setText(roomTitle);
                        txtRoomPrice.setText(getRoomPrice);

                    }

                }
            });

        }


    }

}
