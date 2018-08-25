package com.example.admin.finalprojectcs426finalproject;

public class Upload {
    String mID;
    String mImageURL;

    public Upload() {
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public Upload(String mID, String mImageURL) {

        this.mID = mID;
        this.mImageURL = mImageURL;
    }
}
