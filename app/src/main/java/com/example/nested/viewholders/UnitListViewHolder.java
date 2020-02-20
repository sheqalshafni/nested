package com.example.nested.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nested.R;

public class UnitListViewHolder extends RecyclerView.ViewHolder {

    public TextView mUserType, mPropertyType, mRoomPrice, mRoomState, mRoomTitle, mPostedBy, mPostedContact, mDocumentID, mPreferredGender, mPreferredProfession;
    public CardView parentLayout;
    public ImageView room_image;

    public UnitListViewHolder(@NonNull View itemView) {
        super(itemView);

        mUserType = itemView.findViewById(R.id.txtUserType);
        mPropertyType = itemView.findViewById(R.id.txtRoomType);
        mRoomPrice = itemView.findViewById(R.id.txtRoomPrice);
        mRoomState = itemView.findViewById(R.id.txtRoomState);
        room_image = itemView.findViewById(R.id.roomImage);
        mRoomTitle = itemView.findViewById(R.id.txtRoomTitle);
        mPostedBy = itemView.findViewById(R.id.txtUsername);
        mPostedContact = itemView.findViewById(R.id.txtContact);
        mDocumentID = itemView.findViewById(R.id.documentID);
        mPreferredGender = itemView.findViewById(R.id.txtPreferredGender);
        mPreferredProfession = itemView.findViewById(R.id.txtPreferredProfession);

        parentLayout = itemView.findViewById(R.id.parentLayout);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
            }
        });

    }
}
