package com.example.nested.profileactivities.accountactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nested.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewCreditCardActivity extends AppCompatActivity {

    @BindView(R.id.cardNumber)
    TextView txtCardNumber;
    @BindView(R.id.expiryDate)
    TextView expiryDate;
    @BindView(R.id.cardtype_image)
    ImageView cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_credit_card);

        ButterKnife.bind(this);

    }
}
