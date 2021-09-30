package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenrynaldi on 9/11/16.
 */

public class Result implements Serializable {

    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = new ArrayList<>();
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

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
     * The addressComponents
     */
    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    /**
     *
     * @param addressComponents
     * The address_components
     */
    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    /**
     *
     * @return
     * The formattedAddress
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     *
     * @param formattedAddress
     * The formatted_address
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
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

    /**
     *
     * @return
     * The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

}
