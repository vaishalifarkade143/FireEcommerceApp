package com.example.fireecommerceapp.models;

import java.io.Serializable;

//92.
                            //105.implements Serializable
public class ShowAllModel implements Serializable
{
    String description;
    String img_url;
    String rating;
    String  name;
    int price;
    String type;

    public ShowAllModel() {
    }

    public ShowAllModel(String description, String img_url, String rating, String name, int price, String type) {
        this.description = description;
        this.img_url = img_url;
        this.rating = rating;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
