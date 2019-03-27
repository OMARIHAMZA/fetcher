package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import omari.hamza.fetcher.core.models.OfferApplication;

public class OfferApplicationsResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<OfferApplication> applications;


    public OfferApplicationsResponse(boolean success, ArrayList<OfferApplication> applications) {
        this.success = success;
        this.applications = applications;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<OfferApplication> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<OfferApplication> applications) {
        this.applications = applications;
    }
}
