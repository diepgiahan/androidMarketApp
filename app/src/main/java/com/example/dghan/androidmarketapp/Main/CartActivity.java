package com.example.dghan.androidmarketapp.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dghan.androidmarketapp.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartActivity extends Fragment {
    ArrayList<Products> incartArr=new ArrayList<>();
    ArrayList<Products> relatedProducts=new ArrayList<>();
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.activity_cart,container,false);
        textView=(TextView)view.findViewById(R.id.totalcost);
        //init Arr
        initArr();

        //Init cart List
        RecyclerView cartList=(RecyclerView)view.findViewById(R.id.cartList);
        CartRecycleViewAdapter cartListAdapter=new CartRecycleViewAdapter(getContext(),R.layout.card_row_layout,incartArr,textView);

        LinearLayoutManager cartListLayoutManager=new LinearLayoutManager(getContext());
        cartListLayoutManager.setOrientation(LinearLayout.VERTICAL);
        cartList.setLayoutManager(cartListLayoutManager);
        cartList.setAdapter(cartListAdapter);

        return view;
    }

    void initArr()
    {
        incartArr.add(new Products("Jordan 1","100",R.drawable.jd1));
        incartArr.add(new Products("Jordan 3","300",R.drawable.j3));
    }
    void changeTextView(String content)
    {
        textView.setText(content);
    }

}
