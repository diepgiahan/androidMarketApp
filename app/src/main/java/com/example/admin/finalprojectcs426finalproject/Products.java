package com.example.admin.finalprojectcs426finalproject;

public class Products {
    String description="";
    String id;
    String name;
    String newprice;
    String oldprice;
    String res;//res là dạng URL
    int quantity=1;
    String maincolour;
    String type;
    double rate=1;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaincolour(String maincolour) {
        this.maincolour = maincolour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getMaincolour() {
        return maincolour;
    }

    public String getType() {
        return type;
    }

    public Products(String id, String name, String newprice, String oldprice, String res, int quantity, String maincolour, String type) {
        this.id = id;
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
        this.quantity = quantity;
        this.maincolour = maincolour;
        this.type = type;
    }

    public Products(String id, String name, String newprice, String oldprice, int quantity, String maincolour, String type) {
        this.id = id;
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.quantity = quantity;
        this.maincolour = maincolour;
        this.type = type;
    }


    public Products(String id, String name, String newprice, String oldprice, String res, int quantity) {
        this.id = id;
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Products(String name, String newprice, String oldprice, String res) {
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
    }

    public Products(String name, String oldprice, String res) {
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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

}