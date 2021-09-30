package com.mogawe.mosurvei.model.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HistoryResult implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName(value="idTask", alternate = {"id_task", "uuidTask"})
    @Expose
    private String id_task;
    @SerializedName(value="idResult", alternate = {"id_result", "uuidResult"})
    @Expose
    private String id_result;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName(value="createdDate", alternate = {"created_date"})
    @Expose
    private Long created_date;
    @SerializedName(value="jobName", alternate = {"job_name"})
    @Expose
    private String job_name;
    @SerializedName(value="jobDescription", alternate = { "job_description" })
    @Expose
    private String job_description;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;

    @SerializedName(value="jobTypeName", alternate =  { "job_type_name" } )
    @Expose
    private String job_type_name;
    @SerializedName(value="uuidJobType", alternate =  { "id_job_type" } )
    @Expose
    private String id_job_type;
    @SerializedName(value="statusQc", alternate =  { "status_qc" } )
    @Expose
    private String status_qc;
    @SerializedName(value="status")
    @Expose
    private String status;
    @SerializedName(value="jobPicture", alternate =  { "job_picture" } )
    @Expose
    private String job_picture;
    @SerializedName(value="jobBanner", alternate =  { "job_banner" } )
    @Expose
    private String job_banner;
    @SerializedName("jobColor")
    @Expose
    private String job_color;
    @SerializedName(value="jobTextColor", alternate =  { "job_text_color" } )
    @Expose
    private String job_text_color;
    @SerializedName(value="isDisplayable", alternate =  { "is_displayable" } )
    @Expose
    private Boolean is_displayable;

    private List<HistoryResult> historyResultList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getId_result() {
        return id_result;
    }

    public void setId_result(String id_result) {
        this.id_result = id_result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Long created_date) {
        this.created_date = created_date;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getJob_type_name() {
        return job_type_name;
    }

    public void setJob_type_name(String job_type_name) {
        this.job_type_name = job_type_name;
    }

    public String getId_job_type() {
        return id_job_type;
    }

    public void setId_job_type(String id_job_type) {
        this.id_job_type = id_job_type;
    }

    public String getStatus_qc() {
        return status_qc;
    }

    public void setStatus_qc(String status_qc) {
        this.status_qc = status_qc;
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

    public Boolean getIs_displayable() {
        return is_displayable;
    }

    public void setIs_displayable(Boolean is_displayable) {
        this.is_displayable = is_displayable;
    }

    public List<HistoryResult> getHistoryResultList() {
        return historyResultList;
    }

    public void setHistoryResultList(List<HistoryResult> historyResultList) {
        this.historyResultList = historyResultList;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
