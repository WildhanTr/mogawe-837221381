package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.GeoLocationSpotCheck;
import com.mogawe.mosurvei.model.db.entity.Item;

import java.util.List;

public class GeoLocationSpotCheckResponse extends GenericResponse {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private List<GeoLocationSpotCheck> geoLocationSpotChecks;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<GeoLocationSpotCheck> getGeoLocationSpotChecks() {
        return geoLocationSpotChecks;
    }

    public void setGeoLocationSpotChecks(List<GeoLocationSpotCheck> geoLocationSpotChecks) {
        this.geoLocationSpotChecks = geoLocationSpotChecks;
    }
}
