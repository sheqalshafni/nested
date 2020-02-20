package com.example.nested.searchactivities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nested.LogOnActivity;
import com.example.nested.R;
import com.example.nested.models.Unit;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.omidh.liquidradiobutton.LiquidRadioButton;

import static android.support.constraint.Constraints.TAG;

public class PostUnitActivity extends AppCompatActivity {

    @BindView(R.id.radUserType)
    RadioGroup radUser;
    @BindView(R.id.radGender)
    RadioGroup radGender;
    @BindView(R.id.radRoomState)
    RadioGroup radState;
    @BindView(R.id.radRoomType)
    RadioGroup radRType;
    @BindView(R.id.radPropertyType)
    RadioGroup radPType;
    @BindView(R.id.radProfession)
    RadioGroup radProfession;

    @BindView(R.id.etRoomPrice)
    EditText roomPrice;
    @BindView(R.id.etRoomTitle)
    EditText roomTitle;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.uploadImg)
    ImageView uploadImage;

    private LiquidRadioButton userType, pGender, pRType, pState, pPType, mProfession;

    Uri mSelectedImageUri;

    AlertDialog.Builder builder;

    String saveCurrentDate, saveCurrentTime, downloadImageURL, productKEY, UID, mName, mGender, mContact;

    private static final int GALLERY_CODE = 101;
    private static final int REQUEST_CODE = 102;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;
    FirebaseStorage storage;
    StorageReference ImageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_unit);

        init();
        getImage();
        saveOnClick();

    }

    private void init(){

        ButterKnife.bind(this);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        UID = firebaseAuth.getInstance().getCurrentUser().getUid();
        ImageReference = FirebaseStorage.getInstance().getReference("Roommate");

    }

    private void getImage(){

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
                verifyPermissions();

            }
        });
    }

    private void OpenGallery() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    private void verifyPermissions() {

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,};

        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                permissions[0])== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        permissions[1])== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        permissions[2])== PackageManager.PERMISSION_GRANTED){
        }else{
            ActivityCompat.requestPermissions(PostUnitActivity.this, permissions, REQUEST_CODE);
        }

    }

    private void storeUnitInformation(){

        final ProgressDialog pd = new ProgressDialog(PostUnitActivity.this);
        pd.setMessage("Creating your list");

        pd.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productKEY = saveCurrentDate + saveCurrentTime;

        final StorageReference filepath = ImageReference.child(mSelectedImageUri.getLastPathSegment() + productKEY);

        final UploadTask uploadTask = filepath.putFile(mSelectedImageUri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    throw task.getException();
                }

                return filepath.getDownloadUrl();

            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    pd.dismiss();
                    try{
                        Uri downloadUri = task.getResult();
                        downloadImageURL = downloadUri.toString();
                        saveUnitInfotoFirebase();
                    }catch (Exception e){
                        Log.d(TAG, "Null object " + e.getMessage());
                    }

                }else {
                    pd.dismiss();
                    Log.d(TAG, "Err: " + task.getException());
                }
            }
        });

    }

    private void saveUnitInfotoFirebase() {

        if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

            try{

                db.collection("users").document(UID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        mName = documentSnapshot.getString("name");
                        mContact = documentSnapshot.getString("contact");

                        int userPost = radUser.getCheckedRadioButtonId();
                        int gender = radGender.getCheckedRadioButtonId();
                        int rState = radState.getCheckedRadioButtonId();
                        int rType = radRType.getCheckedRadioButtonId();
                        int pType = radPType.getCheckedRadioButtonId();
                        int profession = radProfession.getCheckedRadioButtonId();

                        userType = findViewById(userPost);
                        pGender = findViewById(gender);
                        pRType = findViewById(rType);
                        pState = findViewById(rState);
                        pPType = findViewById(pType);
                        mProfession = findViewById(profession);

                        try{

                            String uType = userType.getText().toString();
                            String prefGender = pGender.getText().toString();
                            String prefProfession = mProfession.getText().toString();
                            String roomType =  pRType.getText().toString();
                            String roomState = pState.getText().toString();
                            String propType = pPType.getText().toString();
                            String rPrice = roomPrice.getText().toString();
                            String rTitle = roomTitle.getText().toString();
                            String id = db.collection("unit").document().getId();

                            if(!uType.isEmpty() && !roomType.isEmpty() && !roomState.isEmpty() && !propType.isEmpty() && !rPrice.isEmpty() && !rTitle.isEmpty()){

                                Unit mUnit = new Unit();
                                mUnit.setDocument_ID(id);
                                mUnit.setImage_URL(downloadImageURL);
                                mUnit.setPosted_as(uType);
                                mUnit.setPosted_by(mName);
                                mUnit.setContact(mContact);
                                mUnit.setProperty_type(propType);
                                mUnit.setRoom_price(rPrice);
                                mUnit.setRoom_state(roomState);
                                mUnit.setRoom_title(rTitle);
                                mUnit.setProperty_type(roomType);
                                mUnit.setUser_ID(UID);
                                mUnit.setPreferred_gender(prefGender);
                                mUnit.setPreferred_profession(prefProfession);
                                DocumentReference newRoomRef = db.collection("unit").document(id);
                                newRoomRef.set(mUnit).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(PostUnitActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        }else{

                                        }
                                    }
                                });

                            }else {
                                Toast.makeText(PostUnitActivity.this, "Please fill out all information before creating your listing", Toast.LENGTH_LONG).show();
                            }


                        }catch (Exception ex){
                            Toast.makeText(PostUnitActivity.this, "Please fill out all information before creating your listing", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }catch (Exception ex){
                Toast.makeText(PostUnitActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(PostUnitActivity.this, "error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == GALLERY_CODE && resultCode == RESULT_OK && data != null){
            mSelectedImageUri = data.getData();
            uploadImage.setImageURI(mSelectedImageUri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveOnClick(){

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firebaseAuth.getCurrentUser() != null && firebaseUser != null){

                    try{
                        if (mSelectedImageUri == null){
                            Toast.makeText(PostUnitActivity.this, "Please provide an image", Toast.LENGTH_SHORT).show();
                        }else{
                            UID = firebaseAuth.getCurrentUser().getUid();
                            storeUnitInformation();
                        }
                    }catch (Exception e) {
                        //log error message
                        Log.d(TAG, e.getMessage());
                    }
                }else{
                    //Toast.makeText(PostRoomActivity.this, "You have to be signed in to create your own listings", Toast.LENGTH_LONG).show();
                    builder.setTitle("Sign in required");
                    builder.setMessage("You have to be signed in to create your own listings \n\nPlease sign in to ensure full access of the application");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(PostUnitActivity.this, LogOnActivity.class);
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
                }
            }
        });

    }

}
