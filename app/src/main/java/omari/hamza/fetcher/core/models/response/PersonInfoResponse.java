package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import omari.hamza.fetcher.core.models.User;

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
