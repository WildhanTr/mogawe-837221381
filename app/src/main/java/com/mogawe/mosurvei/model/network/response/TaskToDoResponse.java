package com.mogawe.mosurvei.model.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mogawe.mosurvei.model.bean.Task.ToDoList;
import com.mogawe.mosurvei.model.db.entity.TaskToday;

import java.util.List;

public class TaskToDoResponse extends GenericResp {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("object")
    @Expose
    private List<ToDoList> toDoLists;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(List<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
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
