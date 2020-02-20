package com.example.nested.profileactivities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nested.R;
import com.example.nested.models.User;
import com.example.nested.profileactivities.profiledetailsactivities.CredentialsActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.constraint.Constraints.TAG;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ProfileIDActivity extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    @BindView(R.id.profileImage)
    CircleImageView uploadImage;
    @BindView(R.id.personalization_placeholder)
    View btnPInfo;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.txtUserEmail)
    TextView txtEmail;
    @BindView(R.id.txtUsername)
    TextView txtUsername;

    private static final String TAG = "ProfileIDActivity";
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference ImageReference;

    String email, uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_id);

        init();
        uid = firebaseAuth.getCurrentUser().getUid();
        getUserInfo();
        openIntent();


    }

    //generate random unique user ID if the user have not set up their profile
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

    private void init(){
        ButterKnife.bind(this);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        ImageReference = FirebaseStorage.getInstance().getReference("User_Image");
        db = FirebaseFirestore.getInstance();
    }

    private void openIntent(){
        btnPInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileIDActivity.this, CredentialsActivity.class);
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

    //To get user info from firestore
    private void getUserInfo(){

        db.collection("users").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                String name = documentSnapshot.getString("name");


                if(name != null) {
                    txtUsername.setText(name);
                    txtEmail.setText(firebaseUser.getEmail());
                }else {
                    txtUsername.setText("User#"+randomUID());
                    txtEmail.setText(firebaseUser.getEmail());
                }
            }
        });

    }

}
