package com.mogawe.mosurvei.model.network.response.job;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Job;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.network.response.GenericResp;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

import java.util.List;

public class FeaturedJobResponse extends GenericResponse {
    @SerializedName("object")
    @Expose
    private List<Task> gaweanList;

    public List<Task> getGaweanList() {
        return gaweanList;
    }

    public void setGaweanList(List<Task> gaweanList) {
        this.gaweanList = gaweanList;
    }
}
