package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Fact;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.List;

public class FactPollResponse extends GenericResponse implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Fact> factPollList;

    public List<Fact> getFactPollList() {
        return factPollList;
    }

    public void setFactPollList(List<Fact> factPollList) {
        this.factPollList = factPollList;
    }
}
