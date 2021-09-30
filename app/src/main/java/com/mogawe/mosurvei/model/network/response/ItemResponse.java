package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.db.entity.Item;

import java.util.List;

public class ItemResponse extends GenericResponse {

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
    @SerializedName("object")
    @Expose
    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
