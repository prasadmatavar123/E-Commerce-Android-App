package com.demo.e_commerce.Model;

public class CategoryModel {
    String pic;
    String text;

    public CategoryModel(String pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
