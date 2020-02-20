package com.example.nested.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Age")
    private String Age;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Contact")
    private String Contact;
    @SerializedName("Gender")
    private String Gender;
    @SerializedName("NRIC")
    private String NRIC;
    @SerializedName("Credit_Card")
    private String Credit_Card;
    @SerializedName("imageURL")
    private String imageURL;

    public User() {
    }

    public User(String age, String name, String contact, String gender, String NRIC, String credit_Card, String imageURL) {
        Age = age;
        Name = name;
        Contact = contact;
        Gender = gender;
        this.NRIC = NRIC;
        Credit_Card = credit_Card;
        this.imageURL = imageURL;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getCredit_Card() {
        return Credit_Card;
    }

    public void setCredit_Card(String credit_Card) {
        Credit_Card = credit_Card;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
