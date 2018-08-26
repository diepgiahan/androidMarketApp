/*
    This class control all data of the application
 */

package com.example.admin.finalprojectcs426finalproject;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<Products> product_data=new ArrayList<>(); //store product database

    //Product database methods
    public Products getProductById(String id){
        int i, res = 0;
        for (i=0;i<product_data.size();i++)
            if (product_data.get(i).getId()==id)
                res = i;
        return product_data.get(res);
    }

    public void addProduct(Products product){
        if (product_data==null)
            product_data = new ArrayList<Products>();

        //Add a product
        product_data.add(product);

        //Update Database tree
        TrieTree tree = new TrieTree();
        tree.updateTreeViaDatabase(new Pair<String,String>(product.getName(),product.getId()));
        tree.updateTreeViaDatabase(new Pair<String,String>(product.getDescription(),product.getId()));
    }

    public String getSalerByProduct(Products product) {
        return "saler name";
    }

    public String getSalerByProductId(Integer product){
        return "saler name";
    }

    public void setProduct_data(List<Products> product_data) {
        for (int i=0;i<product_data.size();i++)
            addProduct(product_data.get(i));
    }

    //User database methods
    //
    //
}