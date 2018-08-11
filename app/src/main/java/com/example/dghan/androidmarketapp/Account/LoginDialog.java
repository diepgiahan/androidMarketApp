package com.example.dghan.androidmarketapp.Account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginDialog extends DialogFragment {
    public static String name;
    public int switchDialog = -1;
    private EditText emailLogin, passwordLogin;
    private FirebaseAuth firebaseAuth;

    public static  LoginDialog newInstance(String name) {
        LoginDialog dialog = new LoginDialog();
        Bundle args = new Bundle();
        //args.putString("data", name);
        //LoginDialog.name = name; /***/
        dialog.setArguments(args);
        dialog.setCancelable(true);
        if(dialog.getDialog() != null)
            dialog.getDialog().setCanceledOnTouchOutside(true);
        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_login, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "Getting to Login", Toast.LENGTH_LONG).show();
        login(view);
    }
    public void login(View view) {
        switchDialog = -1;
        emailLogin = (EditText) view.findViewById(R.id.loginEmail);
        passwordLogin = (EditText) view.findViewById(R.id.loginPassword);
        TextView loginProblem = (TextView) view.findViewById(R.id.loginProblem);

        firebaseAuth = FirebaseAuth.getInstance();
        loginProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "change password..", Toast.LENGTH_LONG).show();
            }
        });
        TextView registerInstead = (TextView) view.findViewById(R.id.registerInstead);
        registerInstead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switchDialog = 1;
                getDialog().dismiss();
                return false;
            }
        });
        Button btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUserLogin_Click(view);
            }
        });
    }
    private void btnUserLogin_Click(View view) {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Please wait...", "Proccessing...", true);

        (firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(), passwordLogin.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_LONG).show();
                            //Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                            //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            //startActivity(i);
                            //Intent i = new Intent();
                            //i.putExtra("userEmail", firebaseAuth.getCurrentUser().getEmail());
                            //setResult(1651077, i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

