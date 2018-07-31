package com.example.hhmt.findfunction;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Products> {
    private final Context context;

    public ProductAdapter(@NonNull Context context, ArrayList<Products> products) {
        super(context, 0,products);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
        if (convertView==null)
            convertView = createView(position);
        return convertView;
    }

    private View createView(int position){
        Products product = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.result_view,null);
        ImageView image_view = (ImageView)view.findViewById(R.id.image);
        image_view.setImageResource(R.drawable.ic_launcher_background);
        TextView text_view = (TextView)view.findViewById(R.id.name);
        text_view.setText(product.getName());
        text_view = (TextView)view.findViewById(R.id.price);
        text_view.setText(product.getNewprice());
        text_view = (TextView)view.findViewById(R.id.rate);
        text_view.setText(Double.toString(product.getRate()));
        text_view = (TextView)view.findViewById(R.id.category);
        return view;
    }
}
