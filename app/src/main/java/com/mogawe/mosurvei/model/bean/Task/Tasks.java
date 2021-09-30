package com.mogawe.mosurvei.model.bean.Task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.job.Job;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tasks implements Serializable {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("groupJobName")
    @Expose
    private String group_name;
    @SerializedName("groupJobType")
    @Expose
    private String group_type;
    @SerializedName("jobCount")
    @Expose
    private Integer job_count;
    @SerializedName("groupJobList")
    @Expose
    private List<Task> tasks;

    private Integer page;
    private Boolean isLoading = false;

    public Tasks() {
    }

    public Tasks(String group_name, String group_type) {
        this.group_name = group_name;
        this.group_type = group_type;
        this.tasks = new ArrayList<>();
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getJob_count() {
        return job_count;
    }

    public void setJob_count(Integer job_count) {
        this.job_count = job_count;
    }
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getLoading() {
        return isLoading;
    }

    public void setLoading(Boolean loading) {
        isLoading = loading;
    }
}
