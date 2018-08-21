package com.example.dghan.androidmarketapp.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dghan.androidmarketapp.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class HomeActivity extends Fragment {
    ArrayList<Products>popularArr=new ArrayList<>();
    ArrayList<Products>suggestedArr=new ArrayList<>();
    static ArrayList<Products>puttoCart=new ArrayList<>();

    public static ArrayList<Products> getPuttoCart() {
        return puttoCart;
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initArr();
        View view=LayoutInflater.from(getContext()).inflate(R.layout.activity_home,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.reycleview);
        RecycleViewAdapter adapter=new RecycleViewAdapter(getContext(), popularArr, R.layout.cardview_layout, new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent showproduct=new Intent(getActivity(),show_product.class);
                Bundle bundle=new Bundle();
               showproduct.putExtra("chosenproduct",item);
                startActivityForResult(showproduct,0);
            }
        });
        //Popular products
        LinearLayoutManager linearLayout=new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);


        return view;
    }

    void initArr()
    {
        popularArr.add(new Products("Jordan 3","300","200", R.drawable.j3));
        popularArr.add(new Products("Jordan 1","250","100", R.drawable.j1));
        popularArr.add(new Products("Jordan 7 Wheat","320","120", R.drawable.jd7wheat));
        popularArr.add(new Products("Jordan 1","200","300", R.drawable.jd1));


//        suggestedArr.add(new Products("Man's Clothings","","",R.drawable.manclothing));
        //      suggestedArr.add(new Products("Woman's Clothings","","",R.drawable.womanclothing));
        //    suggestedArr.add(new Products("Electronic Devices","","",R.drawable.electronicdevice));
        //  suggestedArr.add(new Products("Others","","",R.drawable.otherproducts));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==RESULT_OK)
        {
            puttoCart.add((Products)data.getExtras().getSerializable("puttocart"));


            final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext()).setTitle("Notification").setMessage("Successfully Added To Your Cart");
            final AlertDialog alert = dialog.create();
            alert.show();
// Hide after some seconds
            final Handler handler  = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (alert.isShowing()) {
                        alert.dismiss();
                    }
                }
            };

            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    handler.removeCallbacks(runnable);
                }
            });

            handler.postDelayed(runnable, 5000);
        }

    }
}