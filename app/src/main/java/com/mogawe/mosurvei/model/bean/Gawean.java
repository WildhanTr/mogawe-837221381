package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gawean {
    @SerializedName("id_job")
    @Expose
    private String idJob;
    @SerializedName("id_group_job")
    @Expose
    private String idGroupJob;
    @SerializedName("id_location")
    @Expose
    private String idLocation;
    @SerializedName("id_project")
    @Expose
    private String idProject;
    @SerializedName("id_job_type")
    @Expose
    private String idJobType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("how_to")
    @Expose
    private String howTo;
    @SerializedName("tutorial")
    @Expose
    private String tutorial;
    @SerializedName("minimum_level")
    @Expose
    private Integer minimumLevel;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("fee")
    @Expose
    private Double fee;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("is_published")
    @Expose
    private Boolean isPublished;

    private Boolean isSelectedRoll = false;

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdGroupJob() {
        return idGroupJob;
    }

    public void setIdGroupJob(String idGroupJob) {
        this.idGroupJob = idGroupJob;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getIdJobType() {
        return idJobType;
    }

    public void setIdJobType(String idJobType) {
        this.idJobType = idJobType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowTo() {
        return howTo;
    }

    public void setHowTo(String howTo) {
        this.howTo = howTo;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public Integer getMinimumLevel() {
        return minimumLevel;
    }

    public void setMinimumLevel(Integer minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getSelectedRoll() {
        return isSelectedRoll;
    }

    public void setSelectedRoll(Boolean selectedRoll) {
        isSelectedRoll = selectedRoll;
    }
}
