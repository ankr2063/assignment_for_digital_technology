package com.ankit.assignment;

public class DisplayCakes {

    private int id;
    private String weight;
    private String cake_name;
    private int price;
    private String pictures;

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getWeight() {
        return weight;
    }

    public String getPictures() {
        return pictures;
    }

    public int getPrice() {
        return price;
    }

    public void setCake_name(String cake_name) {
        this.cake_name = cake_name;
    }

    public String getCake_name() {
        return cake_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
