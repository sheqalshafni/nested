package com.example.nested.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nested.R;

public class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView txtReference, txtPayment;

    public PaymentHistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txtReference = itemView.findViewById(R.id.txtReference);
        txtPayment = itemView.findViewById(R.id.txtAmount);


    }
}
