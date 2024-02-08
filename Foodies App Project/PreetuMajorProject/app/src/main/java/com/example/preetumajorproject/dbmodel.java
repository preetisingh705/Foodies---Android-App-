package com.example.preetumajorproject;

public class dbmodel {
    String shopname, shopdesc, servicecharge, shopimg, stittle;

    public dbmodel() {
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopdesc() {
        return shopdesc;
    }

    public void setShopdesc(String shopdesc) {
        this.shopdesc = shopdesc;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg) {
        this.shopimg = shopimg;
    }

    public String getStittle() {
        return stittle;
    }

    public void setStittle(String stittle) {
        this.stittle = stittle;
    }

    public dbmodel(String shopname, String shopdesc, String servicecharge, String shopimg, String stittle) {
        this.shopname = shopname;
        this.shopdesc = shopdesc;
        this.servicecharge = servicecharge;
        this.shopimg = shopimg;
        this.stittle = stittle;
    }
}

