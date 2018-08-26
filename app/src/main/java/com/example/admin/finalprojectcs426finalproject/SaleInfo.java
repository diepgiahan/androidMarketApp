package com.example.admin.finalprojectcs426finalproject;

public class SaleInfo {
    String seller;
    String prodid;

    public SaleInfo(String seller, String prodid) {
        this.seller = seller;

        this.prodid = prodid;
    }


    public SaleInfo() {
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
}
