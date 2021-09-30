package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 9/14/16.
 */

public class Polyline implements Serializable {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     * @return The points
     */
    public String getPoints() {
        return points;
    }

    /**
     * @param points The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}
