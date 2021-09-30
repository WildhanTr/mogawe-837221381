package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.List;

public class GaweanResponseV2 extends GenericResponse implements Serializable {

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("object")
    @Expose
    private GaweanResponseV2Object object;

    public GaweanResponseV2Object getObject() {
        return object;
    }

    public void setObject(GaweanResponseV2Object object) {
        this.object = object;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
