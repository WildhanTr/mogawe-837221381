package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

public class CitcalMisscallRequest implements Serializable {

    private String phoneNumber;
    private int gateway;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGateway() {
        return gateway;
    }

    public void setGateway(int gateway) {
        this.gateway = gateway;
    }
}
