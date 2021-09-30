package com.mogawe.mosurvei.model.db.entity;

import java.io.Serializable;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Task.TaskActivities;

@Entity
public class Job implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("group_name")
    @Expose
    private String group_name;
    @SerializedName("id_job")
    @Expose
    private String id_job;
    @SerializedName("id_job_type")
    @Expose
    private String id_job_type;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location_name")
    @Expose
    private String location_name;
    @SerializedName("job_type_name")
    @Expose
    private String job_type_name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("how_to")
    @Expose
    private String how_to;
    @SerializedName("minimum_level")
    @Expose
    private Integer minimum_level;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("isScreening")
    @Expose
    private Boolean isScreening;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("is_qc_needed")
    @Expose
    private boolean is_qc_needed;
    @SerializedName("is_auto_approved")
    @Expose
    private boolean is_auto_approved;
    @SerializedName("job_picture")
    @Expose
    private String job_picture;
    @SerializedName("job_banner")
    @Expose
    private String job_banner;
    @SerializedName("job_color")
    @Expose
    private String job_color;
    @SerializedName("job_text_color")
    @Expose
    private String job_text_color;

    private List<TaskActivities> job_activities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_job() {
        return id_job;
    }

    public void setId_job(String id_job) {
        this.id_job = id_job;
    }

    public String getId_job_type() {
        return id_job_type;
    }

    public void setId_job_type(String id_job_type) {
        this.id_job_type = id_job_type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getJob_type_name() {
        return job_type_name;
    }

    public void setJob_type_name(String job_type_name) {
        this.job_type_name = job_type_name;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHow_to() {
        return how_to;
    }

    public void setHow_to(String how_to) {
        this.how_to = how_to;
    }

    public Integer getMinimum_level() {
        return minimum_level;
    }

    public void setMinimum_level(Integer minimum_level) {
        this.minimum_level = minimum_level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Boolean getScreening() {
        return isScreening;
    }

    public void setScreening(Boolean screening) {
        isScreening = screening;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean isIs_qc_needed() {
        return is_qc_needed;
    }

    public void setIs_qc_needed(boolean is_qc_needed) {
        this.is_qc_needed = is_qc_needed;
    }

    public boolean isIs_auto_approved() {
        return is_auto_approved;
    }

    public void setIs_auto_approved(boolean is_auto_approved) {
        this.is_auto_approved = is_auto_approved;
    }

    public String getJob_picture() {
        return job_picture;
    }

    public void setJob_picture(String job_picture) {
        this.job_picture = job_picture;
    }

    public String getJob_banner() {
        return job_banner;
    }

    public void setJob_banner(String job_banner) {
        this.job_banner = job_banner;
    }

    public String getJob_color() {
        return job_color;
    }

    public void setJob_color(String job_color) {
        this.job_color = job_color;
    }

    public String getJob_text_color() {
        return job_text_color;
    }

    public void setJob_text_color(String job_text_color) {
        this.job_text_color = job_text_color;
    }

    public List<TaskActivities> getJob_activities() {
        return job_activities;
    }

    public void setJob_activities(List<TaskActivities> job_activities) {
        this.job_activities = job_activities;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
