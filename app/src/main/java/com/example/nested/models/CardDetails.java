package com.example.nested.models;

import com.google.gson.annotations.SerializedName;

public class CardDetails {

    @SerializedName("cardnumber")
    private String cardnumber;
    @SerializedName("cardexpirydate")
    private String cardexpirydate;
    @SerializedName("cardtypeimg")
    private String cardtypeimg;
    @SerializedName("User_ID")
    private String User_ID;

    public CardDetails() {
    }

    public CardDetails(String cardnumber, String cardexpirydate, String cardtypeimg, String user_ID) {
        this.cardnumber = cardnumber;
        this.cardexpirydate = cardexpirydate;
        this.cardtypeimg = cardtypeimg;
        this.User_ID = user_ID;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardexpirydate() {
        return cardexpirydate;
    }

    public void setCardexpirydate(String cardexpirydate) {
        this.cardexpirydate = cardexpirydate;
    }

    public String getCardtypeimg() {
        return cardtypeimg;
    }

    public void setCardtypeimg(String cardtypeimg) {
        this.cardtypeimg = cardtypeimg;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

}
