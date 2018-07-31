package com.example.hhmt.findfunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initalize database
        Database database = new Database();

        // Initialize product database
        /* EXAMPLE
            Products product = new Products("giay","giay abc","123",1.1);
            Products product2 = new Products("giay abc","giay","123",1.3);
            Products product3 = new Products("giay abc 1","giay cde","123",1.3);
            Products product4 = new Products("giay abc 2","giay cde efg","123",1.4);
            Products product5 = new Products("giay abc 2","giay cde efg","123",1.4);
            Products product6 = new Products("giay abc 2","giay cde efg","123",1.4);
            Products product7 = new Products("giay abc 2","giay cde efg","123",1.4);

            database.addProduct(product);
            database.addProduct(product2);
            database.addProduct(product3);
            database.addProduct(product4);
            database.addProduct(product5);
            database.addProduct(product6);
            database.addProduct(product7);
        */

        // Start searching

        // ~ Initialize search task object
        SearchTask search_task = new SearchTask(this);

        // ~ Search string s
        /* EXAMPLE
            search_task.search("abc cde efg");
        */

        // ~ Get suggestion with current text s
        /*EXAMPLE
            search_task.suggest(s);
        */
    }
}
