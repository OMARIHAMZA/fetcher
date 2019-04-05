package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class CompanyPhoto {

    @SerializedName("id")
    private int id;

    @SerializedName("photo")
    private String photo;

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }
}
