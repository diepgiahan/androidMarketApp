package com.example.admin.finalprojectcs426finalproject.Account;

import android.view.View;
import android.widget.TextView;

public class notification {
    TextView notiNumber;
    String name;
    int currentNumberOfNotification;
    public notification(TextView notiNumber, String name){
        this.notiNumber = notiNumber;
        this.name = name;
        if(name == "chat"){
            currentNumberOfNotification = 0;//get this from database
        }else if(name == "notification"){
            currentNumberOfNotification = 0;//get this from database
        }
        setNotificationNumber(currentNumberOfNotification);
    }
    public void setNotificationNumber(int n){
        if(n > 0){
            notiNumber.setVisibility(View.VISIBLE);
            notiNumber.setText(Integer.toString(n));
        }
        else notiNumber.setVisibility(View.INVISIBLE);
    }
    public void showNotification(){
        if(name == "chat"){

        }else if(name == "notification"){

        }
        setNotificationNumber(0);
    }
}
