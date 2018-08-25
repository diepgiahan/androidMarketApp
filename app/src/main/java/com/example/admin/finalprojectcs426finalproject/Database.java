/*
    This class control all data of the application
 */

package com.example.admin.finalprojectcs426finalproject;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<Products> product_data; //store product database

    //Product database methods
    Products getProductById(int id){
        return product_data.get(id);
    }

    public void addProduct(Products product){
        if (product_data==null)
            product_data = new ArrayList<Products>();

        //Add a product
        product_data.add(product);

        //Update Database tree
        TrieTree tree = new TrieTree();
        tree.updateTreeViaDatabase(new Pair<String,Integer>(product.getName(),product_data.size()-1));
        tree.updateTreeViaDatabase(new Pair<String,Integer>(product.getDescription(),product_data.size()-1));
    }

    public String getSalerByProduct(Products product) {
        return "saler name";
    }

    public String getSalerByProductId(Integer product){
        return "saler name";
    }



    //User database methods
    //
    //
}