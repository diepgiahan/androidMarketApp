package com.example.dghan.androidmarketapp.UserDialog;

import java.util.Random;

public class User {
    private int sec = 19980607; // just easy one
    private String email, passCode;
    private static final int CHAR_LENGTH = 75;
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*() ";
    public User(){
        sec = sec + randomInt(1000) % 128;
        passCode = generateRandomString();
    }
    /**encrypt this*/
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
    }
}
