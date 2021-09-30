package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.List;

public class TaskResponse extends GenericResp implements Serializable {

    @SerializedName("object")
    @Expose
    private Task object;

    public Task getObject() {
        return object;
    }

    public void setObject(Task object) {
        this.object = object;
    }
}
