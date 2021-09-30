package com.mogawe.mosurvei.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Ads;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.util.List;

public class AdsGroup {
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
    private List<Ads> Adverts;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getJob_count() {
        return job_count;
    }

    public void setJob_count(Integer job_count) {
        this.job_count = job_count;
    }

    public List<Ads> getAdverts() {
        return Adverts;
    }

    public void setAdverts(List<Ads> adverts) {
        Adverts = adverts;
    }
}
