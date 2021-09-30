package com.mogawe.mosurvei.model.network.response.shipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.shipment.Province;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.io.Serializable;
import java.util.List;

public class ProvinceListResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Province> object;

    public List<Province> getObject() {
        return object;
    }

    public void setObject(List<Province> object) {
        this.object = object;
    }
}
