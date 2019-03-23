package omari.hamza.fetcher.core.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import omari.hamza.fetcher.core.models.Message;
import omari.hamza.fetcher.core.models.User;

public class MessagesResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("access_token")
    private String token;

    @SerializedName("user")
    private User user;

    @SerializedName("data")
    private ArrayList<Message> messages;


    public MessagesResponse(boolean success, String token, User user) {
        this.success = success;
        this.token = token;
        this.user = user;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
