package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CartPopUpActivity extends Activity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_pop_up);
        context = this;
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width),(int)(height*.95));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);*/
        final Cart cart = new Cart();
        ArrayList<Integer> products = cart.getProducts();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.view);
        CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(this,products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //((TextView)findViewById(R.id.total)).setText(Integer.toString());
        ((TextView)findViewById(R.id.buy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.size()==0) return;
                Intent intent = new Intent(context,ConfirmOrder.class);
                context.startActivity(intent);
            }
        });
    }

    public void close(View view) {
        finish();
    }
}