package com.example.nested.models;

import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

public class PaymentHistory {

    @SerializedName("Invoice_no")
    private String Invoice_no;
    @SerializedName("Paid_with")
    private String Paid_with;
    @SerializedName("Recipient_reference")
    private String Recipient_reference;
    @SerializedName("Room_price")
    private String Room_price;
    @SerializedName("Room_title")
    private String Room_title;
    @SerializedName("Tenant_name")
    private String Tenant_name;
    @SerializedName("User_ID")
    private String User_ID;

    public PaymentHistory() {
    }

    public PaymentHistory(String invoice_no, String paid_with, String recipient_reference, String room_price, String room_title, String tenant_name, String user_ID) {
        Invoice_no = invoice_no;
        Paid_with = paid_with;
        Recipient_reference = recipient_reference;
        Room_price = room_price;
        Room_title = room_title;
        Tenant_name = tenant_name;
        User_ID = user_ID;
    }

    public String getInvoice_no() {
        return Invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        Invoice_no = invoice_no;
    }

    public String getPaid_with() {
        return Paid_with;
    }

    public void setPaid_with(String paid_with) {
        Paid_with = paid_with;
    }

    public String getRecipient_reference() {
        return Recipient_reference;
    }

    public void setRecipient_reference(String recipient_reference) {
        Recipient_reference = recipient_reference;
    }

    public String getRoom_price() {
        return Room_price;
    }

    public void setRoom_price(String room_price) {
        Room_price = room_price;
    }

    public String getRoom_title() {
        return Room_title;
    }

    public void setRoom_title(String room_title) {
        Room_title = room_title;
    }

    public String getTenant_name() {
        return Tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        Tenant_name = tenant_name;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }
}
