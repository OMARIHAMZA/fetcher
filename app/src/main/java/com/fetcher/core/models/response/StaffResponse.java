package com.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.fetcher.core.models.Person;

public class StaffResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<Person> staff;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Person> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Person> staff) {
        this.staff = staff;
    }
}
