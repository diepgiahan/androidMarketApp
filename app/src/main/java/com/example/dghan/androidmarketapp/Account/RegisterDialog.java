package com.example.dghan.androidmarketapp.Account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterDialog extends DialogFragment {
    public static String name;
    public int switchDialog = -1;

    public static  RegisterDialog newInstance(String name) {
        RegisterDialog dialog = new RegisterDialog();
        Bundle args = new Bundle();
        args.putString("data", name);
        RegisterDialog.name = name; /***/
        dialog.setArguments(args);
        dialog.setCancelable(true);
        if(dialog.getDialog() != null)
            dialog.getDialog().setCanceledOnTouchOutside(true);
        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dialog_registration, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = getArguments().getString("data", "");
                Toast.makeText(getActivity(), "Getting to Register", Toast.LENGTH_LONG).show();
                register myRegisterDialog;
    }
    private class register extends AppCompatActivity {
        private EditText txtEmailAddress;
        private EditText txtPassword;
        private String email, password;
        private FirebaseAuth firebaseAuth;
        public register() {
            switchDialog = -1;
            setContentView(R.layout.dialog_registration);
            txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegistration);
            txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
            do {
                email = txtEmailAddress.getText().toString();
                password = txtPassword.getText().toString();
                txtPassword.setText("");
            }while(!checkValid());
            firebaseAuth = FirebaseAuth.getInstance();
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
        //login instead switchDialog = 0
        @Override
        public void finalize() {
            getDialog().dismiss();
        }
        public void btnRegistrationUser_Click(View v) {
            final ProgressDialog progressDialog = ProgressDialog.show(register.this, "Please wait...", "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(email, password))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();


                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "Registration successful", Toast.LENGTH_LONG).show();
                                //Intent i = new Intent(register.this, LoginActivity.class);
                                //startActivity(i);
                            }
                            else
                            {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
