package com.example.dghan.androidmarketapp.Account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.Main.AccountActivity;
import com.example.dghan.androidmarketapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterDialog extends DialogFragment {
    public static String name;
    public int switchDialog = -1;
    private EditText txtEmailAddress;
    private EditText txtPassword;
    private String email, password;
    private FirebaseAuth firebaseAuth;

    public static  RegisterDialog newInstance(String name) {
        RegisterDialog dialog = new RegisterDialog();
        Bundle args = new Bundle();
        //args.putString("data", name);
        //RegisterDialog.name = name; /***/
        dialog.setArguments(args);
        dialog.setCancelable(true);
        if(dialog.getDialog() != null)
            dialog.getDialog().setCanceledOnTouchOutside(true);
        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Register", Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.dialog_registration, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //String data = getArguments().getString("data", "");
        register(view);
    }
    public void register(View view) {
        switchDialog = -1;
        //setContentView(R.layout.dialog_registration);
        txtEmailAddress = (EditText) view.findViewById(R.id.registerEmail);
        txtPassword = (EditText) view.findViewById(R.id.registerPassword);

        TextView loginInstead = (TextView) view.findViewById(R.id.loginInstead);
        loginInstead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switchDialog = 0;
                Intent intent = new Intent();
                intent.putExtra("switchDialog", 0);
                intent.putExtra("loggedIn", false);
                getTargetFragment().onActivityResult(getTargetRequestCode(), 1651077, intent);
                getDialog().dismiss();
                return false;
            }
        });
        Button btnRegister = (Button) view.findViewById(R.id.btnRegistrationUser);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUserRegister_Click(view);
            }
        });
    }
    public void btnUserRegister_Click(View v) {
        do {
            email = txtEmailAddress.getText().toString();
            password = txtPassword.getText().toString();
            txtPassword.setText("");
        }while(!checkValid());
        firebaseAuth = FirebaseAuth.getInstance();

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Please wait...", "Processing...", true);
        (firebaseAuth.createUserWithEmailAndPassword(email, password))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.putExtra("switchDialog", -1);
                            intent.putExtra("loggedIn", true);
                            getTargetFragment().onActivityResult(getTargetRequestCode(), 1651077, intent);
                            getDialog().dismiss();
                            //Intent i = new Intent(register.this, LoginActivity.class);
                            //startActivity(i);
                        }else{
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public boolean checkValid(){
        /** Password should be less than 15 and more than 8 characters in length.
         * Password should contain at least one upper case and one lower case alphabet.
         * Password should contain at least one number.
         * Password should contain at least one special character.
         http://techdive.in/java/java-password-validation   */
        if(!(email.matches("@") && email.matches("."))){
            Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.length() > 15 || password.length() < 8) {
            Toast.makeText(getActivity(), "Invalid password: length between 8 to 15", Toast.LENGTH_LONG).show();
            return false;
        }
            /*if (password.indexOf(userName) > -1) {
                System.out.println("Password Should not be same as user name");
                return false;
            }*/
        String upperCaseChars = "(.*[A-Z].*)";
        if(!password.matches(upperCaseChars )) {
            Toast.makeText(getActivity(), "Invalid password: must have uppercase", Toast.LENGTH_LONG).show();
            return false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if(!password.matches(lowerCaseChars )) {
            Toast.makeText(getActivity(), "Invalid password: must have lowercase", Toast.LENGTH_LONG).show();
            return false;
        }
        String numbers = "(.*[0-9].*)";
        if(!password.matches(numbers )) {
            Toast.makeText(getActivity(), "Invalid password: must have number", Toast.LENGTH_LONG).show();
            return false;
        }
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if(!password.matches(specialChars )) {
            Toast.makeText(getActivity(), "Invalid password: must have special character", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
