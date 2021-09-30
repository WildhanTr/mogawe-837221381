package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.CertificateCount;
import com.mogawe.mosurvei.model.bean.SupplierDashboardCount;

import java.io.Serializable;

public class SupplierDashboardCountResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private SupplierDashboardCount object;

    public SupplierDashboardCount getObject() {
        return object;
    }

    public void setObject(SupplierDashboardCount object) {
        this.object = object;
    }
}
