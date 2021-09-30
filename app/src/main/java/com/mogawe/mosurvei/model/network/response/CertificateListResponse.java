package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;

import java.io.Serializable;
import java.util.List;

public class CertificateListResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Certificate> object;

    public List<Certificate> getObject() {
        return object;
    }

    public void setObject(List<Certificate> object) {
        this.object = object;
    }
}
