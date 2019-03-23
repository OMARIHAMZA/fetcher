package omari.hamza.fetcher.core.models;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("name")
    private String title;

    @SerializedName("website")
    private String website;

    @SerializedName("message")
    private String body;

    public Message(String title, String website, String body) {
        this.title = title;
        this.website = website;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
