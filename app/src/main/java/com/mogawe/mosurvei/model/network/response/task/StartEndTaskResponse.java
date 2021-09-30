package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class StartEndTaskResponse extends GenericResp {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private Task task;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
