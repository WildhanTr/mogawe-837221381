package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Task.TaskPolyline;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

public class TaskPolylineResponse extends GenericResponse {
    @SerializedName("object")
    @Expose
    private TaskPolyline taskPolyline;

    public TaskPolyline getTaskPolyline() {
        return taskPolyline;
    }

    public void setTaskPolyline(TaskPolyline taskPolyline) {
        this.taskPolyline = taskPolyline;
    }
}
