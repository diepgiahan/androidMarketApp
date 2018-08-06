package com.example.dghan.androidmarketapp.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.dghan.androidmarketapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {
    ArrayList<Products>popularArr=new ArrayList<>();
    ArrayList<Products>suggestedArr=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initArr();
        View view=LayoutInflater.from(getContext()).inflate(R.layout.activity_home,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.reycleview);
        RecycleViewAdapter adapter=new RecycleViewAdapter(getContext(),popularArr,R.layout.cardview_layout);
        //Popular products
        LinearLayoutManager linearLayout=new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);


        //Suggested List

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
        RecyclerView recyclerView2=(RecyclerView)view.findViewById(R.id.reycleview2);
        recyclerView2.setLayoutManager(gridLayoutManager);
        RecycleViewAdapter adapter2=new RecycleViewAdapter(getContext(),suggestedArr, R.layout.cardview_layout);
        recyclerView2.setAdapter(adapter2);


        return view;
    }

    void initArr()
    {
        popularArr.add(new Products("Jordan 3","300$","200$",R.drawable.j3));
        popularArr.add(new Products("Jordan 1","250$","100$",R.drawable.j1));
        popularArr.add(new Products("Jordan 7 Wheat","320$","120$",R.drawable.jd7wheat));
        popularArr.add(new Products("Jordan 1","200$","300$",R.drawable.jd1));


        suggestedArr.add(new Products("Man's Clothings","","",R.drawable.manclothing));
        suggestedArr.add(new Products("Woman's Clothings","","",R.drawable.womanclothing));
        suggestedArr.add(new Products("Electronic Devices","","",R.drawable.electronicdevice));
        suggestedArr.add(new Products("Others","","",R.drawable.otherproducts));

    }
}
