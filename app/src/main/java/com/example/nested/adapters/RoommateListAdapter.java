package com.example.nested.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.nested.R;
import com.example.nested.ViewSelectedRoommateActivity;
import com.example.nested.models.Roommate;
import com.example.nested.viewholders.RoommateListViewHolder;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class RoommateListAdapter extends RecyclerView.Adapter<RoommateListViewHolder> {

    private List<Roommate> mRoommateList;
    private OnItemClickListener listener;
    private Context mContext;

    public RoommateListAdapter(List<Roommate> mRoommateList, Context mContext) {
        this.mRoommateList = mRoommateList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RoommateListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.content_roommate_list, viewGroup, false);

        return new RoommateListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RoommateListViewHolder roommateListViewHolder, int i) {

        final Roommate mRoommate = mRoommateList.get(i);

        roommateListViewHolder.mUserType.setText(mRoommateList.get(i).getPosted_as());
        roommateListViewHolder.mPropertyType.setText(mRoommateList.get(i).getProperty_type());
        roommateListViewHolder.mRoomPrice.setText(mRoommateList.get(i).getRoom_price());
        roommateListViewHolder.mRoomState.setText(mRoommateList.get(i).getRoom_state());
        roommateListViewHolder.mRoomTitle.setText(mRoommateList.get(i).getRoom_title());
        roommateListViewHolder.mPostedBy.setText(mRoommateList.get(i).getPosted_by());
        roommateListViewHolder.mPostedContact.setText(mRoommateList.get(i).getContact());
        roommateListViewHolder.mDocumentID.setText(mRoommateList.get(i).getDocument_ID());
        roommateListViewHolder.mPreferredGender.setText(mRoommateList.get(i).getPreferred_gender());
        roommateListViewHolder.mPreferredProfession.setText(mRoommateList.get(i).getPrefrerred_profession());
        Glide.with(mContext)
                .load(mRoommate.getImage_URL())
                .centerCrop()
                .into(roommateListViewHolder.room_image);

        roommateListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewSelectedRoommateActivity.class);
                intent.putExtra("usertype", mRoommate.getPosted_as());
                intent.putExtra("propertytype", mRoommate.getProperty_type());
                intent.putExtra("roomprice", mRoommate.getRoom_price());
                intent.putExtra("roomsstate", mRoommate.getRoom_state());
                intent.putExtra("roomtitle", mRoommate.getRoom_title());
                intent.putExtra("postedby", mRoommate.getPosted_by());
                intent.putExtra("postedcontact", mRoommate.getContact());
                intent.putExtra("roomimage", mRoommate.getImage_URL());
                intent.putExtra("documentid", mRoommate.getDocument_ID());
                intent.putExtra("preferredgender", mRoommate.getPreferred_gender());
                intent.putExtra("preferredprofession", mRoommate.getPrefrerred_profession());
                mContext.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {
        return mRoommateList.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
