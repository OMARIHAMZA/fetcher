package com.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.fetcher.core.models.Rating;

public class RatingResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<Rating> ratings;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
}
