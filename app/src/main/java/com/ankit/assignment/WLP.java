package com.ankit.assignment;

public class WLP {

    private int weight_id;
    private int layer_id;
    private String weight;
    private int layer;
    private int price;
    private String pictures;

    public int getLayer() {
        return layer;
    }

    public int getLayer_id() {
        return layer_id;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight_id() {
        return weight_id;
    }

    public String getPictures() {
        return pictures;
    }

    public String getWeight() {
        return weight;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setLayer_id(int layer_id) {
        this.layer_id = layer_id;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setWeight_id(int weight_id) {
        this.weight_id = weight_id;
    }

}
