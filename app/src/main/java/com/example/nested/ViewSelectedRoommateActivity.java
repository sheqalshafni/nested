package com.example.nested;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewSelectedRoommateActivity extends AppCompatActivity {

    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.txtContact)
    TextView txtContact;
    @BindView(R.id.txtUserType)
    TextView txtUserType;
    @BindView(R.id.txtRoomTitle)
    TextView txtRoomTitle;
    @BindView(R.id.txtPreferredGender)
    TextView txtGender;
    @BindView(R.id.txtPreferredProfession)
    TextView txtProfession;
    @BindView(R.id.txtRoomPrice)
    TextView txtRoomPrice;
    @BindView(R.id.txtRoomType)
    TextView txtRoomType;
    @BindView(R.id.txtRoomState)
    TextView txtRoomState;
    @BindView(R.id.roomImage)
    ImageView mRoomImage;
    @BindView(R.id.btnEnquiry)
    Button btnEnquiry;
    @BindView(R.id.btnBack)
    ImageView btnBack;

    private String UserType, PropertyType,RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, getDocumentID, preferredGender, preferredProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_roommate);

        init();
        getIncomingIntent();
        getData();
    }

    private void init(){
        ButterKnife.bind(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("usertype") && getIntent().hasExtra("propertytype") && getIntent().hasExtra("roomprice")){
            UserType = getIntent().getStringExtra("usertype");
            PropertyType = getIntent().getStringExtra("propertytype");
            RoomPrice = getIntent().getStringExtra("roomprice");
            RoomState = getIntent().getStringExtra("roomsstate");
            RoomTitle = getIntent().getStringExtra("roomtitle");
            PostedBy = getIntent().getStringExtra("postedby");
            PostedContact = getIntent().getStringExtra("postedcontact");
            RoomImage = getIntent().getStringExtra("roomimage");
            preferredGender = getIntent().getStringExtra("preferredgender");
            preferredProfession = getIntent().getStringExtra("preferredprofession");

            setImage(UserType, PropertyType, RoomPrice, RoomState, RoomTitle, PostedBy, PostedContact, RoomImage, preferredGender, preferredProfession);
        }
    }

    private void setImage(String userType, String propertyType, String roomPrice,
                          String roomState, String roomTitle, String postedBy,
                          String postedContact, String roomImage,
                          String preferredGender, String preferredProfession) {

        txtUsername.setText(postedBy);
        txtContact.setText(postedContact);
        txtUserType.setText(userType);
        txtRoomTitle.setText(roomTitle);
        txtGender.setText(preferredGender);
        txtProfession.setText(preferredProfession);
        txtRoomPrice.setText(roomPrice);
        txtRoomType.setText(propertyType);
        txtRoomState.setText(roomState);
        Glide.with(getApplicationContext())
                .load(roomImage)
                .centerCrop()
                .into(mRoomImage);
    }

    private void getData(){

        String text = "tel:";
        String no = txtContact.getText().toString();
        final String postedContact = text + no;

        btnEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(postedContact));
                startActivity(intent);
            }
        });
    }
}
