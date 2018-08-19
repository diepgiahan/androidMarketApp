package com.example.dghan.androidmarketapp.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dghan.androidmarketapp.R;

import java.util.ArrayList;

public class CartActivity extends Fragment {
    ArrayList<Products> incartArr=new ArrayList<>();
    ArrayList<Products> relatedProducts=new ArrayList<>();
    TextView textView;
    RecyclerView cartList;
    CartRecycleViewAdapter cartListAdapter;
    //ArrayList<Products>puttocart=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.activity_cart,container,false);



        //init Arr
        initArr();
        incartArr= HomeActivity.getPuttoCart();
        textView=(TextView)view.findViewById(R.id.totalcost);
        int total=getFirstTotalPrice();
        textView.setText(Integer.toString(total));
        //Init cart List
        cartList=(RecyclerView)view.findViewById(R.id.cartList);
        cartListAdapter=new CartRecycleViewAdapter(getContext(), R.layout.card_row_layout, incartArr, textView, new CartRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent showproduct=new Intent(getActivity(),show_product.class);
                Bundle bundle=new Bundle();
                showproduct.putExtra("chosenproduct",item);
                startActivity(showproduct);
            }
        });

        LinearLayoutManager cartListLayoutManager=new LinearLayoutManager(getContext());
        cartListLayoutManager.setOrientation(LinearLayout.VERTICAL);
        cartList.setLayoutManager(cartListLayoutManager);
        cartList.setAdapter(cartListAdapter);
        cartList.setItemAnimator(new DefaultItemAnimator());
        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int choice=cartListAdapter.getItemSelected(item);
        if(choice==0)
        {
            Products chosenProduct=cartListAdapter.getChosenProduct();
            incartArr.remove(chosenProduct);
            cartListAdapter.notifyDataSetChanged();
            int recenttotal=Integer.parseInt(textView.getText().toString());
            textView.setText(Integer.toString(recenttotal-Integer.parseInt(chosenProduct.oldprice)*chosenProduct.getQuantity()));

        }
        // cartList.getAdapter();//   Toast.makeText(getContext(), "ONCONTEXTITEM:"+Integer.toString(cartListAdapter.getItemSelected(item)), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    void initArr()
    {
        //  incartArr.add(new Products("Jordan 1","100",R.drawable.jd1));
        //incartArr.add(new Products("Jordan 3","300",R.drawable.j3));
    }
    void changeTextView(String content)
    {
        textView.setText(content);
    }

    int getFirstTotalPrice()
    {
        int price=0;
        for (int i=0;i<incartArr.size();i++)
        {
            price+=Integer.valueOf(incartArr.get(i).oldprice);
        }
        return price;
    }
}
