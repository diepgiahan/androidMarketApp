package com.example.admin.finalprojectcs426finalproject;

import android.provider.ContactsContract;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Cart {
    private static ArrayList<String> products = new ArrayList<>();
    private static ArrayList<Integer> amount = new ArrayList<>();
    private TreeMap<String,Integer> map = new TreeMap<String,Integer>();
    private Set<String> set = new HashSet<String>();

    public void add(String product, int amount){

        if (set.contains(product)) {
            this.amount.set(map.get(product), this.amount.get(map.get(product)) + amount);
            return;
        }
        products.add(product);
        this.amount.add(amount);
        map.put(product,products.size()-1);
        set.add(product);


    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public static ArrayList<Integer> getAmount() {
        return amount;
    }
    public void clearProducts(){
        products.clear();
    }
    public void clearAmount(){
        amount.clear();
    }
    public int size(){
        return products.size();
    }

    public int getAmountAt(int position){
        return amount.get(position);
    }
    public String getProductAt(int position){
        return  products.get(position);
    }
    public int getPriceAt(int position){
        Database database = new Database();
        return Integer.valueOf((database.getProductById(products.get(position))).getNewprice());
    }
    public void setAmount(int position, int amount) {
        this.amount.set(position,amount);
    }
}
