package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null)
            convertView = createView(position);
        return convertView;
    }

    private View createView(int position){
        final Products product = getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.result_view,null);
        ImageView image_view = (ImageView)view.findViewById(R.id.image);
        image_view.setImageResource(R.drawable.img);
        TextView text_view = (TextView)view.findViewById(R.id.name);
        text_view.setText(product.getName());
        text_view = (TextView)view.findViewById(R.id.price);
        text_view.setText(product.getNewprice());
        text_view = (TextView)view.findViewById(R.id.rate);
        text_view.setText(Double.toString(product.getRate()));
        text_view = (TextView)view.findViewById(R.id.category);
        text_view.setText(product.getDescription());
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("product",product.getId());
                context.startActivity(intent);
            }
        });
        return view;
    }
}