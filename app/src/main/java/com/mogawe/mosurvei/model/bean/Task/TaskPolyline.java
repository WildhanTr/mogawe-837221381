package com.mogawe.mosurvei.model.bean.Task;

import com.google.gson.annotations.SerializedName;

public class TaskPolyline {
    @SerializedName(value="idJob", alternate = {"id_job", "uuid"})
    private String id_job;
    @SerializedName(value="idJobType", alternate = {"id_job_type"})
    private String id_job_type;
    @SerializedName(value="jobTypeName", alternate = {"job_type_name"})
    private String job_type_name;
    @SerializedName(value="locationName", alternate = {"location_name"})
    private String location_name;
    @SerializedName(value="latlngs")
    private String latlngs;

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

    public String getJob_type_name() {
        return job_type_name;
    }

    public void setJob_type_name(String job_type_name) {
        this.job_type_name = job_type_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLatlngs() {
        return latlngs;
    }

    public void setLatlngs(String latlngs) {
        this.latlngs = latlngs;
    }
}
