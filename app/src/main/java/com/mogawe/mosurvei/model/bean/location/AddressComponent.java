package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenrynaldi on 9/11/16.
 */

public class AddressComponent implements Serializable {

    @SerializedName("long_name")
    @Expose
    private String longName;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();
    @SerializedName("short_name")
    @Expose
    private String shortName;

    /**
     * @return The longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName The long_name
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * @return The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * @return The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
