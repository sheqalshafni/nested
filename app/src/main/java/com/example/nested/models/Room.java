package com.example.nested.models;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("Posted_as")
    private String Posted_as;
    @SerializedName("Property_type")
    private String Property_type;
    @SerializedName("Room_price")
    private String Room_price;
    @SerializedName("Room_state")
    private String Room_state;
    @SerializedName("Image_URL")
    private String Image_URL;
    @SerializedName("Room_title")
    private String Room_title;
    @SerializedName("Posted_by")
    private String Posted_by;
    @SerializedName("Posted_gender")
    private String Posted_gender;
    @SerializedName("User_ID")
    private String User_ID;
    @SerializedName("Document_ID")
    private String Document_ID;
    @SerializedName("Contact")
    private String Contact;

    public Room() {
    }

    public Room(String posted_as, String property_type, String room_price, String room_state, String image_URL, String room_title, String posted_by, String posted_gender, String user_ID, String document_ID, String contact) {
        Posted_as = posted_as;
        Property_type = property_type;
        Room_price = room_price;
        Room_state = room_state;
        Image_URL = image_URL;
        Room_title = room_title;
        Posted_by = posted_by;
        Posted_gender = posted_gender;
        User_ID = user_ID;
        Document_ID = document_ID;
        Contact = contact;
    }

    public String getPosted_as() {
        return Posted_as;
    }

    public void setPosted_as(String posted_as) {
        Posted_as = posted_as;
    }

    public String getProperty_type() {
        return Property_type;
    }

    public void setProperty_type(String property_type) {
        Property_type = property_type;
    }

    public String getRoom_price() {
        return Room_price;
    }

    public void setRoom_price(String room_price) {
        Room_price = room_price;
    }

    public String getRoom_state() {
        return Room_state;
    }

    public void setRoom_state(String room_state) {
        Room_state = room_state;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getRoom_title() {
        return Room_title;
    }

    public void setRoom_title(String room_title) {
        Room_title = room_title;
    }

    public String getPosted_by() {
        return Posted_by;
    }

    public void setPosted_by(String posted_by) {
        Posted_by = posted_by;
    }

    public String getPosted_gender() {
        return Posted_gender;
    }

    public void setPosted_gender(String posted_gender) {
        Posted_gender = posted_gender;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getDocument_ID() {
        return Document_ID;
    }

    public void setDocument_ID(String document_ID) {
        Document_ID = document_ID;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
