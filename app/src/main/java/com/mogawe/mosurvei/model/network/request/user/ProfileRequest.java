package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

public class ProfileRequest implements Serializable {

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
