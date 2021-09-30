package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobTimes implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("idJob")
    @Expose
    private Integer idJob;
    @SerializedName("timeFrom")
    @Expose
    private Long timeFrom;
    @SerializedName("timeTo")
    @Expose
    private Long timeTo;

    @SerializedName("uuidLocation")
    @Expose
    private String uuidLocation;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("locationLatitude")
    @Expose
    private String locationLatitude;
    @SerializedName("locationLongitude")
    @Expose
    private String locationLongitude;

    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("createdBy")
    @Expose
    private Long createdBy;
    @SerializedName("updatedDate")
    @Expose
    private Long updatedDate;
    @SerializedName("updatedBy")
    @Expose
    private Long updatedBy;

    public String getUuidLocation() {
        return uuidLocation;
    }

    public void setUuidLocation(String uuidLocation) {
        this.uuidLocation = uuidLocation;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getIdJob() {
        return idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

    public Long getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Long timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Long getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Long timeTo) {
        this.timeTo = timeTo;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}
