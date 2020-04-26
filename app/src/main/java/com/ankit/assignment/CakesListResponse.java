package com.ankit.assignment;

import java.util.ArrayList;

public class CakesListResponse {

    private String status;
    private String error_msg;
    private ArrayList<Cakes> data;

    public ArrayList<Cakes> getData() {
        return data;
    }

    public String getError_msg() {
        return error_msg;
    }

    public String getStatus() {
        return status;
    }

    public void setData(ArrayList<Cakes> data) {
        this.data = data;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
