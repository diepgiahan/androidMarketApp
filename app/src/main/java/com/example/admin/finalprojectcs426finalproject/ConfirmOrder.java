package com.example.admin.finalprojectcs426finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmOrder extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        ((TextView)findViewById(R.id.confirmorder)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.clearAmount();
                cart.clearProducts();
                finish();
            }
        });
    }
}