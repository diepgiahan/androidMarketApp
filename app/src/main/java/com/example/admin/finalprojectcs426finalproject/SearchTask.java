package com.example.admin.finalprojectcs426finalproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SearchTask extends ContextWrapper {

    private TrieTree tree;


    public SearchTask(Context context){
        //get current context, activity and database
        super(context);
        this.tree = new TrieTree();

        /*
        //Create a search bar view through layout inflater
        View v = LayoutInflater.from(this.context).inflate(R.layout.search_bar,null);
        EditText et = (EditText) v.findViewById(R.id.search_line);
        et.setText("Enter any products here...");

        //Find search bar view position in current layout
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.main);

        //Add view created into search bar view in current layout
        linearLayout.addView(v);
        */
    }

    public void search(String s){
        if (s==null)
            return;

        // Default search query: OR query
        // Get the frequency of substrings in description and name of the item list
        // Rate item according to its frequency
        String substring; //to get token
        String value; //temporary variable
        Set<String> set = new HashSet<>(); //to store unique ID product
        Set<String> list = new HashSet<>(); //temporary variable
        TreeMap<String,Integer> rate_list = new TreeMap<>();
        List<Pair<Integer,String>> sorted_list = new ArrayList<>(); //to sort rate_list value
        StringTokenizer tokenizer = new StringTokenizer(s," ,!@#$%^&*()"); //to do search string token task

        //with each substring
        while (tokenizer.hasMoreTokens()){
            substring = tokenizer.nextToken();

            //get the list of items containing substring
            list = tree.searchAWord(substring);

            for (Iterator<String> i = list.iterator(); i.hasNext();){
                value = i.next();
                set.add(value);
                if (rate_list.get(value)==null)
                    rate_list.put(value,1);
                else rate_list.put(value,rate_list.get(value)+1);
            }
        }

        // ~ Sort the id list according to rating
        for (Iterator<String> i = set.iterator(); i.hasNext();){
            value = i.next();
            sorted_list.add(new Pair<Integer, String>(rate_list.get(value),value));
        }

        // ~ Implement Comparator of Pair<Integer,Integer> type
        Collections.sort(sorted_list, new Comparator<Pair<Integer, String>>() {
            @Override
            public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2) {

                if (o1.first > o2.first)
                    return -1;
                else if (o1.first < o2.first)
                    return 1;
                else return 0;
            }
        });

        // ~ Get id list after searching and rating
        List<String> id_list = new ArrayList<>();
        for (int i=0;i<sorted_list.size();i++)
            id_list.add(sorted_list.get(i).second);

        // Start a new activity to view search results
        // ~ Initialize an activity
        Intent intent = new Intent(this,SearchResultActivity.class);

        // ~ Push id list searched to the new activity
        intent.putStringArrayListExtra("id list",(ArrayList<String>)id_list);
        intent.putExtra("search string",s);

        // ~ Start the activity
        startActivity(intent);
    }

    List<Integer> searchNoNewAcitivy(String s){
        /*if (s==null)
            return new ArrayList<Integer>();

        // Default search query: OR query
        // Get the frequency of substrings in description and name of the item list
        // Rate item according to its frequency
        String substring; //to get token
        String value; //temporary variable
        Set<String> set = new HashSet<Integer>(); //to store unique ID product
        Set<Integer> list = new HashSet<Integer>(); //temporary variable
        TreeMap<Integer,Integer> rate_list = new TreeMap<>();
        List<Pair<Integer,Integer>> sorted_list = new ArrayList<>(); //to sort rate_list value
        StringTokenizer tokenizer = new StringTokenizer(s," ,!@#$%^&*()"); //to do search string token task

        //with each substring
        while (tokenizer.hasMoreTokens()){
            substring = tokenizer.nextToken();

            //get the list of items containing substring
            list = tree.searchAWord(substring);

            for (Iterator<Integer> i = list.iterator(); i.hasNext();){
                value = i.next();
                set.add(value);
                if (rate_list.get(value)==null)
                    rate_list.put(value,1);
                else rate_list.put(value,rate_list.get(value)+1);
            }
        }

        // ~ Sort the id list according to rating
        for (Iterator<Integer> i = set.iterator(); i.hasNext();){
            value = i.next();
            sorted_list.add(new Pair<Integer, Integer>(rate_list.get(value),value));
        }

        // ~ Implement Comparator of Pair<Integer,Integer> type
        /*Collections.sort(sorted_list, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {

                if (o1.first > o2.first)
                    return -1;
                else if (o1.first < o2.first)
                    return 1;
                else if (o1.second > o2.second)
                    return -1;
                else if (o1.second < o2.second)
                    return 1;
                else return 0;
            }
        });

        // ~ Get id list after searching and rating*/
        List<Integer> id_list = new ArrayList<Integer>();
        /*
        for (int i=0;i<sorted_list.size();i++)
            id_list.add(sorted_list.get(i).second);
            */
        return id_list;

    }

    List<String> suggest(String s){
        // Get suggest list which begins with string s
        return tree.suggest(s);
    }
}