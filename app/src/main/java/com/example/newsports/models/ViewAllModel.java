package com.example.newsports.models;

public class ViewAllModel {

    String name;
    String img_url;
    String rating;
    String description;
    String price;

    public ViewAllModel(String name, String img_url, String rating, String description, String price) {
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.description = description;
        this.price = price;
    }

    public ViewAllModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
