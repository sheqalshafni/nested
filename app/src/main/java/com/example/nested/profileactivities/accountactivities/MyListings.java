package com.example.nested.profileactivities.accountactivities;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.nested.R;
import com.example.nested.adapters.RoomListAdapter;
import com.example.nested.models.Room;
import com.example.nested.searchactivities.FindRoomActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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


public class MyListings extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.myListingsRecyclerView)
    RecyclerView myListing;
    @BindView(R.id.splashcreen_anim)
    LottieAnimationView mListingNotFound;
    @BindView(R.id.txtInfo)
    TextView txtInfo;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private FirebaseFirestore db;
    private RoomListAdapter roomListAdapter;

    List<Room> room = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistings);

        ButterKnife.bind(this);
        init();
        displayRoomListing();

    }

    private void displayRoomListing(){

        final CollectionReference roomRef = FirebaseFirestore.getInstance().collection("rooms");
        roomRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();
                    if(querySnapshot != null){

                        Query listingQuery = roomRef
                                .whereEqualTo("user_ID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        listingQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){

                                        Room mRoom = documentSnapshot.toObject(Room.class);
                                        room.add(mRoom);
                                        mListingNotFound.setVisibility(View.GONE);
                                        txtInfo.setVisibility(View.GONE);

                                    }
                                    roomListAdapter = new RoomListAdapter(room, MyListings.this);
                                    roomListAdapter.notifyDataSetChanged();
                                    myListing.setAdapter(roomListAdapter);
                                }else {
                                    Toast.makeText(MyListings.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void init(){
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myListing.setLayoutManager(layoutManager);
        db = FirebaseFirestore.getInstance();
    }
}
