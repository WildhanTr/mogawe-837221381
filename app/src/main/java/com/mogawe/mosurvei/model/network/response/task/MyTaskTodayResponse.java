package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.TaskToday;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class MyTaskTodayResponse extends GenericResp {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private List<TaskToday> taskGroups;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<TaskToday> getTaskGroups() {
        return taskGroups;
    }

    public void setTaskGroups(List<TaskToday> taskGroups) {
        this.taskGroups = taskGroups;
    }
}
