package com.example.dghan.androidmarketapp.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.R;


public class AccountActivity extends Fragment {
    boolean switchActivity = false;
    View view;
    ImageView avatar;
    TextView username;
    Bitmap avatarImage;
    String username_str = "Guest user",
            userEmail_str, userAddress_str, userTel_str;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(username_str == "Guest user"){
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_account_guest,container,false);
            guestAccount();
        }
        else{
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_account_login,container,false);
            normalAccount();
        }
        if(switchActivity)  onRefresh();
        return view;
    }
    public void onRefresh(){
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(currentFragment);
        fragmentTransaction.attach(currentFragment);
        fragmentTransaction.commit();
    }
    public void normalAccount(){
        switchActivity = false;
        final TextView emailView = (TextView)view.findViewById(R.id.emailLogin);
        final TextView addressView = (TextView)view.findViewById(R.id.addressLogin);
        final TextView telView = (TextView)view.findViewById(R.id.telLogin);

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
                switchActivity = true;
            }
        });
    }
    public void guestAccount(){ // return logged in (or successfully registered
        switchActivity = false;
        final int[] switchDialog = {-1};
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
                switchDialog[0] = loginDialog();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchDialog[0] = registerDialog();
            }
        });
        while(switchDialog[0] != -1){
            if(switchDialog[0] == 0)
                loginDialog();
            if(switchDialog[0] == 1)
                registerDialog();
        }
        // @todo add search history
    }
    public int loginDialog(){
        FragmentManager fm = getFragmentManager();//getSupportFragmentManager();
        com.example.dghan.androidmarketapp.Account.LoginDialog loginDialog = com.example.dghan.androidmarketapp.Account.LoginDialog.newInstance("login");
        loginDialog.show(fm, null);
        return loginDialog.switchDialog;
    }
    public int registerDialog(){
        FragmentManager fm = getFragmentManager();//getSupportFragmentManager();
        com.example.dghan.androidmarketapp.Account.RegisterDialog registerDialog = com.example.dghan.androidmarketapp.Account.RegisterDialog.newInstance("register");
        registerDialog.show(fm, null);
        return registerDialog.switchDialog;
    }
    public boolean changeAvatar(){
        // @todo changeAvatar
        //avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        return true; // work fine
    }
}
