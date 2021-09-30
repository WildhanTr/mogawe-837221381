package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VerifyChatRoomResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private long object;

    public long getObject() {
        return object;
    }

    public void setObject(long object) {
        this.object = object;
    }
}
