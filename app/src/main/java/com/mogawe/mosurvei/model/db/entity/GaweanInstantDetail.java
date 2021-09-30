package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;

import java.io.Serializable;
import java.util.List;

public class GaweanInstantDetail implements Serializable {

    @SerializedName("uuid")
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    String uuid;

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @SerializedName("startDate")
    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    String startDate;

    @SerializedName("endDate")
    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    String endDate;

    @SerializedName("timeTo")
    public String getTimeTo() {
        return this.timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    String timeTo;

    @SerializedName("timeFrom")
    public String getTimeFrom() {
        return this.timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    String timeFrom;

    @SerializedName("jobDuration")
    public int getJobDuration() {
        return this.jobDuration;
    }

    public void setJobDuration(int jobDuration) {
        this.jobDuration = jobDuration;
    }

    int jobDuration;

    @SerializedName("jobTarget")
    public int getJobTarget() {
        return this.jobTarget;
    }

    public void setJobTarget(int jobTarget) {
        this.jobTarget = jobTarget;
    }

    int jobTarget;

    @SerializedName("targetParticipants")
    public int getTargetParticipants() {
        return this.targetParticipants;
    }

    public void setTargetParticipants(int targetParticipants) {
        this.targetParticipants = targetParticipants;
    }

    int targetParticipants;

    @SerializedName("currentParticipants")
    public int getCurrentParticipants() {
        return this.currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    int currentParticipants;

    @SerializedName("participationStatus")
    public String getParticipationStatus() {
        return this.participationStatus;
    }

    public void setParticipationStatus(String participationStatus) {
        this.participationStatus = participationStatus;
    }

    String participationStatus;

    @SerializedName("fee")
    public double getFee() {
        return this.fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    double fee;

    @SerializedName("iconUrl")
    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    String iconUrl;

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    @SerializedName("code")
    public Object getCode() {
        return this.code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    Object code;

    @SerializedName("tutorial")
    public String getTutorial() {
        return this.tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    String tutorial;

    @SerializedName("certificates")
    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    List<Certificate> certificates;

}
