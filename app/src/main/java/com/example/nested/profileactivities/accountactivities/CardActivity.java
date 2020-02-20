package com.example.nested.profileactivities.accountactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nested.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CardActivity extends AppCompatActivity {

    private static final String TAG = "CardActivity" ;
    @BindView(R.id.default_card_placeholder)
    View btnViewCard;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddCard)
    FloatingActionButton btnAddCard;
    @BindView(R.id.txtCardNumber)
    TextView txtCardNumber;
    @BindView(R.id.cardType)
    ImageView cardType;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    private String UID, cardNo;

    List<String> mastercard = Arrays.asList("51", "52", "53", "54", "55", "22", "23", "24", "25", "26", "27");
    List<String > amex = Arrays.asList("34", "37");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ButterKnife.bind(this);
        init();
        openIntent();
        loadCard();

    }

    private void init(){
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        UID = firebaseAuth.getCurrentUser().getUid();

    }

    private void loadCard(){

            try{

                db.collection("card_details").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                        changeText();

                        try{

                            cardNo = documentSnapshot.getString("cardnumber");
                            txtCardNumber.setText(cardNo);

                            String a = txtCardNumber.getText().toString();

                            if(mastercard.contains(a.substring(0,2))){
                                cardType.setImageResource(R.drawable.ic_mastercard);
                            }else if (amex.contains(a.substring(0,1))){
                                cardType.setImageResource(R.drawable.ic_amex);
                            }else if (a.substring(0,1).equals("4")){
                                cardType.setImageResource(R.drawable.ic_visa);
                            }else{
                                cardType.setImageResource(R.drawable.ic_blank_card);
                            }

                        }catch (Exception ex){
                            Log.d(TAG, "Card error: "+ex.getMessage());
                        }


                    }
                });

            }catch (Exception ex){
                Toast.makeText(CardActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }


    }

    private void openIntent(){
        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, CardDetailsActivity.class);
                startActivity(intent);
            }
        });
        btnViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardActivity.this, ViewCreditCardActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void changeText(){
        txtCardNumber.addTextChangedListener(new TextWatcher() {
            private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
            private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
            private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
            private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
            private static final char DIVIDER = '-';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                    s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
                }
            }
        });
    }

    private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
        boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
        for (int i = 0; i < s.length(); i++) { // check that every element is right
            if (i > 0 && (i + 1) % dividerModulo == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }

    private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }

    private char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int index = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }

}
