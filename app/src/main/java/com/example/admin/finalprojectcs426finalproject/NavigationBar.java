package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NavigationBar extends LinearLayout{
    ImageView homee, noti, account;
    Context currentContext;
    public NavigationBar(Context context) {
        super(context);
        currentContext = context;
        init();
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        currentContext = context;
        init();
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentContext = context;
        init();
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        currentContext = context;
        init();
    }

    void init() {
        inflate(getContext(), R.layout.navigation_layout,this);
        homee = findViewById(R.id.homebtn);
        account = findViewById(R.id.main_account);
        noti = findViewById(R.id.notification);

        homee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                ((Activity)getContext()).finish();
                getContext().startActivity(intent);
            }
        });

        noti.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        account.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AccountActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
