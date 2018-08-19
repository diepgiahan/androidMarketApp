package com.example.dghan.androidmarketapp.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dghan.androidmarketapp.R;

public class show_product extends FragmentActivity {
    Products choseProduct;
    TextView name;
    TextView price;
    TextView type;
    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_product);
        //get product
       getProduct();
       img=(ImageView)findViewById(R.id.showproduct_img);
        name=(TextView)findViewById(R.id.showproduct_name);
        price=(TextView)findViewById(R.id.showproduct_price);
        name.setText(choseProduct.getName());
        price.setText(choseProduct.getOldprice());
        img.setImageResource(choseProduct.getRes());

        //Add to cart
        Button addtocart=(Button)findViewById(R.id.showproduct_addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra("puttocart",choseProduct);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        //Contact

    }
    void getProduct()
    {
        Intent intent =getIntent();
        Bundle extra=intent.getExtras();
        choseProduct=(Products)extra.getSerializable("chosenproduct");

    }


}
