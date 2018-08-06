/*
    This class control all data of the application
 */

package com.example.dghan.androidmarketapp.Search;

import java.util.ArrayList;
import java.util.List;
import android.util.Pair;

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
        tree.updateTreeViaDatabase(new Pair<String,Integer>(product.getName()+" "+product.getDescription(),product_data.size()-1));
    }

    //User database methods
    //
    //
}