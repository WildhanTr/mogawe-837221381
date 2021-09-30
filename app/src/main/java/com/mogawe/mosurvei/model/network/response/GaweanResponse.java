package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Gawean;
import com.mogawe.mosurvei.model.db.entity.Job;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.io.Serializable;
import java.util.List;

public class GaweanResponse extends GenericResponse implements Serializable {

    @SerializedName("object")
    @Expose
    private List<Task> gaweanList;
    @SerializedName("object2")
    @Expose
    private List<Task> gaweanSuggestionList;

    public List<Task> getGaweanList() {
        return gaweanList;
    }

    public void setGaweanList(List<Task> gaweanList) {
        this.gaweanList = gaweanList;
    }

    public List<Task> getGaweanSuggestionList() {
        return gaweanSuggestionList;
    }

    public void setGaweanSuggestionList(List<Task> gaweanSuggestionList) {
        this.gaweanSuggestionList = gaweanSuggestionList;
    }
}
