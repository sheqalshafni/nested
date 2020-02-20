package com.example.nested.profileactivities.accountactivities;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nested.R;
import com.example.nested.models.CardDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.tenbis.support.views.CompactCreditInput;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardDetailsActivity extends AppCompatActivity {

    @BindView(R.id.btnAddCard)
    Button btnAddCard;
    @BindView(R.id.btnBack)
    ImageView btnBack;

    @BindView(R.id.txtCardDetails)
    CompactCreditInput txtCardDetails;

    private final static String TAG = "CardDetailsActivity";
    String cardnumber, expirydate, UID, docID, userID, cvv;

//    List<String> mastercard = Arrays.asList("51", "52", "53", "54", "55", "22", "23", "24", "25", "26", "27");
//    List<String > amex = Arrays.asList("34", "37");
//    List<String> options;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;
    CardDetails cardDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        ButterKnife.bind(this);
        init();
        saveCardDetails();
        openIntent();

    }

    private void init(){
        //initialize firebase instance
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        //get current user UID
        UID = firebaseAuth.getInstance().getCurrentUser().getUid();

        //instantiate model class
        cardDetails = new CardDetails();
    }

    private void openIntent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveCardDetails(){

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

                    try{

                        cardnumber = txtCardDetails.getCardNumberInput().getText().toString();
                        expirydate = txtCardDetails.getCardExpirationDateInput().getText().toString();
                        cvv = txtCardDetails.getCardCvvNumberInput().getText().toString();

                        if(!cardnumber.isEmpty() && !expirydate.isEmpty() && !cvv.isEmpty()){

                            cardDetails.setCardnumber(cardnumber);
                            cardDetails.setCardexpirydate(expirydate);
                            cardDetails.setUser_ID(UID);

                            DocumentReference documentReference = db.collection("card_details").document(UID);
                            documentReference.set(cardDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CardDetailsActivity.this, "New card added", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(CardDetailsActivity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
                                        //log error message
                                        Log.d(TAG, task.getException().getMessage());
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(CardDetailsActivity.this, "Please fill in all information before saving a new card", Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception ex){
                        Toast.makeText(CardDetailsActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(CardDetailsActivity.this, "User null", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
