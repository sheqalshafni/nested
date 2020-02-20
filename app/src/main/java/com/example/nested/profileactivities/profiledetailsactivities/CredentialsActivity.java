package com.example.nested.profileactivities.profiledetailsactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nested.R;
import com.example.nested.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CredentialsActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        //init
        ButterKnife.bind(this);
        final User user = new User();
        ImageView btnBack = ButterKnife.findById(this, R.id.btnBack);
        TextView btnEdit = ButterKnife.findById(this, R.id.btnEdit);

        final TextView txtName = ButterKnife.findById(this, R.id.txtName);
        final TextView txtAge = ButterKnife.findById(this, R.id.txtAge);
        final TextView txtNRIC = ButterKnife.findById(this, R.id.txtNRIC);
        final TextView txtContact = ButterKnife.findById(this, R.id.txtContact);
        final TextView txtGender = ButterKnife.findById(this, R.id.txtGender);
        final TextView txtWarning = ButterKnife.findById(this, R.id.txtWarning);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();


        final ProgressDialog progressDialog = new ProgressDialog(CredentialsActivity.this);
        progressDialog.setMessage("Loading profile");

        if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

            try{

                progressDialog.show();
                String uid = firebaseAuth.getCurrentUser().getUid();
                db.collection("users").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        String name = documentSnapshot.getString("name");
                        String age = documentSnapshot.getString("age");
                        String contact = documentSnapshot.getString("contact");
                        String gender = documentSnapshot.getString("gender");
                        String nric = documentSnapshot.getString("nric");
                        txtGender.setText(gender);
                        txtNRIC.setText(nric);
                        txtContact.setText(contact);
                        txtAge.setText(age);
                        txtName.setText(name);
                        progressDialog.dismiss();

                        if(txtAge.getText().equals("") || txtContact.getText().equals("") || txtGender.getText().equals("") || txtName.getText().equals("")
                                || txtNRIC.getText().equals("") || txtNRIC.getText().equals("")){
                            txtWarning.setVisibility(View.VISIBLE);
                        }else{
                            txtWarning.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }catch (Exception ex){
                Toast.makeText(CredentialsActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(CredentialsActivity.this, "error", Toast.LENGTH_LONG).show();
        }



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CredentialsActivity.this, EditProfileActivity.class);
                intent.putExtra("name", txtName.getText());
                intent.putExtra("age", txtAge.getText());
                intent.putExtra("nric", txtNRIC.getText());
                intent.putExtra("contact", txtContact.getText());
                intent.putExtra("gender", txtGender.getText());
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
