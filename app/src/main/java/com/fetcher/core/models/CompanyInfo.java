package com.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompanyInfo {

    @SerializedName("company")
    private Company company;

    @SerializedName("projects")
    private ArrayList<Project> projects;

    @SerializedName("branches")
    private ArrayList<Branch> branches;

    @SerializedName("photos")
    private ArrayList<CompanyPhoto> companyPhotos;

    public ArrayList<CompanyPhoto> getCompanyPhotos() {
        return companyPhotos;
    }

    public void setCompanyPhotos(ArrayList<CompanyPhoto> companyPhotos) {
        this.companyPhotos = companyPhotos;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
