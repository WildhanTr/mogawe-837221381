package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 5/1/16.
 */
public class LoginRequest implements Serializable {

    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
