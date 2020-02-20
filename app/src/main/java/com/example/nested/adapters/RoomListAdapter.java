package com.example.nested.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.nested.R;
import com.example.nested.ViewSelectedRoom;
import com.example.nested.models.Room;
import com.example.nested.searchactivities.FindRoomActivity;
import com.example.nested.viewholders.RoomListViewHolder;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListViewHolder> {

    private List<Room> mRoomList;
    private OnItemClickListener listener;
    private Context mContext;

    public RoomListAdapter(List<Room> mRoomList, Context mContext) {
        this.mRoomList = mRoomList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RoomListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.content_room_list, viewGroup, false);

        return new RoomListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListViewHolder roomListViewHolder, final int i) {

        final Room mRoom = mRoomList.get(i);

        roomListViewHolder.mUserType.setText(mRoomList.get(i).getPosted_as());
        roomListViewHolder.mPropertyType.setText(mRoomList.get(i).getProperty_type());
        roomListViewHolder.mRoomPrice.setText(mRoomList.get(i).getRoom_price());
        roomListViewHolder.mRoomState.setText(mRoomList.get(i).getRoom_state());
        roomListViewHolder.mRoomTitle.setText(mRoomList.get(i).getRoom_title());
        roomListViewHolder.mPostedBy.setText(mRoomList.get(i).getPosted_by());
        roomListViewHolder.mPostedContact.setText(mRoomList.get(i).getContact());
        roomListViewHolder.mDocumentID.setText(mRoomList.get(i).getDocument_ID());
        Glide.with(mContext)
                .load(mRoom.getImage_URL())
                .centerCrop()
                .into(roomListViewHolder.room_image);

        roomListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewSelectedRoom.class);
                intent.putExtra("usertype", mRoom.getPosted_as());
                intent.putExtra("propertytype", mRoom.getProperty_type());
                intent.putExtra("roomprice", mRoom.getRoom_price());
                intent.putExtra("roomsstate", mRoom.getRoom_state());
                intent.putExtra("roomtitle", mRoom.getRoom_title());
                intent.putExtra("postedby", mRoom.getPosted_by());
                intent.putExtra("postedcontact", mRoom.getContact());
                intent.putExtra("roomimage", mRoom.getImage_URL());
                intent.putExtra("documentid", mRoom.getDocument_ID());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
