package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("type")
    private String type;

    @SerializedName("id_photo")
    private String idPhoto;

    @SerializedName("cv")
    private String cv;

    @SerializedName("website")
    private String companyWebsite;

    @SerializedName("official_email")
    private String companyEmail;

    @SerializedName("main_address")
    private String companyAddress;

    @SerializedName("field_of_work")
    private String workField;


    public User(int id, String workField, String username, String email, String mobile, String type, String companyWebsite, String companyEmail, String companyAddress) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.type = type;
        this.companyWebsite = companyWebsite;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress;
        this.workField = workField;
    }

    public User() {
    }

    public String getWorkField() {
        return workField;
    }

    public void setWorkField(String workField) {
        this.workField = workField;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public int getId() {
        return id;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
