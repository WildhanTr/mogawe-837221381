package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.job.JobAttachment;

import java.io.Serializable;
import java.util.List;

public class JobAttachmentResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private List<JobAttachment> object;
    private UserResponse.RequestKey requestKey;

    public List<JobAttachment> getObject() {
        return object;
    }

    public void setObject(List<JobAttachment> object) {
        this.object = object;
    }

    public UserResponse.RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(UserResponse.RequestKey requestKey) {
        this.requestKey = requestKey;
    }
}
