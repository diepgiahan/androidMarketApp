package com.example.admin.finalprojectcs426finalproject;

import java.util.HashSet;
import java.util.Set;

public class TrieTreeNode {
    private TrieTreeNode child[];
    private Set<String> list;
    public TrieTreeNode(){
        child = new TrieTreeNode[130];
    }
    public void finalize(){
        for (int i=0;i<130;i++)
            child[i]=null;
    }
    public TrieTreeNode[] getChild(){
        return  child;
    }

    public void setList(String id) {
        if (list==null)
            this.list = new HashSet<String>();
        this.list.add(id);
    }

    public Set<String> getList() {
        return list;
    }

}