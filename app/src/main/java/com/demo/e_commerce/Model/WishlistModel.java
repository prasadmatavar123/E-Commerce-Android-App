package com.demo.e_commerce.Model;

public class WishlistModel {

    String pic;
    String text1;
    String text2;
    String text3;

    public WishlistModel(String pic, String text1, String text2, String text3) {
        this.pic = pic;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
