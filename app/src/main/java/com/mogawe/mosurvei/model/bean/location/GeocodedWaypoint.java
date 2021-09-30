package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenrynaldi on 9/14/16.
 */

public class GeocodedWaypoint implements Serializable {

    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("geocoder_status")
    @Expose
    private String geocoderStatus;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();

    /**
     *
     * @return
     * The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     *
     * @return
     * The geocoderStatus
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     *
     * @param geocoderStatus
     * The geocoder_status
     */
    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    /**
     *
     * @return
     * The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}
