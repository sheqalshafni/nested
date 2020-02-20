package com.example.nested.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nested.R;
import com.example.nested.models.PaymentHistory;
import com.example.nested.viewholders.PaymentHistoryViewHolder;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentHistoryViewHolder> {

    private List<PaymentHistory> mPaymentHistoryList;
    private Context mContext;

    public PaymentListAdapter(List<PaymentHistory> mPaymentHistoryList, Context mContext) {
        this.mPaymentHistoryList = mPaymentHistoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PaymentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.content_paymenthistory_list, viewGroup, false);

        return new PaymentHistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryViewHolder paymentHistoryViewHolder, int i) {

        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistoryViewHolder.txtReference.setText(paymentHistory.getRecipient_reference());
        paymentHistoryViewHolder.txtPayment.setText(paymentHistory.getRoom_price());
    }

    @Override
    public int getItemCount() {
        return mPaymentHistoryList.size();
    }
}
