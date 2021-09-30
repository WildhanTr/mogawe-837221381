package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 9/11/16.
 */

public class Bounds implements Serializable {

    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
    @SerializedName("northeast")
    @Expose
    private Northeast northeast;

    /**
     *
     * @return
     * The southwest
     */
    public Southwest getSouthwest() {
        return southwest;
    }

    /**
     *
     * @param southwest
     * The southwest
     */
    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    /**
     *
     * @return
     * The northeast
     */
    public Northeast getNortheast() {
        return northeast;
    }

    /**
     *
     * @param northeast
     * The northeast
     */
    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

}
