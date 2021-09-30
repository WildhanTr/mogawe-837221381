package com.mogawe.mosurvei.model.bean.Task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskActivities implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuidJob")
    @Expose
    private String id_job;
    @SerializedName("uuidActionType")
    @Expose
    private String id_action_type;
    @SerializedName("actionName")
    @Expose
    private String activities_name;
    @SerializedName("actionCode")
    @Expose
    private String activities_code;
    @SerializedName("actionDescription")
    @Expose
    private String activities_description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_job() {
        return id_job;
    }

    public void setId_job(String id_job) {
        this.id_job = id_job;
    }

    public String getId_action_type() {
        return id_action_type;
    }

    public void setId_action_type(String id_action_type) {
        this.id_action_type = id_action_type;
    }

    public String getActivities_name() {
        return activities_name;
    }

    public void setActivities_name(String activities_name) {
        this.activities_name = activities_name;
    }

    public String getActivities_code() {
        return activities_code;
    }

    public void setActivities_code(String activities_code) {
        this.activities_code = activities_code;
    }

    public String getActivities_description() {
        return activities_description;
    }

    public void setActivities_description(String activities_description) {
        this.activities_description = activities_description;
    }
}
