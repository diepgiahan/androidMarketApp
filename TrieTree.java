package com.example.hhmt.findfunction;

import android.util.Pair;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class TrieTree {
    private static TrieTreeNode root;
    public TrieTree(){
        if (root==null) {
            root = new TrieTreeNode();
        }
    }

    public void addAWord(String word, int id){
        TrieTreeNode tmp = root;
        for (int i=0;i<word.length();i++){
            if ((tmp.getChild())[word.charAt(i)-' ']==null){
                tmp.getChild()[word.charAt(i)-' '] = new TrieTreeNode();
            }
            tmp=tmp.getChild()[word.charAt(i)-' '];
        }
        tmp.setList(id);
    }

    public Set<Integer> searchAWord(String word){
        TrieTreeNode tmp = root;
        for (int i=0;i<word.length();i++){
            if (tmp.getChild()[word.charAt(i)-' ']==null)
                return null;
            else tmp=tmp.getChild()[word.charAt(i)-' '];
        }
        return tmp.getList();
    }

    public List<String> suggest(String string){
        TrieTreeNode tmp = root;
        List<String> result = new ArrayList<String>();

        for (int i=0;i<string.length();i++)
            if (tmp.getChild()[string.charAt(i)-' ']!=null) {
                tmp = tmp.getChild()[string.charAt(i) - ' '];
            }
            else return null;

        Pair<TrieTreeNode,String> top;
        Queue<Pair<TrieTreeNode,String>> queue = new LinkedList<Pair<TrieTreeNode,String>>();
        queue.add(new Pair(tmp,string));

        while (!queue.isEmpty()){
            top = queue.remove();
            if (top.first.getList()!=null)
                result.add(top.second);
            for (int i=0;i<145;i++)
                if (top.first.getChild()[i]!=null){
                    queue.add(new Pair(top.first.getChild()[i],top.second+(char)i));
                }
        }
        return result;
    }


    public void updateTreeViaDatabase(Pair<String,Integer> update){
        String string = update.first;
        String substring;
        Integer id = update.second;

        StringTokenizer tokenizer = new StringTokenizer(string," ,.;:!@#$%^&*");
        while (tokenizer.hasMoreTokens()){
            substring = tokenizer.nextToken();
            addAWord(substring,id);
        }
    }
}


