package com.mogawe.mosurvei.model.network.response.shipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.shipment.Logistic;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.io.Serializable;
import java.util.List;

public class LogisticListResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Logistic> object;

    public List<Logistic> getObject() {
        return object;
    }

    public void setObject(List<Logistic> object) {
        this.object = object;
    }
}
