package com.mogawe.mosurvei.model.network.response.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.HomeContent;
import com.mogawe.mosurvei.model.bean.Task.Tasks;
import com.mogawe.mosurvei.model.db.entity.HomeContentV2;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

import java.io.Serializable;
import java.util.List;

public class HomeContentsResponse extends GenericResponse implements Serializable {
    @SerializedName("object")
    @Expose
    private List<HomeContentV2> groupJobs;
    @SerializedName("rowCount")
    @Expose
    private Integer row_count;
    @SerializedName("pageCount")
    @Expose
    private Integer page_count;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public List<HomeContentV2> getGroupJobs() {
        return groupJobs;
    }

    public void setGroupJobs(List<HomeContentV2> groupJobs) {
        this.groupJobs = groupJobs;
    }

    public Integer getRow_count() {
        return row_count;
    }

    public void setRow_count(Integer row_count) {
        this.row_count = row_count;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
