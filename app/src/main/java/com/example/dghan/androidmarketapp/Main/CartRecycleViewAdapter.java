package com.example.dghan.androidmarketapp.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    TextView TotalCost;
    ViewGroup defaultParent;

    int getTotalPrice()
    {
        int price=0;
        for (int i=0;i<mData.size();i++)
        {
            price+=mData.get(i).getQuantity()*Integer.valueOf(mData.get(i).oldprice);
        }
        return price;
    }


    public CartRecycleViewAdapter(Context context, int res, ArrayList<Products> mData) {
        this.context = context;
        this.res = res;
        this.mData = mData;
    }

    public CartRecycleViewAdapter(Context context, int res, ArrayList<Products> mData, TextView totalCost) {
        this.context = context;
        this.res = res;
        this.mData = mData;
        this.TotalCost = totalCost;
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
        holder.quantity.setText("1");
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
/** START @Han add the minimum number to be 0 */
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
                /** Conf 1 looix nhor: lucs mowis baajt leen casi nafy > 0, khi truwf xuoosng truwf 1 laafn 2 naasc: -2 -4 ...
                 *  Taan cos ddeer truwf xoos owr ddaau nuwax har
                 *  xong tawng tuwf soos aam leen r giarm nguowjc laij thif khoong bij nuwax
                 * */
                /*  ORIGINAL:
                    Toast.makeText(context, "Clicked increased", Toast.LENGTH_SHORT).show();
                    holder.setQuantityIntMinus(presentQuantity - 1);
                    holder.changeQuantityDisplay(presentQuantity - 1);
                    mData.get(position).setQuantity(presentQuantity - 1);
                    int price = getTotalPrice();
                    //    totalcost.setText(Integer.toString(price));
                    TotalCost.setText(Integer.toString(price));
                */
/** END @Han add the minimum number to be 0 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantity;
        TextView name;
        TextView price;
        ImageView pic;
        ImageButton increase;
        ImageButton decrease;
        int quantityInt=1;

        public int getQuantityInt() {
            return quantityInt;
        }

        void changeQuantityDisplay(int number)
        {
            quantity.setText(Integer.toString(number));
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


        }
        void initButton()
        {


        }
    }


}
