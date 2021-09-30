package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.job.JobRefCode;

import java.io.Serializable;
import java.util.List;

public class JobRefCodeResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private JobRefCode object;
    private UserResponse.RequestKey requestKey;

    public JobRefCode getObject() {
        return object;
    }

    public void setObject(JobRefCode object) {
        this.object = object;
    }

    public UserResponse.RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(UserResponse.RequestKey requestKey) {
        this.requestKey = requestKey;
    }
}
