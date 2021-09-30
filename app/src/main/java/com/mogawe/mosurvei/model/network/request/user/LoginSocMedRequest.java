package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class LoginSocMedRequest implements Serializable {

    @SerializedName(value="fullName")
    private String fullName;
    private String email;
    @SerializedName(value="idFacebook")
    private String facebookId;
    @SerializedName(value="idGmail")
    private String gmailId;
    @SerializedName("idTwitter")
    private String twitterId;

    public LoginSocMedRequest(String fullName, String email, String facebookId, String gmailId, String twitterId) {
        this.fullName = fullName;
        this.email = email;
        this.facebookId = facebookId;
        this.gmailId = gmailId;
        this.twitterId = twitterId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
}
