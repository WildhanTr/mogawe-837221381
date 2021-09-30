package com.mogawe.mosurvei.model.network.request.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class PhoneActivationRequest implements Serializable {

    private String email;
    private String phone;
//    @SerializedName("activation_code")
    private String activationCode;

    public PhoneActivationRequest(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public PhoneActivationRequest(String email, String phone, String activationCode) {
        this.email = email;
        this.phone = phone;
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
