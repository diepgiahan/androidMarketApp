package com.example.admin.finalprojectcs426finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Integer> products;
    private Context context;

    public CartRecyclerViewAdapter(Context context, ArrayList<Integer> products){
        this.context = context;
        this.products = products;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Database database = new Database();
        Integer product = products.get(position);
        holder.saler.setText(database.getSalerByProductId(product));
        //holder.image
        holder.name.setText((database.getProductById(product).getName()));
        holder.new_price.setText(database.getProductById(product).getNewprice());
        holder.old_price.setText(database.getProductById(product).getOldprice());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, saler, new_price, old_price, amount;
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            saler = (TextView)itemView.findViewById(R.id.saler);
            new_price = (TextView)itemView.findViewById(R.id.new_price);
            old_price = (TextView)itemView.findViewById(R.id.old_price);
            //amount = (TextView)itemView.findViewById(R.id.amount);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}
