package com.example.dghan.androidmarketapp.Main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.Account.ChangePasswordDialog;
import com.example.dghan.androidmarketapp.Account.LoginDialog;
//import com.example.dghan.androidmarketapp.Account.RegisterDialog.OnDialogListener;
import com.example.dghan.androidmarketapp.Account.RegisterDialog;
import com.example.dghan.androidmarketapp.R;

import static java.lang.String.valueOf;

public class AccountActivity extends Fragment{// implements OnDialogListener {
    static boolean loggedIn = false;
    static int switchDialog = -1;
    View view;
    ImageView avatar;
    TextView username;
    Bitmap avatarImage;
    String username_str = "Guest user",
            userEmail_str, userAddress_str, userTel_str;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!loggedIn){
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_account_guest,container,false);
            guestAccount();
        }
        else{
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_account_login,container,false);
            Toast.makeText(getContext(), "Logged in", Toast.LENGTH_LONG).show();
            normalAccount();
        }
        return view;
    }
    public void onRefresh(){
        Toast.makeText(getContext(), "Refresh", Toast.LENGTH_LONG).show();
        getActivity().recreate();
    }
    public void normalAccount(){
        final TextView emailView = (TextView)view.findViewById(R.id.emailLogin);
        final TextView addressView = (TextView)view.findViewById(R.id.addressLogin);
        final TextView telView = (TextView)view.findViewById(R.id.telLogin);

        //@todo add changepassword text view..
        //@todo generate user id
        avatar = (ImageView)view.findViewById(R.id.avatar1);
        avatar.setImageResource(R.drawable.man); // saved image
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // access abcd -> changeAvatar(file);
                changeAvatar();
                Toast.makeText(getContext(), "Pressed to change Avatar", Toast.LENGTH_LONG).show();
            }
        });
        username = (TextView)view.findViewById(R.id.username1);
        username.setText(username_str);

        // @todo add buy history
        // @todo add search history

        Button btn_logout = (Button)view.findViewById(R.id.btnLogout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Pressed Logout", Toast.LENGTH_LONG).show();
                username_str = "Guest user";
//                    avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);// this will be function by the prev...
                // refresh activity
                loggedIn = false;
                onRefresh();
            }
        });
    }
    public void guestAccount(){ // return logged in (or successfully registered
        loggedIn = false;
        avatar = (ImageView)view.findViewById(R.id.avatar);
        //avatar.setImageResource(R.drawable.man);
        avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        avatar.setImageBitmap(avatarImage);
        username = (TextView)view.findViewById(R.id.username);
        username.setText(username_str);
        Button btn_login = (Button)view.findViewById(R.id.btnLogin);
        Button btn_register = (Button)view.findViewById(R.id.btnRegister);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDialog();
            }
        });
        // @todo add search history
    }
    public void loop(){
        Toast.makeText(getContext(), valueOf(switchDialog), Toast.LENGTH_LONG).show();
        switch (switchDialog) {
            case 0: loginDialog();  break;
            case 1: registerDialog();   break;
            case -2: //sorry for being mean but this is too late to code..
                ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Need help?", "Contact me at my fake\nphone number 01668 666 666", true);
                progressDialog.setCanceledOnTouchOutside(true);
                break;
        }
        if(loggedIn)  onRefresh();
    }
    public void changePasswordDialog(){
        DialogFragment changePasswordDialog = new ChangePasswordDialog();
        changePasswordDialog.setTargetFragment(this, 1651077);
        changePasswordDialog.show(getFragmentManager(), null);
    }
    public void loginDialog(){
        DialogFragment loginDialog = new LoginDialog();
        loginDialog.setTargetFragment(this, 1651077);
        loginDialog.show(getFragmentManager(), null);
    }
    public void registerDialog(){
        DialogFragment registerDialog = new RegisterDialog();
        registerDialog.setTargetFragment(this, 1651077);
        registerDialog.show(getFragmentManager(), null);
    }
    public boolean changeAvatar(){
        // @todo changeAvatar
        //avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        return true; // work fine
    }
    /*@Override
    public void onDialogResult(int switchDialog, boolean loggedIn) {
        Toast.makeText(getContext(), "got here", Toast.LENGTH_SHORT).show();
        this.switchDialog = switchDialog;
        this.loggedIn = loggedIn;
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getContext(), "got here", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
        switchDialog = data.getIntExtra("switchDialog", -1);
        loggedIn = data.getBooleanExtra("loggedIn", false);
        loop();
    }
}
