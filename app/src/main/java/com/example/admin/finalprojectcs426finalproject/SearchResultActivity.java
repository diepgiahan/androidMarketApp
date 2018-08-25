/*
    This activity control result after click find button in previous activity
 */

package com.example.admin.finalprojectcs426finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class SearchResultActivity extends AppCompatActivity {
    List<Integer> id_list = new ArrayList<Integer>();
    Database database = new Database();
    ArrayList<Products> products = new ArrayList<>();
    Integer number_of_times=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        related(findViewById(R.id.related));

        //Get the intent from the previous activity
        Intent intent = getIntent();

        ///Get the list of ID's product (id_list) created by SearchTask

        id_list = intent.getIntegerArrayListExtra("id list");
        EditText editText = (EditText)findViewById(R.id.search_edit_frame);
        editText.setText(intent.getStringExtra("search string"));
        editText.setSelection(editText.getText().length());

        //Get the product list (products) by their ID in id_list

        for (int i=0;i<id_list.size();i++){
            products.add(database.getProductById(id_list.get(i)));
        }

        //Set search results (products) to list view
        ListView list_view = (ListView)findViewById(R.id.result_list);
        ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,products);
        list_view.setAdapter(product_adapter);
    }

        public void related(View view) {
        Drawable background = getDrawable(R.drawable.non_selected);
        int color = getColor(R.color.colorBlack);

        TextView textView = (TextView)findViewById(R.id.price);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.rate);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.new_arrival);
        textView.setTextColor(color);
        textView.setBackground(background);
        textView = (TextView)findViewById(R.id.related);
        textView.setBackground(getDrawable(R.drawable.selected));
        textView.setTextColor(getColor(R.color.colorWhite2));

            ListView list_view = (ListView)findViewById(R.id.result_list);
            ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,products);
            list_view.setAdapter(product_adapter);
        number_of_times = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void price(View view) {
          


        Drawable background = getDrawable(R.drawable.non_selected);
        int color = getColor(R.color.colorBlack);

        TextView textView = (TextView)findViewById(R.id.related);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.rate);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.new_arrival);
        textView.setTextColor(color);
        textView.setBackground(background);
        textView = (TextView)findViewById(R.id.price);
        textView.setBackground(getDrawable(R.drawable.selected));
        textView.setTextColor(getColor(R.color.colorWhite2));
        ArrayList<Pair<Integer,Products>> collection = new ArrayList<>();
        for (int i=0;i<id_list.size();i++){
            Products item = products.get(i);
            collection.add(new Pair<Integer, Products>(Integer.parseInt(item.getNewprice()),item));
        }
        number_of_times++;
        if (number_of_times%2==1) {
            ((ImageView) findViewById(R.id.upordown)).setImageResource(R.drawable.price_up);
            collection.sort(comparator);
        }
        else {
            ((ImageView) findViewById(R.id.upordown)).setImageResource(R.drawable.price_down);
            collection.sort(comparator2);
        }
        ArrayList<Products> productsArrayList = new ArrayList<>();
        for (int i=0;i<collection.size();i++)
            productsArrayList.add(collection.get(i).second);
        ListView list_view = (ListView)findViewById(R.id.result_list);
        ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,productsArrayList);
        list_view.setAdapter(product_adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void rate(View view) {
        Drawable background = getDrawable(R.drawable.non_selected);
        int color = getColor(R.color.colorBlack);

        TextView textView = (TextView)findViewById(R.id.related);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.price);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.new_arrival);
        textView.setTextColor(color);
        textView.setBackground(background);
        textView = (TextView)findViewById(R.id.rate);
        textView.setBackground(getDrawable(R.drawable.selected));
        textView.setTextColor(getColor(R.color.colorWhite2));
        ArrayList<Pair<Double,Products>> collection = new ArrayList<>();
        for (int i=0;i<id_list.size();i++){
            Products item = products.get(i);
            collection.add(new Pair<Double, Products>(item.getRate(),item));
        }
        collection.sort(doubleComparator);
        ArrayList<Products> productsArrayList = new ArrayList<>();
        for (int i=0;i<collection.size();i++)
            productsArrayList.add(collection.get(i).second);
        ListView list_view = (ListView)findViewById(R.id.result_list);
        ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,productsArrayList);
        list_view.setAdapter(product_adapter);
        number_of_times=0;
    }

    public void new_arrival(View view) {
        Drawable background = getDrawable(R.drawable.non_selected);
        int color = getColor(R.color.colorBlack);

        TextView textView = (TextView)findViewById(R.id.related);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.rate);
        textView.setBackground(background);
        textView.setTextColor(color);
        textView = (TextView)findViewById(R.id.price);
        textView.setTextColor(color);
        textView.setBackground(background);
        textView = (TextView)findViewById(R.id.new_arrival);
        textView.setBackground(getDrawable(R.drawable.selected));
        textView.setTextColor(getColor(R.color.colorWhite2));
        number_of_times=0;
    }
    Comparator<Pair<Integer,Products>> comparator = new Comparator<Pair<Integer, Products>>() {
        @Override
        public int compare(Pair<Integer, Products> o1, Pair<Integer, Products> o2) {
            if (o1.first>o2.first)
                return 1;
            else if (o1.first<o2.first)
                return -1;
            return 0;
        }
    };
    Comparator<Pair<Integer,Products>> comparator2 = new Comparator<Pair<Integer, Products>>() {
        @Override
        public int compare(Pair<Integer, Products> o1, Pair<Integer, Products> o2) {
            if (o1.first>o2.first)
                return -1;
            else if (o1.first<o2.first)
                return 1;
            return 0;
        }
    };
    Comparator<Pair<Double,Products>> doubleComparator = new Comparator<Pair<Double, Products>>() {
        @Override
        public int compare(Pair<Double, Products> o1, Pair<Double, Products> o2) {
            if (o1.first>o2.first)
                return -1;
            else if (o1.first<o2.first)
                return 1;
            return 0;
        }
    };

    public void searchButtonPresssed(View view) {
        products.clear();
        SearchTask searchTask = new SearchTask(this);
        //id_list = searchTask.searchNoNewAcitivy(((EditText)findViewById(R.id.search_edit_frame)).getText().toString());;

        //Get the product list (products) by their ID in id_list

        /*for (int i=0;i<id_list.size();i++){
            products.add(database.getProductById(id_list.get(i)));
        }

        //Set search results (products) to list view
        ListView list_view = (ListView)findViewById(R.id.result_list);
        ProductAdapter product_adapter = new ProductAdapter(SearchResultActivity.this,products);
        list_view.setAdapter(product_adapter);*/

    }
}