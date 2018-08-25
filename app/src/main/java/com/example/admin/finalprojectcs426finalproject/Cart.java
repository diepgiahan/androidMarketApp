package com.example.admin.finalprojectcs426finalproject;

import java.util.ArrayList;

public class Cart {
    private static ArrayList<Integer> products = new ArrayList<>();
    private static ArrayList<Integer> amount = new ArrayList<>();

    public void add(int product, int amount){
        products.add(product);
        this.amount.add(amount);
    }

    public static ArrayList<Integer> getProducts() {
        return products;
    }
    public int size(){
        return products.size();
    }
}
