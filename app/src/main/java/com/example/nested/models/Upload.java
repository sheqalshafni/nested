package com.example.nested.models;

public class Upload {

    private String mImageURL;

    public Upload(){
        //empty constructor
    }

    public Upload(String imageURL){
        mImageURL = imageURL;
    }

    public String getmImageURL(){
        return mImageURL;
    }

    public void setmImageURL(String imageURL){
        mImageURL = imageURL;
    }
}
