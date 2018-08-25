package com.example.admin.finalprojectcs426finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.finalprojectcs426finalproject.Account.User;
import com.example.admin.finalprojectcs426finalproject.Account.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

//import com.example.dghan.androidmarketapp.Account.RegisterDialog.OnDialogListener;

public class AccountActivity extends Activity {// implements OnDialogListener {
    static boolean loggedIn = false;
    static int switchDialog = -1;
    DatabaseReference userRef;
    ImageView avatar;
    Bitmap avatarImage;
    String username_str = "Guest user";
    User currentUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!loggedIn){
            setContentView(R.layout.activity_account_guest);
            guestAccount();
        } else{
            setContentView(R.layout.activity_account_login);
            normalAccount();
        }
    }
    public void onRefresh(){
        Toast.makeText(getApplicationContext(), "Refresh", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void normalAccount(){
        final TextView emailView = (TextView)findViewById(R.id.emailLogin);
        final TextView addressView = (TextView)findViewById(R.id.addressLogin);
        final TextView telView = (TextView)findViewById(R.id.telLogin);
        TextView username = (TextView)findViewById(R.id.username1);

        userRef= FirebaseDatabase.getInstance().getReference("USERS");
        //currentUser.setCurrentUser();//no need?
        UserInfo userInfo= new UserInfo(currentUser.UserId,currentUser.getUserName(),currentUser.email);
        String child=userRef.push().getKey();
        userRef.child(child).setValue(userInfo);


        //@todo add changepassword text view..
        avatar = (ImageView)findViewById(R.id.avatar1);
        avatar.setImageResource(R.drawable.man); // saved image
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // access abcd -> changeAvatar(file);
                changeAvatar();
                Toast.makeText(getApplicationContext(), "Pressed to change Avatar", Toast.LENGTH_LONG).show();
            }
        });
        username.setText(username_str);
        emailView.setText(currentUser.getEmail());

        // @todo add buy history
        // @todo add search history

        Button btn_logout = (Button)findViewById(R.id.btnLogout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Pressed Logout", Toast.LENGTH_LONG).show();
                username_str = "Guest user";
//                    avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);// this will be function by the prev...
                // refresh activity
                loggedIn = false;
                onRefresh();
            }
        });
    }
    public void guestAccount(){
        loggedIn = false;
        avatar = (ImageView)findViewById(R.id.avatar);
        avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        avatar.setImageBitmap(avatarImage);
        TextView username = (TextView)findViewById(R.id.username);
        username.setText(username_str);
        Button btn_login = (Button)findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog();
                loop();
            }
        });
        Button btn_register = (Button)findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDialog();
                loop();
            }
        });
        // @todo add search history
    }
    public void loop(){
        Toast.makeText(this, valueOf(switchDialog), Toast.LENGTH_LONG).show();
        switch (switchDialog) {
            case 0: loginDialog();  break;
            case 1: registerDialog();   break;
            case -2: //sorry for being mean but this is too late to code..
                currentUser.changePassword();
                break;
        }
        if(loggedIn)  onRefresh();
    }
    @SuppressLint("ClickableViewAccessibility")
    public void loginDialog(){
        switchDialog = -1;
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.dialog_login);
        loginDialog.setCanceledOnTouchOutside(true);

        final EditText emailLogin = (EditText) findViewById(R.id.loginEmail);
        final EditText passwordLogin = (EditText) findViewById(R.id.loginPassword);
        TextView loginProblem = (TextView) findViewById(R.id.loginProblem);
        loginProblem.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                loginDialog.dismiss();
                currentUser.changePassword();
                return false;
            }
        });
        TextView registerInstead = (TextView) findViewById(R.id.registerInstead);
        registerInstead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switchDialog = 1;
                loggedIn = false;
                loginDialog.dismiss();
                return false;
            }
        });
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(getApplicationContext(), "Please wait...", "Proccessing...", true);
                loggedIn = currentUser.login(emailLogin.getText().toString(), passwordLogin.getText().toString());
                progressDialog.dismiss();
                if(!loggedIn){
                    Toast.makeText(getApplicationContext(), "Invalid combination of email and password", Toast.LENGTH_LONG).show();
                }
                loginDialog.dismiss();
            }
        });
        loginDialog.show();
    }
    public void registerDialog(){
        switchDialog = -1;
        final Dialog registerDialog = new Dialog(getApplicationContext());
        registerDialog.setContentView(R.layout.dialog_registration);
        registerDialog.setCanceledOnTouchOutside(true);

        final EditText txtEmailAddress = (EditText) findViewById(R.id.registerEmail);
        final EditText txtPassword = (EditText) findViewById(R.id.registerPassword);

        TextView loginInstead = (TextView) findViewById(R.id.loginInstead);
        loginInstead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switchDialog = 0;
                loggedIn = false;
                registerDialog.dismiss();
                return false;
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btnRegistrationUser);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedIn = currentUser.register(txtEmailAddress.getText().toString(), txtPassword.getText().toString());
                if(!loggedIn)
                    Toast.makeText(getApplicationContext(), "Cannot sign up", Toast.LENGTH_LONG).show();
                registerDialog.dismiss();
            }
        });
        registerDialog.show();
    }
    public boolean changeAvatar(){
        // @todo changeAvatar
        //avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        return true; // work fine
    }

}
