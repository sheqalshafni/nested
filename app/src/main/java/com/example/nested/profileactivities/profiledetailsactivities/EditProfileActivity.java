package com.example.nested.profileactivities.profiledetailsactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;
import com.example.nested.R;
import com.example.nested.adapters.RoomListAdapter;
import com.example.nested.models.Room;
import com.example.nested.models.User;
import com.example.nested.profileactivities.accountactivities.MyListings;
import com.example.nested.searchactivities.FindRoomActivity;
import com.example.nested.searchactivities.PostRoomActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileActivity extends AppCompatActivity {

    private List<String> options;
    private PickerUI mPickerUI;
    private int currentPosition = -1;
    private static final String TAG = "EditProfileActivity";

    @BindView(R.id.gender_placeholder)
    View gender;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        user = new User();

        gender = findViewById(R.id.gender_placeholder);
        mPickerUI = findViewById(R.id.genderPicker);
        ImageView btnBack = ButterKnife.findById(this, R.id.btnBack);
        TextView btnSave = ButterKnife.findById(this, R.id.btnSave);
        final TextView setGender = ButterKnife.findById(this, R.id.txtGender);

        final EditText txtName = ButterKnife.findById(this, R.id.txtName);
        final EditText txtAge = ButterKnife.findById(this, R.id.txtAge);
        final EditText txtNRIC = ButterKnife.findById(this, R.id.txtNRIC);
        final EditText txtContact = ButterKnife.findById(this, R.id.txtContact);
        final TextView txtGender = ButterKnife.findById(this, R.id.txtGender);

        genderPicker();

        options = Arrays.asList(getResources().getStringArray(R.array.gender));
        mPickerUI.setItems(this, options);
        mPickerUI.setItemsClickables(false);
        mPickerUI.setAutoDismiss(false);
        mPickerUI.setOnClickItemPickerUIListener(
                new PickerUI.PickerUIItemClickListener() {

                    @Override
                    public void onItemClickPickerUI(int which, int position, String valueResult) {
                        currentPosition = position;
                        setGender.setText(valueResult.toString());
                    }
                });

        //get data from CredentialsActivity
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String nric = getIntent().getStringExtra("nric");
        String contact = getIntent().getStringExtra("contact");
        String gender = getIntent().getStringExtra("gender");

        //set values
        txtName.setText(name);
        txtAge.setText(age);
        txtNRIC.setText(nric);
        txtContact.setText(contact);
        txtGender.setText(gender);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = firebaseAuth.getCurrentUser().getUid();

                String name = txtName.getText().toString();
                String nric = txtNRIC.getText().toString();
                String age =  txtAge.getText().toString();
                String contact = txtContact.getText().toString();
                String gender = txtGender.getText().toString();

                if(!name.isEmpty() && !nric.isEmpty() && !age.isEmpty() && !contact.isEmpty() && !gender.isEmpty()){

                    Map<String, Object> user  = new HashMap<>();
                    user.put("name", name);
                    user.put("nric", nric);
                    user.put("age", age);
                    user.put("contact", contact);
                    user.put("gender", gender);

                    db.collection("users").document(uid).set(user);

                    Toast.makeText(EditProfileActivity.this, "Your profile has been updated", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditProfileActivity.this, CredentialsActivity.class);
                    startActivity(intent);
                    finish();

                }else {

                    Toast.makeText(EditProfileActivity.this, "Please fill out all the information before updating your profile", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    public void genderPicker(){
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerUISettings pickerUISettings =
                        new PickerUISettings.Builder().withItems(options)
                                .build();
                mPickerUI.setSettings(pickerUISettings);

                if(currentPosition==-1) {
                    mPickerUI.slide();
                }
                else{
                    mPickerUI.slide(currentPosition);
                }
            }
        });
    }

}
