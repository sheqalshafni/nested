package com.example.nested.profileactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nested.LogOnActivity;
import com.example.nested.R;
import com.example.nested.profileactivities.accountactivities.MyListings;
import com.example.nested.profileactivities.accountactivities.CardActivity;
import com.example.nested.profileactivities.settingsactivities.LanguageActivity;
import com.example.nested.profileactivities.settingsactivities.PrivacyActivity;
import com.example.nested.profileactivities.supportactivities.AboutActivity;
import com.example.nested.profileactivities.supportactivities.DeleteAccountActivity;
import com.example.nested.profileactivities.supportactivities.RulesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;


public class ProfileActivity extends AppCompatActivity {

    //Account bindview
    @BindView(R.id.card_placeholder)
    View btnCard;
    @BindView(R.id.address_placeholder)
    View btnAddress;
    @BindView(R.id.profile_placeholder)
    View btnProfile;

    //Settings bindview
    @BindView(R.id.privacy_placeholder)
    View btnPrivacy;
    @BindView(R.id.language_placeholder)
    View btnLanguage;

    //Support bindview
    @BindView(R.id.about_placeholder)
    View btnAbout;
    @BindView(R.id.rules_placeholder)
    View btnRules;
    @BindView(R.id.accountdelete_placeholder)
    View btnAccDelete;
    @BindView(R.id.btnSignOut)
    Button btnSignout;
    @BindView(R.id.btnSignIn)
    Button btnSignin;

    @BindView(R.id.btnBack)
    ImageView btnBack;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //init
        //account
        btnCard = findViewById(R.id.card_placeholder);
        btnAddress = findViewById(R.id.address_placeholder);
        btnProfile = findViewById(R.id.profile_placeholder);
        //settings
        btnLanguage = findViewById(R.id.language_placeholder);
        btnPrivacy = findViewById(R.id.privacy_placeholder);
        //support
        btnAbout = findViewById(R.id.about_placeholder);
        btnRules = findViewById(R.id.rules_placeholder);
        btnAccDelete = findViewById(R.id.accountdelete_placeholder);

        btnSignout = findViewById(R.id.btnSignOut);
        btnSignin = findViewById(R.id.btnSignIn);
        btnBack = findViewById(R.id.btnBack);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        profileAccount();
        profileSettings();
        profileSupport();

        if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
            btnSignout.setVisibility(View.GONE);

        }else {
            btnSignout.setVisibility(View.VISIBLE);
            btnSignin.setVisibility(View.INVISIBLE);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //sigin
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                startActivity(intent);
            }
        });

        //signout
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

                        firebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(ProfileActivity.this, "You're not currently signed in", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){

                }

            }
        });
    }

    public void profileAccount() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }else{

                    Intent intent = new Intent(ProfileActivity.this, ProfileIDActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }else{
                    Intent intent = new Intent(ProfileActivity.this, CardActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 {
                     if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
                         builder.setTitle("Sign in required");
                         builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                             public void onClick(DialogInterface dialog, int which) {
                                 Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                                 startActivity(intent);
                                 dialog.dismiss();
                             }
                         });

                         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                             }
                         });

                         AlertDialog alert = builder.create();
                         alert.show();

                     }else{
                         Intent intent = new Intent(ProfileActivity.this, MyListings.class);
                         startActivity(intent);
                     }
                }
            }
        });
    }

    public void profileSettings() {
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, PrivacyActivity.class);
                startActivity(intent);
            }
        });

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LanguageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void profileSupport() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });

        btnAccDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firebaseAuth.getCurrentUser() == null && firebaseUser == null){
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be logged in to be able to edit your profile \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ProfileActivity.this, LogOnActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }else{

                    Intent intent = new Intent(ProfileActivity.this, DeleteAccountActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
