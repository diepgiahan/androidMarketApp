package com.example.admin.finalprojectcs426finalproject;

public class ProdctsWithoutSeri {
    String id;
    String name;
    String newprice;
    String oldprice;
    String res;//res là dạng URL
    int quantity=1;
    String maincolour;
    String type;

    public String getMaincolour() {
        return maincolour;
    }

    public String getType() {
        return type;
    }

    public ProdctsWithoutSeri(String id, String name, String newprice, String oldprice, String res, int quantity, String maincolour, String type) {
        this.id = id;
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
        this.quantity = quantity;
        this.maincolour = maincolour;
        this.type = type;
    }

    public ProdctsWithoutSeri() {
    }

    public ProdctsWithoutSeri(String id, String name, String newprice, String oldprice, int quantity, String maincolour, String type) {
        this.id = id;
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.quantity = quantity;
        this.maincolour = maincolour;
        this.type = type;
    }


    public ProdctsWithoutSeri(String id, String name, String newprice, String oldprice, String res, int quantity) {
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

    public ProdctsWithoutSeri(String name, String newprice, String oldprice, String res) {
        this.name = name;
        this.newprice = newprice;
        this.oldprice = oldprice;
        this.res = res;
    }

    public ProdctsWithoutSeri(String name, String oldprice, String res) {
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
