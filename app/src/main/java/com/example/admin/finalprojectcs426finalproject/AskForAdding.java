package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.finalprojectcs426finalproject.Cart;
import com.example.admin.finalprojectcs426finalproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AskForAdding extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_for_adding);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.5),(int)(height*.5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        EditText editText = (EditText)findViewById(R.id.amount);
        editText.setSelection(editText.getText().length());
    }

    public void add(View view) {
        EditText editText = (EditText)findViewById(R.id.amount);
        editText.setText(Integer.toString(Integer.valueOf(editText.getText().toString())+1));
        editText.setSelection(editText.getText().length());
    }

    public void remove(View view) {
        EditText editText = (EditText)findViewById(R.id.amount);
        if (Integer.valueOf(editText.getText().toString())<=1)
            return;
        editText.setText(Integer.toString(Integer.valueOf(editText.getText().toString())-1));
        editText.setSelection(editText.getText().length());
    }

    public void add_success(View view) {
        Cart cart = new Cart();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        cart.add(id,Integer.valueOf(((EditText)findViewById(R.id.amount)).getText().toString()));
        Intent intent1 = new Intent(this,AskAfterAdding.class);
        startActivity(intent1);
        finish();
    }
}