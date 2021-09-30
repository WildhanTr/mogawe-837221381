package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.db.entity.GaweanInstant;

import java.util.List;

public class GaweanInstantResponse extends GenericResp {

    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;

    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    @SerializedName("offset")
    @Expose
    private Integer offset;

    @SerializedName("object")
    @Expose
    private List<GaweanInstant> gaweanInstant;

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

    public List<GaweanInstant> getGaweanInstant() {
        return gaweanInstant;
    }

    public void setGaweanInstant(List<GaweanInstant> gaweanInstant) {
        this.gaweanInstant = gaweanInstant;
    }
}
