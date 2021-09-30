package com.mogawe.mosurvei.model.network.response.timeline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.TimelineActivityEntity;
import com.mogawe.mosurvei.model.network.response.GenericResponse;

import java.io.Serializable;
import java.util.List;

public class TimelineActivityResponse extends GenericResponse implements Serializable {

    @SerializedName("object")
    @Expose
    private List<TimelineActivityEntity> timelineActivityEntityList;

    @SerializedName("object2")
    @Expose
    private List<TimelineActivityEntity> timelineActivityEntityList2;

    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("offset")
    @Expose
    private Integer offset;

    public List<TimelineActivityEntity> getTimelineActivityEntityList() {
        return timelineActivityEntityList;
    }

    public void setTimelineActivityEntityList(List<TimelineActivityEntity> timelineActivityEntityList) {
        this.timelineActivityEntityList = timelineActivityEntityList;
    }

    public List<TimelineActivityEntity> getTimelineActivityEntityList2() {
        return timelineActivityEntityList2;
    }

    public void setTimelineActivityEntityList2(List<TimelineActivityEntity> timelineActivityEntityList2) {
        this.timelineActivityEntityList2 = timelineActivityEntityList2;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
