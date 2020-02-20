package com.example.nested.payment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nested.R;
import com.example.nested.models.PaymentHistory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakePaymentActivity extends AppCompatActivity {

    @BindView(R.id.txtInvoiceNo)
    TextView txtInvoiceNo;
    @BindView(R.id.txtTenantName)
    TextView txtTenantName;
    @BindView(R.id.txtRoomTitle)
    TextView txtRoomTitle;
    @BindView(R.id.txtRoomPrice)
    TextView txtRoomPrice;
    @BindView(R.id.txtSubtotal)
    TextView txtSubtotal;
    @BindView(R.id.btnPayRent)
    Button btnPayrent;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.etReference)
    EditText etReference;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;

    private String UID, getRoomTitle, getTenantName, getRoomPrice, getCardPaymentType;
    private static final String TAG = "MakePaymentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        init();
        loadRentData();
        saveReceiptPayment();

    }

    private void init(){
        ButterKnife.bind(this);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadRentData(){

        UID = firebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("rented_rooms").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                getRoomTitle = documentSnapshot.getString("room_title");
                getTenantName = documentSnapshot.getString("tenant_name");
                getRoomPrice = documentSnapshot.getString("room_price");

                txtTenantName.setText(getTenantName);
                txtRoomTitle.setText(getRoomTitle);
                txtRoomPrice.setText(getRoomPrice);
                txtSubtotal.setText(getRoomPrice);
                txtInvoiceNo.setText("RI# " + randomUID());

            }
        });
    }

    protected String randomUID(){
        String Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * Char.length());
            salt.append(Char.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private void saveReceiptPayment(){

        btnPayrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    db.collection("card_details").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                            getCardPaymentType = documentSnapshot.getString("cardnumber");

                            try{

                                if(getCardPaymentType.isEmpty()){
                                    Toast.makeText(MakePaymentActivity.this, "Please set up your credit card before making any payments", Toast.LENGTH_LONG).show();
                                }else if (etReference.getText().toString().isEmpty()){

                                    Toast.makeText(MakePaymentActivity.this, "Recipient reference is compulsory", Toast.LENGTH_LONG).show();

                                }else{

//                                    Map<Object, String> payment = new HashMap<>();
//                                    payment.put("Invoice_No", txtInvoiceNo.getText().toString());
//                                    payment.put("Tenant_name", txtTenantName.getText().toString());
//                                    payment.put("Room_title", txtRoomTitle.getText().toString());
//                                    payment.put("Room_price", txtRoomPrice.getText().toString());
//                                    payment.put("Paid_with", getCardPaymentType);
//                                    payment.put("Recipient_reference", etReference.getText().toString());
//                                    payment.put("User_ID", UID);

                                    PaymentHistory paymentHistory = new PaymentHistory();
                                    paymentHistory.setInvoice_no(txtInvoiceNo.getText().toString());
                                    paymentHistory.setTenant_name(txtTenantName.getText().toString());
                                    paymentHistory.setRoom_title(txtRoomTitle.getText().toString());
                                    paymentHistory.setRoom_price(txtRoomPrice.getText().toString());
                                    paymentHistory.setPaid_with(getCardPaymentType);
                                    paymentHistory.setUser_ID(UID);

                                    DocumentReference newPaymentRef = db.collection("rented_payments").document();
                                    newPaymentRef.set(paymentHistory).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(MakePaymentActivity.this, "Payment success", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }

                            }catch (Exception ex){
                                Toast.makeText(MakePaymentActivity.this, "Please set up your credit card before making any payments", Toast.LENGTH_LONG).show();
                                Log.d(TAG, "Error: " +ex.getMessage());
                            }
                        }
                    });

                }catch (Exception ex){

                    Log.d(TAG, "Error: " +ex.getMessage());

                }

            }
        });
    }
}
