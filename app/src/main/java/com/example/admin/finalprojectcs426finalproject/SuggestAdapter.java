package com.example.admin.finalprojectcs426finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SuggestAdapter extends ArrayAdapter<String> {
    private final Context context;

    public SuggestAdapter(@NonNull Context context, ArrayList<String> suggest) {
        super(context, 0,suggest);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null)
            convertView = newView(position);
        return convertView;
    }

    private View newView(int position){
        String suggest = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.suggest,null);
        TextView textView = ((TextView)view.findViewById(R.id.suggest));
        textView.setText(suggest);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTask searchTask = new SearchTask(context);
                searchTask.search(((TextView)v).getText().toString());
            }
        });
        return view;
    }
}