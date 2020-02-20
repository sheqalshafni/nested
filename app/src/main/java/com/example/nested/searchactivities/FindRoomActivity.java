package com.example.nested.searchactivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nested.R;
import com.example.nested.adapters.RoomListAdapter;
import com.example.nested.models.Room;
import com.example.nested.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FindRoomActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.roomRecyclerView)
    RecyclerView room_RecyclerView;
    @BindView(R.id.btnPostRoom)
    FloatingActionButton fabPostRoom;
    @BindView(R.id.mRoomListRefresh)
    SwipeRefreshLayout mRefreshList;
    @BindView(R.id.btnBack)
    ImageView btnBack;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;
    private RoomListAdapter roomListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);

        ButterKnife.bind(this);
        mRefreshList.setOnRefreshListener(this);
        init();
        getRoomList();

    }

    @Override
    public void onRefresh() {
        getRoomList();
        mRefreshList.setRefreshing(false);
    }

    private void init(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        room_RecyclerView.setLayoutManager(layoutManager);
        db = FirebaseFirestore.getInstance();

        fabPostRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindRoomActivity.this, PostRoomActivity.class);
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

    private void getRoomList() {

        CollectionReference roomRef = FirebaseFirestore.getInstance().collection("rooms");
        roomRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {


                        List<Room> mRoom = task.getResult().toObjects(Room.class);
                        roomListAdapter = new RoomListAdapter(mRoom, FindRoomActivity.this);
                        roomListAdapter.notifyDataSetChanged();
                        room_RecyclerView.setAdapter(roomListAdapter);
                    }
                }
            }
        });
    }
}
