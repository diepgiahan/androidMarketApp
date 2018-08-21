package com.example.dghan.androidmarketapp.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dghan.androidmarketapp.R;

import java.util.ArrayList;

public class CartRecycleViewAdapter extends RecyclerView.Adapter<CartRecycleViewAdapter.MyViewHolder> {

    Context context;
    int res;
    ArrayList<Products>mData=new ArrayList<>();
    Products chosenProduct;

    TextView TotalCost;
    ViewGroup defaultParent;


    public interface OnItemClickListener {
        void onItemClick(Products item);
    }

    private  final  OnItemClickListener listener;

    public CartRecycleViewAdapter(Context context, int res, ArrayList<Products> mData, TextView totalCost, OnItemClickListener listener) {
        this.context = context;
        this.res = res;
        this.mData = mData;
        this.TotalCost = totalCost;
        this.listener=listener;
    }

    int getTotalPrice()
    {
        int price=0;
        for (int i=0;i<mData.size();i++)
        {
            price+=mData.get(i).getQuantity()*Integer.valueOf(mData.get(i).oldprice);
        }
        return price;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(res,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.initButton();
        holder.name.setText(mData.get(position).getName());
        holder.price.setText(mData.get(position).getOldprice());
        holder.pic.setImageResource(mData.get(position).getRes());
        holder.quantity.setText(Integer.toString(mData.get(position).getQuantity()));
        // View view=LayoutInflater.from(context).inflate(R.layout.activity_cart,null,false);
        // final TextView totalcost=(TextView)view.findViewById(R.id.totalcost);
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Clicked increase" , Toast.LENGTH_SHORT).show();
                int presentQuantity=holder.getQuantityInt();
                holder.setQuantityIntPlus(presentQuantity);
                holder.changeQuantityDisplay(presentQuantity+1);
                mData.get(position).setQuantity(presentQuantity+1);

                // View view=LayoutInflater.from(context).inflate(R.layout.activity_cart,null,false);
                //   TextView totalcost=(TextView)view.findViewById(R.id.totalcost);

                int price=getTotalPrice();
                //    totalcost.setText(Integer.toString(price));
                TotalCost.setText(Integer.toString(price));
            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int presentQuantity = holder.getQuantityInt();
                if(presentQuantity == 0){
                    Toast.makeText(context, "Click remove to delete this item from cart", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Clicked decreased", Toast.LENGTH_SHORT).show();
                    holder.setQuantityIntMinus(presentQuantity - 1);
                    holder.changeQuantityDisplay(presentQuantity - 1);
                    mData.get(position).setQuantity(presentQuantity - 1);
                    int price = getTotalPrice();
                    //    totalcost.setText(Integer.toString(price));
                    TotalCost.setText(Integer.toString(price));
                }

            }
        });

        holder.setLongClickListener(new LongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                //  Toast.makeText(context, mData.get(pos).getName(), Toast.LENGTH_SHORT).show();
                chosenProduct=mData.get(pos);


            }
        });
        holder.bind(mData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public Products getChosenProduct() {
        return chosenProduct;
    }

    public int getItemSelected(MenuItem menuItem)
    {
        return menuItem.getItemId();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener {

        TextView quantity;
        TextView name;
        TextView price;
        ImageView pic;
        ImageButton increase;
        ImageButton decrease;
        int quantityInt=1;
        LongClickListener longClickListener;

        public int getQuantityInt() {
            return quantityInt;
        }

        void changeQuantityDisplay(int number)
        {
            quantity.setText(Integer.toString(number));
        }

        public void bind(final Products item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


        public void setQuantityIntPlus(int quantityInt) {
            this.quantityInt = quantityInt+1;
        }

        public void setQuantityIntMinus(int quantityInt) {
            this.quantityInt = quantityInt-1;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            View view=LayoutInflater.from(context).inflate(R.layout.activity_cart,null,false);
            name=(TextView)itemView.findViewById(R.id.listproduct_name);
            price=(TextView)itemView.findViewById(R.id.listproduct_price);
            pic=(ImageView)itemView.findViewById(R.id.listproduct_img);

            increase=(ImageButton)itemView.findViewById(R.id.increasebtn);
            decrease=(ImageButton)itemView.findViewById(R.id.decreasebtn);
            quantity=(TextView) itemView.findViewById(R.id.listproduct_quantity);
            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }
        public void setLongClickListener(LongClickListener lc)
        {
            this.longClickListener=lc;

        }

        @Override
        public boolean onLongClick(View v) {
            //Toast.makeText(context, "LayoutPos"+Integer.toString(getLayoutPosition()), Toast.LENGTH_SHORT).show();
            this.longClickListener.onItemLongClick(getLayoutPosition());
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Settings");
            menu.add(0,0,0,"Remove");
        }
    }


}
