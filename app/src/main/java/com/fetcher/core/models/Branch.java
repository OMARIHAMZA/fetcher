package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class Branch {

    @SerializedName("id")
    private int id;

    @SerializedName("address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
