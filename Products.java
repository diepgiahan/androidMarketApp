package com.example.admin.market;

public class Products {
    String name;
    String newprice;
    String oldprice;
    int res;
    int quantity=1;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
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

}
