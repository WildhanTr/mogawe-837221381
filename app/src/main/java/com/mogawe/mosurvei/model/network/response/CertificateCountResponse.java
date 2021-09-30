package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.bean.CertificateCount;

import java.io.Serializable;
import java.util.List;

public class CertificateCountResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private CertificateCount object;

    public CertificateCount getObject() {
        return object;
    }

    public void setObject(CertificateCount object) {
        this.object = object;
    }
}
