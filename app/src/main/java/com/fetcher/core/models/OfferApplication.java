package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class OfferApplication {

    @SerializedName("application_id")
    private int applicationId;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("person_id")
    private int personId;

    @SerializedName("name")
    private String applicantName;

    @SerializedName("email")
    private String applicantEmail;

    @SerializedName("mobile")
    private String applicantMobile;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantMobile() {
        return applicantMobile;
    }

    public void setApplicantMobile(String applicantMobile) {
        this.applicantMobile = applicantMobile;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
