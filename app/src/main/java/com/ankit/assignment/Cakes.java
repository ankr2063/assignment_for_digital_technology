package com.ankit.assignment;

import java.util.ArrayList;

public class Cakes {

    private Integer id;
    private String cake_name;
    private ArrayList<WLP> w_l_p;

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<WLP> getW_l_p() {
        return w_l_p;
    }

    public Integer getId() {
        return id;
    }

    public String getCake_name() {
        return cake_name;
    }

    public void setCake_name(String cake_name) {
        this.cake_name = cake_name;
    }

    public void setW_l_p(ArrayList<WLP> w_l_p) {
        this.w_l_p = w_l_p;
    }
}
