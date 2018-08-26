package com.example.dghan.androidmarketapp.Account;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private int sec = 19980607; // just easy one
    public String UserId, UserName, email;
    private FirebaseAuth firebaseAuth;
    boolean loggedIn = false;

    public User(String userId, String userName, String email) {
        UserId = userId;
        UserName = userName;
        this.email = email;
    }

    public User(){
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public boolean login(String emailLogin, String passwordLogin){
        (firebaseAuth.signInWithEmailAndPassword(emailLogin, passwordLogin))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loggedIn = task.isSuccessful();
                    }
                });

        return loggedIn;
    }

    public void setCurrentUser() {
        UserName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }

    public String getUserId() {
        return UserId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return UserName;
    }

    /*private static final int CHAR_LENGTH = 75;
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*() ";
    public User(){
        sec = sec + randomInt(1000) % 128;
        passCode = generateRandomString();
    }


    //encrypt this
    public String passGenerate(String original){
        StringBuffer answer = new StringBuffer();
        return answer.toString();
    }

    public String passReverse(String input){
        StringBuffer answer = new StringBuffer();
        return answer.toString();
    }

    private String generateRandomString(){
        int randomN = randomInt(10);
        StringBuffer randomStr = new StringBuffer();
        for(int i=0; i< randomN; i++){
            int number = randomInt(CHAR_LENGTH);
            char ch = CHAR_LIST.charAt(number);
            randomStr.append(ch);
        }
        return randomStr.toString();
    }
    private int randomInt(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }*/
}