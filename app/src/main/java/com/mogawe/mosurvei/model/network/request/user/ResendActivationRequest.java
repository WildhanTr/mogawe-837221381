package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class ResendActivationRequest implements Serializable {

    private String email;

    public ResendActivationRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
