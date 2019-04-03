package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("rating")
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
