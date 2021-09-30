package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CertificateCount implements Serializable {

    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("notVerified")
    @Expose
    private Integer notVerified;
    @SerializedName("pending")
    @Expose
    private Integer pending;
    @SerializedName("expired")
    @Expose
    private Integer expired;

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getNotVerified() {
        return notVerified;
    }

    public void setNotVerified(Integer notVerified) {
        this.notVerified = notVerified;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }
}
