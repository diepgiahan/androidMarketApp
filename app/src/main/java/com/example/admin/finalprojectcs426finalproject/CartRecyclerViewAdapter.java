package com.example.admin.finalprojectcs426finalproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>{

    TextView totaltextview;
    private ArrayList<String> products;
    Context context;
    public CartRecyclerViewAdapter(Context context, ArrayList<String> products,TextView total){
        this.context = context;
        this.products = products;
        this.totaltextview=total;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    int getTotalPrice()
    {
        Database database=new Database();
        int s=0;
        for (int i=0;i<products.size();i++)
        {
            Products currentproduct=database.getProductById(products.get(i));
            s+=Integer.parseInt(currentproduct.newprice);
        }
        return s;
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Database database = new Database();
        String product = products.get(position);
        //holder.saler.setText(database.getSalerByProductId(product));
        //holder.image
        holder.name.setText((database.getProductById(product).getName()));
        holder.new_price.setText(database.getProductById(product).getNewprice());
        holder.old_price.setText(database.getProductById(product).getOldprice());
        Cart cart = new Cart();
        holder.amount.setText(Integer.toString(cart.getAmountAt(position)));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                EditText editText = holder.amount;
                int amount = cart.getAmountAt(position)+1;
                cart.setAmount(position,amount);
                editText.setText(Integer.toString(amount));
                editText.setSelection(editText.getText().length());
                int total = 0;
                for (int i=0;i<cart.size();i++)
                    total = total + cart.getAmountAt(i)*cart.getPriceAt(i);
                View getotal=LayoutInflater.from(context).inflate(R.layout.cart_pop_up,null);

                totaltextview.setText(Integer.toString(total));

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, saler, new_price, old_price;
        ImageView add, remove;
        private EditText amount;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            saler = (TextView) itemView.findViewById(R.id.saler);
            new_price = (TextView) itemView.findViewById(R.id.new_price);
            old_price = (TextView) itemView.findViewById(R.id.old_price);
            image = (ImageView) itemView.findViewById(R.id.image);
            add = (ImageView) itemView.findViewById(R.id.add);
            remove = (ImageView) itemView.findViewById(R.id.remove);
            amount = (EditText) itemView.findViewById(R.id.amount);
        }
    }
}
