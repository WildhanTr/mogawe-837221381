package com.mogawe.mosurvei.model.db.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TaskToday implements Serializable {

    @SerializedName("jobs")
    @Expose
    private List<Task> jobs;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Task> getJobs() {
        return jobs;
    }

    public void setJobs(List<Task> jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
