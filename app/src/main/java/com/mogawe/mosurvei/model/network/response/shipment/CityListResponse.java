package com.mogawe.mosurvei.model.network.response.shipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.shipment.City;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.io.Serializable;
import java.util.List;

public class CityListResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<City> object;

    public List<City> getObject() {
        return object;
    }

    public void setObject(List<City> object) {
        this.object = object;
    }
}
