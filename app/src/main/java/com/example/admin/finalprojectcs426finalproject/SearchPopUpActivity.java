package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchPopUpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width),(int)(height*.95));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);
        ImageView imageView = (ImageView)findViewById(R.id.close);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final EditText search_edit_frame = (EditText)findViewById(R.id.search_edit_frame);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SearchTask searchTask = new SearchTask(SearchPopUpActivity.this);
                String string = ((EditText)findViewById(R.id.search_edit_frame)).getText().toString();
                ArrayList<String> suggest = new ArrayList<String>();
                if (searchTask.suggest(string)==null || string.length()==0)
                    suggest.add("");
                else suggest = (ArrayList)searchTask.suggest(string);
                ListView listView = (ListView)findViewById(R.id.SuggestContainer);
                SuggestAdapter suggestAdapter = new SuggestAdapter(SearchPopUpActivity.this,suggest);
                listView.setAdapter(suggestAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        search_edit_frame.addTextChangedListener(textWatcher);
    }
}