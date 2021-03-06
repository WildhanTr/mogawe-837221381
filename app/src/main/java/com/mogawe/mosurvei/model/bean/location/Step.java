package com.mogawe.mosurvei.model.bean.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 9/14/16.
 */

public class Step implements Serializable {

    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("end_location")
    @Expose
    private EndLocation endLocation;
    @SerializedName("polyline")
    @Expose
    private Polyline polyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation startLocation;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;

    /**
     * @return The htmlInstructions
     */
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    /**
     * @param htmlInstructions The html_instructions
     */
    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    /**
     * @return The duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * @return The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     * @return The endLocation
     */
    public EndLocation getEndLocation() {
        return endLocation;
    }

    /**
     * @param endLocation The end_location
     */
    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * @return The polyline
     */
    public Polyline getPolyline() {
        return polyline;
    }

    /**
     * @param polyline The polyline
     */
    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    /**
     * @return The startLocation
     */
    public StartLocation getStartLocation() {
        return startLocation;
    }

    /**
     * @param startLocation The start_location
     */
    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * @return The travelMode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * @param travelMode The travel_mode
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * @return The maneuver
     */
    public String getManeuver() {
        return maneuver;
    }

    /**
     * @param maneuver The maneuver
     */
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

}
