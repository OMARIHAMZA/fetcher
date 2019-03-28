package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import omari.hamza.fetcher.core.models.Company;

public class CompaniesResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<Company> companies;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }
}
