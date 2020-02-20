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
import com.example.nested.ViewSelectedUnitActivity;
import com.example.nested.models.Unit;
import com.example.nested.viewholders.UnitListViewHolder;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class UnitListAdapter extends RecyclerView.Adapter<UnitListViewHolder> {

    private List<Unit> mUnitList;
    private Context mContext;
    private OnItemClickListener listener;

    public UnitListAdapter(List<Unit> mUnitList, Context mContext) {
        this.mUnitList = mUnitList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UnitListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.content_unit_list, viewGroup, false);

        return new UnitListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitListViewHolder unitListViewHolder, int i) {

        final Unit mUnit = mUnitList.get(i);

        unitListViewHolder.mUserType.setText(mUnitList.get(i).getPosted_as());
        unitListViewHolder.mPropertyType.setText(mUnitList.get(i).getProperty_type());
        unitListViewHolder.mRoomPrice.setText(mUnitList.get(i).getRoom_price());
        unitListViewHolder.mRoomState.setText(mUnitList.get(i).getRoom_state());
        unitListViewHolder.mRoomTitle.setText(mUnitList.get(i).getRoom_title());
        unitListViewHolder.mPostedBy.setText(mUnitList.get(i).getPosted_by());
        unitListViewHolder.mPostedContact.setText(mUnitList.get(i).getContact());
        unitListViewHolder.mDocumentID.setText(mUnitList.get(i).getDocument_ID());
        unitListViewHolder.mPreferredGender.setText(mUnitList.get(i).getPreferred_gender());
        unitListViewHolder.mPreferredProfession.setText(mUnitList.get(i).getPreferred_profession());
        Glide.with(mContext)
                .load(mUnit.getImage_URL())
                .centerCrop()
                .into(unitListViewHolder.room_image);

        unitListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewSelectedUnitActivity.class);
                intent.putExtra("usertype", mUnit.getPosted_as());
                intent.putExtra("propertytype", mUnit.getProperty_type());
                intent.putExtra("roomprice", mUnit.getRoom_price());
                intent.putExtra("roomsstate", mUnit.getRoom_state());
                intent.putExtra("roomtitle", mUnit.getRoom_title());
                intent.putExtra("postedby", mUnit.getPosted_by());
                intent.putExtra("postedcontact", mUnit.getContact());
                intent.putExtra("roomimage", mUnit.getImage_URL());
                intent.putExtra("documentid", mUnit.getDocument_ID());
                intent.putExtra("preferredgender", mUnit.getPreferred_gender());
                intent.putExtra("preferredprofession", mUnit.getPreferred_profession());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUnitList.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
