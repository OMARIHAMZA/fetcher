package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("name")
    private String name;

    @SerializedName("company_id")
    private int id;

    @SerializedName("official_email")
    private String email;

    @SerializedName("website")
    private String website;

    @SerializedName("main_address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
