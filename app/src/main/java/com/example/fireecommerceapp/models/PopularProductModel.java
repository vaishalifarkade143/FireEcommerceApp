package com.example.fireecommerceapp.models;

import java.io.Serializable;

//62
                                    //84.implements Serializable
public class PopularProductModel implements Serializable {

    String Description;
    String name;
    String rating;
    int price;
    String img_url;

    public PopularProductModel() {
    }

    public PopularProductModel(String Description, String name, String rating, int price, String img_url) {
        this.Description = Description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
