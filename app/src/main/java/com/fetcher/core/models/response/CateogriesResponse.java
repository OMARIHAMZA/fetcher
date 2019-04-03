package com.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.fetcher.core.models.Category;

public class CateogriesResponse {

    @SerializedName("success")
    private boolean status;

    @SerializedName("data")
    private ArrayList<Category> categories;

    public CateogriesResponse(boolean status, ArrayList<Category> categories) {
        this.status = status;
        this.categories = categories;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
