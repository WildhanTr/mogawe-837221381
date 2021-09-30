package com.mogawe.mosurvei.model.network.request;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 4/1/17.
 */

public class GenericReq implements Serializable {

    private String username = "MGWAPP";
    private String api_key = "504fbae0d815bf3e";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
