package com.example.nested.mainmenuactivities;

import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nested.R;
import com.example.nested.adapters.PaymentListAdapter;
import com.example.nested.models.PaymentHistory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PaymentHistoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.paymentHistoryRecyclerView)
    RecyclerView paymentRV;
    @BindView(R.id.mPaymentHistoryRefresh)
    SwipeRefreshLayout mPaymentRefresh;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    private final static String TAG = "PaymentHistoryActivity";
    private PaymentListAdapter paymentListAdapter;
    List<PaymentHistory> paymentHistories = new ArrayList<PaymentHistory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        init();
        mPaymentRefresh.setOnRefreshListener(this);
        viewPastPayment();
    }

    private void init(){
        ButterKnife.bind(this);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        paymentRV.setLayoutManager(layoutManager);
        db = FirebaseFirestore.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void viewPastPayment(){

        final CollectionReference paymentRef = FirebaseFirestore.getInstance().collection("rented_payments");
        paymentRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();
                    if(querySnapshot != null){

                        Query listingQuery = paymentRef
                                .whereEqualTo("User_ID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        listingQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){

                                        PaymentHistory mHistory = documentSnapshot.toObject(PaymentHistory.class);
                                        paymentHistories.add(mHistory);

                                    }
                                    paymentListAdapter = new PaymentListAdapter(paymentHistories, PaymentHistoryActivity.this);
                                    paymentListAdapter.notifyDataSetChanged();
                                    paymentRV.setAdapter(paymentListAdapter);
                                }else {
                                    Toast.makeText(PaymentHistoryActivity.this, "Unable to load data", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Retrieve failed: " +task.getException());
                                }
                            }
                        });
                    }
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        viewPastPayment();
        mPaymentRefresh.setRefreshing(false);
    }
}
