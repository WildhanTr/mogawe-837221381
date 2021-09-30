package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.HistoryTaskDone;
import com.mogawe.mosurvei.model.network.response.GenericResp;

import java.util.List;

public class HistoryTaskResponse extends GenericResp {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("object")
    @Expose
    private List<HistoryTaskDone> historyTaskDones;
    @SerializedName("object2")
    @Expose
    private List<HistoryTaskDone> historyTaskDones2;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<HistoryTaskDone> getHistoryTaskDones() {
        return historyTaskDones;
    }

    public void setHistoryTaskDones(List<HistoryTaskDone> historyTaskDones) {
        this.historyTaskDones = historyTaskDones;
    }

    public List<HistoryTaskDone> getHistoryTaskDones2() {
        return historyTaskDones2;
    }

    public void setHistoryTaskDones2(List<HistoryTaskDone> historyTaskDones2) {
        this.historyTaskDones2 = historyTaskDones2;
    }
}
