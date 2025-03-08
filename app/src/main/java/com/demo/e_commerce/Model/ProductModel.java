package com.demo.e_commerce.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable {
    private String proid;
    private String name;
    private String memprice;
    private ArrayList<Price>  mrp;
    private String discount;
    private String proimg;
    private String variant;
    private String starate;
    private String descr;

    public ProductModel(String proid, String name, String memprice, ArrayList<Price> mrp, String discount, String proimg, String variant, String starate, String descr) {
        this.proid = proid;
        this.name = name;
        this.memprice = memprice;
        this.mrp = mrp;
        this.discount = discount;
        this.proimg = proimg;
        this.variant = variant;
        this.starate = starate;
        this.descr = descr;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }

    public String getId() {
        return proid;
    }

    public void setId(String id) {
        this.proid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return memprice;
    }

    public void setPrice(String price) {
        this.memprice = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getThumbnail() {
        return proimg;
    }

    public void setThumbnail(String thumbnail) {
        this.proimg = thumbnail;
    }

    public ArrayList<Price> getMrp() {
        return mrp;
    }

    public void setMrp(ArrayList<Price> mrp) {
        this.mrp = mrp;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getStarate() {
        return starate;
    }

    public void setStarate(String starate) {
        this.starate = starate;
    }

}
