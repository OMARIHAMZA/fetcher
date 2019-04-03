package com.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import com.fetcher.core.models.User;

public class PersonInfoResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("person")
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
