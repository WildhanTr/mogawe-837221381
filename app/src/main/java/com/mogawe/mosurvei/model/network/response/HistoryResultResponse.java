package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.HistoryResult;

import java.util.List;

public class HistoryResultResponse extends GenericResp {
    @SerializedName("resultHistoriesSize")
    @Expose
    private Integer resultHistoriesSize;
    @SerializedName("object")
    @Expose
    private List<HistoryResult> resultHistories;
    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    public List<HistoryResult> getResultHistories() {
        return resultHistories;
    }

    public void setResultHistories(List<HistoryResult> resultHistories) {
        this.resultHistories = resultHistories;
    }

    public Integer getResultHistoriesSize() {
        return resultHistoriesSize;
    }

    public void setResultHistoriesSize(Integer resultHistoriesSize) {
        this.resultHistoriesSize = resultHistoriesSize;
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
}