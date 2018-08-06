package com.example.dghan.androidmarketapp.Search;

import java.util.HashSet;
import java.util.Set;

public class TrieTreeNode {
    private TrieTreeNode child[];
    private Set<Integer> list;
    public TrieTreeNode(){
        child = new TrieTreeNode[150];
    }
    public void finalize(){
        for (int i=0;i<145;i++)
            child[i]=null;
    }
    public TrieTreeNode[] getChild(){
        return  child;
    }

    public void setList(int id) {
        if (list==null)
            this.list = new HashSet<Integer>();
        this.list.add(id);
    }

    public Set<Integer> getList() {
        return list;
    }

}
