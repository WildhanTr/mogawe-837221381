package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 9/11/16.
 */

public class Geometry implements Serializable {

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("coordinate")
    @Expose
    private Coordinate coordinate;

    /**
     *
     * @return
     * The bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     *
     * @param bounds
     * The bounds
     */
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    /**
     *
     * @return
     * The viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     *
     * @param viewport
     * The viewport
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    /**
     *
     * @return
     * The locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     *
     * @param locationType
     * The location_type
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     *
     * @return
     * The coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     *
     * @param coordinate
     * The coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}


