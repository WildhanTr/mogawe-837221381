package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class ActivationRequest implements Serializable {

    private String email;
//    @SerializedName("activation_code")
    private String activationCode;


    public ActivationRequest(String email, String activationCode) {
        this.email = email;
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
