package com.example.dghan.androidmarketapp.Main;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dghan.androidmarketapp.R;

import java.util.List;

public class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    private Context context;
    private List<Products> mData;
    int res;

    public interface OnItemClickListener {
        void onItemClick(Products item);
    }
    private final OnItemClickListener listener;

    public RecycleViewAdapter(Context context, List<Products> mData, int res, OnItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        this.res=res;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(res,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.newprice.setText(mData.get(position).getNewprice());
        holder.oldprice.setText(mData.get(position).getOldprice());
        /** START @Han add strikethrough text for oldprice */
        holder.oldprice.setPaintFlags(holder.oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        /** END @Han add strikethrough text for oldprice */
        holder.pic.setImageResource(mData.get(position).getRes());
        holder.bind(mData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView oldprice;
        TextView newprice;
        ImageView pic;


        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.pname);
            oldprice=(TextView)itemView.findViewById(R.id.oldprice);
            newprice=(TextView)itemView.findViewById(R.id.newprice);
            pic=(ImageView)itemView.findViewById(R.id.pimage);
        }
        public void bind(final Products item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
