package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailProductActivity extends Activity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);
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
        Intent intent = getIntent();
        Database database = new Database();
        final Products product = database.getProductById(intent.getIntExtra("product",0));
        //((ImageView)findViewById(R.id.image)).setImageResource();
        //saler image
        ((TextView)findViewById(R.id.saler)).setText(database.getSalerByProduct(product));
        ((TextView)findViewById(R.id.old_price)).setText(product.getOldprice());
        ((TextView)findViewById(R.id.new_price)).setText(product.getNewprice());
        ((TextView)findViewById(R.id.description)).setText(product.getDescription());
        ((TextView)findViewById(R.id.buy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                Intent intent1 = new Intent(context,AskForAdding.class);
                context.startActivity(intent1);
                //cart.add(product.getId(),1);

            }
        });
    }
}