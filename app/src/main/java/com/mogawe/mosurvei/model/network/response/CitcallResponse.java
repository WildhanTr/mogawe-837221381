package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CitcallResponse implements Serializable {

    @SerializedName("rc")
    @Expose
    private int rc;
    @SerializedName("trxid")
    @Expose
    private String trxid;
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("gateway")
    @Expose
    private int gateway;

    public int getRc() {
        return rc;
    }

    public String getTrxid() {
        return trxid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getToken() {
        return token;
    }

    public int getGateway() {
        return gateway;
    }
}
