package com.example.dghan.androidmarketapp.Main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.dghan.androidmarketapp.R;

public class Navigation extends LinearLayout {

    public Navigation(Context context) {
        super(context);
        init();
    }

    public Navigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Navigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Navigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    void init()
    {
        inflate(getContext(), R.layout.navigation_layout, this);

    }
}
