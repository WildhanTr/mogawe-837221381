package com.mogawe.mosurvei.model.bean.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by glenrynaldi on 4/4/17.
 */

public class JobAttachment implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("uuidJob")
    @Expose
    private String uuidJob;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("type")
    @Expose
    private String type;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidJob() {
        return uuidJob;
    }

    public void setUuidJob(String uuidJob) {
        this.uuidJob = uuidJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
