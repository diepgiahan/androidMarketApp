package com.example.dghan.androidmarketapp.Main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.example.dghan.androidmarketapp.R;

public class tool extends android.support.v7.widget.Toolbar {
    EditText searchBar;
    boolean searchBarVisible = false;
    public tool(Context context) {
        super(context);
        init();
    }

    public tool(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public tool(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    void init()
    {
        inflate(getContext(),R.layout.customtoolbar,this);
        searchBar=new EditText(getContext());
        searchBar.setHint("Enter a product here . . .");
        ImageButton search=(ImageButton)findViewById(R.id.searchbtn);
        searchBar.setBackgroundResource(R.color.appColor);

        final LinearLayout floatLayout=(LinearLayout)findViewById(R.id.plusLinearLayout);
        floatLayout.addView(searchBar);
        floatLayout.setVisibility(View.INVISIBLE);

        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBarVisible == false)
                    floatLayout.setVisibility(View.VISIBLE);
                else
                    floatLayout.setVisibility(View.INVISIBLE);
                searchBarVisible = !searchBarVisible;
            }
        });
        /* ORIGINAL changed by @Han
        inflate(getContext(),R.layout.customtoolbar,this);
        searchBar=new EditText(getContext());
        searchBar.setHint("Enter your product here . . .");
        ImageButton search=(ImageButton)findViewById(R.id.searchbtn);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout addLayout=(LinearLayout)findViewById(R.id.plusLinearLayout);
                addLayout.addView(searchBar);
               ViewGroup vg = findViewById (R.id.mainLayout);
               vg.invalidate();

            }
        });*/

    }
}
