package com.mogawe.mosurvei.model.network.response.section;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.io.Serializable;

public class SectionSubmitResponse extends GenericResp {
    @SerializedName("uuid")
    @Expose
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    private SectionSourceResponse.RequestKey requestKey;

    public SectionSourceResponse.RequestKey getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(SectionSourceResponse.RequestKey requestKey) {
        this.requestKey = requestKey;
    }

    public enum RequestKey {
        RESULT_UPLOADED
    }
}
