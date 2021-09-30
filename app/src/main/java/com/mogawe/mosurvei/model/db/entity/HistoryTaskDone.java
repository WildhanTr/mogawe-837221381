package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryTaskDone {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("uuidTask")
    @Expose
    private String uuidTask;
    @SerializedName("uuidJob")
    @Expose
    private String uuidJob;
    @SerializedName("uuidLocation")
    @Expose
    private String uuidLocation;
    @SerializedName("uuidSession")
    @Expose
    private String uuidSession;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("statusQc")
    @Expose
    private String statusQc;
    @SerializedName("statusClose")
    @Expose
    private String statusClose;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("jobName")
    @Expose
    private String jobName;
    @SerializedName("batch")
    @Expose
    private String batch;
    @SerializedName("batchPeriod")
    @Expose
    private String batchPeriod;
    @SerializedName("mogawersCode")
    @Expose
    private String mogawersCode;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("resultFacts")
    @Expose
    private String resultFacts;
    @SerializedName("jobId")
    @Expose
    private String jobId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidTask() {
        return uuidTask;
    }

    public void setUuidTask(String uuidTask) {
        this.uuidTask = uuidTask;
    }

    public String getUuidJob() {
        return uuidJob;
    }

    public void setUuidJob(String uuidJob) {
        this.uuidJob = uuidJob;
    }

    public String getUuidLocation() {
        return uuidLocation;
    }

    public void setUuidLocation(String uuidLocation) {
        this.uuidLocation = uuidLocation;
    }

    public String getUuidSession() {
        return uuidSession;
    }

    public void setUuidSession(String uuidSession) {
        this.uuidSession = uuidSession;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatusQc() {
        return statusQc;
    }

    public void setStatusQc(String statusQc) {
        this.statusQc = statusQc;
    }

    public String getStatusClose() {
        return statusClose;
    }

    public void setStatusClose(String statusClose) {
        this.statusClose = statusClose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchPeriod() {
        return batchPeriod;
    }

    public void setBatchPeriod(String batchPeriod) {
        this.batchPeriod = batchPeriod;
    }

    public String getMogawersCode() {
        return mogawersCode;
    }

    public void setMogawersCode(String mogawersCode) {
        this.mogawersCode = mogawersCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getResultFacts() {
        return resultFacts;
    }

    public void setResultFacts(String resultFacts) {
        this.resultFacts = resultFacts;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
