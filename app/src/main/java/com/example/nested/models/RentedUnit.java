package com.example.nested.models;

import com.google.gson.annotations.SerializedName;

public class RentedUnit {

    @SerializedName("Document_ID")
    private String Document_ID;
    @SerializedName("User_ID")
    private String User_ID;
    @SerializedName("Landlord_ID")
    private String  Landlord_UID;
    @SerializedName("Room_price")
    private String Room_price;
    @SerializedName("Tenant_name")
    private String Tenant_name;
    @SerializedName("StartRent")
    private String StartRent;
    @SerializedName("EndRent")
    private String EndRent;
    @SerializedName("Room_title")
    private String Room_title;
    @SerializedName("Current_docID")
    private String Current_docID;

    public RentedUnit() {
    }

    public RentedUnit(String document_ID, String user_ID, String landlord_UID, String room_price, String tenant_name, String startRent, String endRent, String room_title, String current_docID) {
        Document_ID = document_ID;
        User_ID = user_ID;
        Landlord_UID = landlord_UID;
        Room_price = room_price;
        Tenant_name = tenant_name;
        StartRent = startRent;
        EndRent = endRent;
        Room_title = room_title;
        Current_docID = current_docID;
    }

    public String getDocument_ID() {
        return Document_ID;
    }

    public void setDocument_ID(String document_ID) {
        Document_ID = document_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getLandlord_UID() {
        return Landlord_UID;
    }

    public void setLandlord_UID(String landlord_UID) {
        Landlord_UID = landlord_UID;
    }

    public String getRoom_price() {
        return Room_price;
    }

    public void setRoom_price(String room_price) {
        Room_price = room_price;
    }

    public String getTenant_name() {
        return Tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        Tenant_name = tenant_name;
    }

    public String getStartRent() {
        return StartRent;
    }

    public void setStartRent(String startRent) {
        StartRent = startRent;
    }

    public String getEndRent() {
        return EndRent;
    }

    public void setEndRent(String endRent) {
        EndRent = endRent;
    }

    public String getRoom_title() {
        return Room_title;
    }

    public void setRoom_title(String room_title) {
        Room_title = room_title;
    }

    public String getCurrent_docID() {
        return Current_docID;
    }

    public void setCurrent_docID(String current_docID) {
        Current_docID = current_docID;
    }
}
