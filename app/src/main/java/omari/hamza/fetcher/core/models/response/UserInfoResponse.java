package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import omari.hamza.fetcher.core.models.User;

public class UserInfoResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private User user;

    public UserInfoResponse(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

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
