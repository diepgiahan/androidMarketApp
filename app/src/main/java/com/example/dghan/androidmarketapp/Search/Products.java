package com.example.dghan.androidmarketapp.Search;

public class Products {
    private String name;
    private String description;
    private String newprice;
    private String oldprice;
    private int res;
    private int id;
    private int quantity=1;
    private double rate;


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Products(String name, String description, String newprice, double rate){
        this.name = name;
        this.description = description;
        this.newprice = newprice;
        this.rate = rate;
    }

   public Products(String name, String newprice, String oldprice, int res) {
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
    }

    public Products(String name, String oldprice, int res) {
        this.name = name;
        this.oldprice = oldprice;
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewprice() {
        return newprice;
    }

    public void setNewprice(String newprice) {
        this.newprice = newprice;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
