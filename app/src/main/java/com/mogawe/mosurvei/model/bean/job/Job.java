package com.mogawe.mosurvei.model.bean.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by glenrynaldi on 4/4/17.
 */

public class Job implements Serializable {

    @SerializedName("id_job")
    @Expose
    private String idJob;
    @SerializedName("id_store")
    @Expose
    private String idStore;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("fee_unit")
    @Expose
    private String feeUnit;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("Dist")
    @Expose
    private String dist;
    @SerializedName("id_job_type")
    @Expose
    private String idJobType;
    @SerializedName("areas")
    @Expose
    private List<Area> areas = null;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("id_period")
    @Expose
    private String idPeriod;
    @SerializedName("received_date")
    @Expose
    private String receivedDate;
    @SerializedName("fee_final")
    @Expose
    private String feeFinal;
    private Boolean isApproved = false;
    @SerializedName("areaDist")
    @Expose
    private String areaDist;
    @SerializedName("storeDist")
    @Expose
    private String storeDist;
    @SerializedName("radius")
    @Expose
    private String radiusMeter = "20";
    @SerializedName("job_limit")
    @Expose
    private String jobLimit;
    @SerializedName("jml_done")
    @Expose
    private String jmlDone = "0";
    @SerializedName("tutorial")
    @Expose
    private String tutorial;
    @SerializedName("is_store")
    @Expose
    private String isStore;
    private String data;
    private String status;

    @SerializedName("id_area")
    @Expose
    private String idArea;
    @SerializedName("job_name")
    @Expose
    private String jobName;
    @SerializedName("status_reason")
    @Expose
    private Double statusReason;
    @SerializedName("quality_rating")
    @Expose
    private Float qualityRating;

    public Job() {
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getIdJobType() {
        return idJobType;
    }

    public void setIdJobType(String idJobType) {
        this.idJobType = idJobType;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean isApporved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getFeeUnit() {
        return feeUnit;
    }

    public void setFeeUnit(String feeUnit) {
        this.feeUnit = feeUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(String idPeriod) {
        this.idPeriod = idPeriod;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getFeeFinal() {
        return feeFinal;
    }

    public void setFeeFinal(String feeFinal) {
        this.feeFinal = feeFinal;
    }

    public String getAreaDist() {
        return areaDist;
    }

    public void setAreaDist(String areaDist) {
        this.areaDist = areaDist;
    }

    public String getStoreDist() {
        return storeDist;
    }

    public void setStoreDist(String storeDist) {
        this.storeDist = storeDist;
    }

    public String getRadiusMeter() {
        return radiusMeter;
    }

    public void setRadiusMeter(String radiusMeter) {
        this.radiusMeter = radiusMeter;
    }

    public String getJobLimit() {
        return jobLimit;
    }

    public void setJobLimit(String jobLimit) {
        this.jobLimit = jobLimit;
    }

    public String getJmlDone() {
        return jmlDone;
    }

    public void setJmlDone(String jmlDone) {
        this.jmlDone = jmlDone;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public String isStore() {
        return isStore;
    }

    public void setIsStore(String isStore) {
        this.isStore = isStore;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Double getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(Double statusReason) {
        this.statusReason = statusReason;
    }

    public Float getQualityRating() {
        return qualityRating;
    }

    public void setQualityRating(Float qualityRating) {
        this.qualityRating = qualityRating;
    }
}
