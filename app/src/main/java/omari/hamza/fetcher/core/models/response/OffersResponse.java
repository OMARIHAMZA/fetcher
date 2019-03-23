package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import omari.hamza.fetcher.core.models.Offer;

public class OffersResponse {


    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<Offer> offers;

    public OffersResponse() {
    }

    public OffersResponse(boolean success, ArrayList<Offer> offers) {
        this.success = success;
        this.offers = offers;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
}
