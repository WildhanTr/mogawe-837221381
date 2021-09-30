package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.LocationEntity;

import java.io.Serializable;
import java.util.List;

public class LocationResponse extends GenericResponse implements Serializable {

    @SerializedName("object")
    @Expose
    private List<LocationEntity> locationList;

    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("offset")
    @Expose
    private Integer offset;

    public List<LocationEntity> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationEntity> locationList) {
        this.locationList = locationList;
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
