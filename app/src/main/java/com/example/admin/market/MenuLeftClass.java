package com.example.admin.market;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuLeftClass extends LinearLayout {
    Button homebtn;
    Button cartbtn;
    Button accountbtn;
    public MenuLeftClass(Context context) {
        super(context);
        init();
    }

    public MenuLeftClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuLeftClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MenuLeftClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init()
    {
        inflate(getContext(),R.layout.menu_left_drawer,this);



    }
}
