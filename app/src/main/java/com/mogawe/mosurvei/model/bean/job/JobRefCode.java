package com.mogawe.mosurvei.model.bean.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by glenrynaldi on 4/4/17.
 */

public class JobRefCode implements Serializable {

    @SerializedName("uuidMogawers")
    @Expose
    private String uuidMogawers;
    @SerializedName("uuidRefCode")
    @Expose
    private String uuidRefCode;
    @SerializedName("uuidProject")
    @Expose
    private String uuidProject;
    @SerializedName("value")
    @Expose
    private String value;

    public String getUuidMogawers() {
        return uuidMogawers;
    }

    public void setUuidMogawers(String uuidMogawers) {
        this.uuidMogawers = uuidMogawers;
    }

    public String getUuidRefCode() {
        return uuidRefCode;
    }

    public void setUuidRefCode(String uuidRefCode) {
        this.uuidRefCode = uuidRefCode;
    }

    public String getUuidProject() {
        return uuidProject;
    }

    public void setUuidProject(String uuidProject) {
        this.uuidProject = uuidProject;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
