package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Certificate implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("uuidCategory")
    @Expose
    private String uuidCategory;
    @SerializedName("uuidJob")
    @Expose
    private String uuidJob;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("jobPicture")
    @Expose
    private String jobPicture;

    @SerializedName("potentialJob")
    @Expose
    private Integer potentialJob;
    @SerializedName("minimumJobFee")
    @Expose
    private Double minimumJobFee;

    public Integer getPotentialJob() {
        return potentialJob;
    }

    public void setPotentialJob(Integer potentialJob) {
        this.potentialJob = potentialJob;
    }

    public Double getMinimumJobFee() {
        return minimumJobFee;
    }

    public void setMinimumJobFee(Double minimumJobFee) {
        this.minimumJobFee = minimumJobFee;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidCategory() {
        return uuidCategory;
    }

    public void setUuidCategory(String uuidCategory) {
        this.uuidCategory = uuidCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getUuidJob() {
        return uuidJob;
    }

    public void setUuidJob(String uuidJob) {
        this.uuidJob = uuidJob;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getJobPicture() {
        return jobPicture;
    }

    public void setJobPicture(String jobPicture) {
        this.jobPicture = jobPicture;
    }
}
