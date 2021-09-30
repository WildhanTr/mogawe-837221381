package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeoLocationSpotCheck implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("distance")
    @Expose
    private Double distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
