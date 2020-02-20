package com.example.nested.searchactivities;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
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
import com.example.nested.adapters.UnitListAdapter;
import com.example.nested.models.Unit;
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


public class FindUnitActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.unitRecyclerView)
    RecyclerView unitRecyclerView;
    @BindView(R.id.btnPostUnit)
    FloatingActionButton btnPostUnit;
    @BindView(R.id.mUnitRefreshList)
    SwipeRefreshLayout mRefreshList;

    private UnitListAdapter unitListAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_unit);

        init();
        mRefreshList.setOnRefreshListener(this);
        getUnitList();

    }

    private void init(){

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        unitRecyclerView.setLayoutManager(layoutManager);
        db = FirebaseFirestore.getInstance();

        btnPostUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindUnitActivity.this, PostUnitActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onRefresh() {
        getUnitList();
        mRefreshList.setRefreshing(false);
    }

    private void getUnitList() {

        CollectionReference roomRef = FirebaseFirestore.getInstance().collection("unit");
        roomRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {


                        List<Unit> mUnit = task.getResult().toObjects(Unit.class);
                        unitListAdapter = new UnitListAdapter(mUnit, FindUnitActivity.this);
                        unitListAdapter.notifyDataSetChanged();
                        unitRecyclerView.setAdapter(unitListAdapter);
                    }
                }
            }
        });

    }
}
