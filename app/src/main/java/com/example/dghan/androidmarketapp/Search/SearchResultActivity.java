/*
    This activity control result after click find button in previous activity
 */

package com.example.dghan.androidmarketapp.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.dghan.androidmarketapp.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        //Get the intent from the previous activity
        Intent intent = getIntent();

        ///Get the list of ID's product (id_list) created by SearchTask
        List<Integer> id_list = new ArrayList<Integer>();
        id_list = intent.getIntegerArrayListExtra("id list");

        //Get the product list (products) by their ID in id_list
        Database database = new Database();
        ArrayList<Products> products = new ArrayList<>();
        for (int i=0;i<id_list.size();i++){
            products.add(database.getProductById(id_list.get(i)));
        }

        //Set search results (products) to list view
        ListView list_view = (ListView)findViewById(R.id.result_list);
        ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,products);
        list_view.setAdapter(product_adapter);
    }
}
