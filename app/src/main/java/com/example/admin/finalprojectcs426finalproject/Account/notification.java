package com.example.admin.finalprojectcs426finalproject.Account;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class notification {
    TextView notiNumber;
    String name;
    int currentNumberOfNotification;

    public void increaseNoti(){
        notiNumber.setText(Integer.toString(++currentNumberOfNotification));
    }
    public void decreaseNoti(){
        notiNumber.setText(Integer.toString(--currentNumberOfNotification));
    }
    public notification(){
        notiNumber = null;
        name = "";
    }
    public notification(TextView notiNumber, String name){
        this.notiNumber = notiNumber;
        this.name = name;
        if(name == "chat"){
            currentNumberOfNotification = 0;//get this from database
        } else if(name == "notification"){
            currentNumberOfNotification = 0;//get this from database
        } else if(name == "cart"){
            currentNumberOfNotification = 0;//get this from database
        }
        setNotificationNumber(currentNumberOfNotification);
    }
    public void setNotificationNumber(int n){
        try{
            if(n > 0){
                notiNumber.setVisibility(View.VISIBLE);
                notiNumber.setText(Integer.toString(n));
                currentNumberOfNotification = n; //update
            }
            else {
                notiNumber.setVisibility(View.INVISIBLE);
                currentNumberOfNotification = 0;
            }
        }catch (Exception e){
            e.getMessage();
        }

    }
    public void showNotification(){
/*  open notification popup should be use in mainactivity or this will need to pass some variable -> main activity is better
        if(name == "chat"){
//open chat
        } else if(name == "notification"){
//open notification
        } else if(name == "cart"){
//open cart view
        }
*/
        setNotificationNumber(0);
        currentNumberOfNotification = 0; //update ~ even it is updated in setNotificationNumber
    }
}
