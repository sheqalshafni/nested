package com.example.nested.searchactivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.nested.R;
import com.example.nested.adapters.RoommateListAdapter;
import com.example.nested.models.Roommate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FindRoommateActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnPostRoommate)
    FloatingActionButton btnPostRoommate;
    @BindView(R.id.roommateRecyclerView)
    RecyclerView roommateRecyclerView;
    @BindView(R.id.mRoommateListRefresh)
    SwipeRefreshLayout mRefreshList;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;
    private RoommateListAdapter roommateListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_roommate);

        init();
        getRoommateList();

    }

    private void init(){

        ButterKnife.bind(this);
        mRefreshList.setOnRefreshListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        roommateRecyclerView.setLayoutManager(layoutManager);
        db = FirebaseFirestore.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnPostRoommate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindRoommateActivity.this, PostRoommateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        getRoommateList();
        mRefreshList.setRefreshing(false);
    }

    private void getRoommateList() {

        CollectionReference roomRef = FirebaseFirestore.getInstance().collection("roommate");
        roomRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {

                        List<Roommate> mRoommate = task.getResult().toObjects(Roommate.class);
                        roommateListAdapter = new RoommateListAdapter(mRoommate, FindRoommateActivity.this);
                        roommateListAdapter.notifyDataSetChanged();
                        roommateRecyclerView.setAdapter(roommateListAdapter);
                    }
                }
            }
        });

    }
}
