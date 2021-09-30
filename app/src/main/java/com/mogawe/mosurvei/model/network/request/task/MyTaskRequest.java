package com.mogawe.mosurvei.model.network.request.task;

import com.mogawe.mosurvei.model.network.request.GenericReq;

public class MyTaskRequest extends GenericReq {
    private Integer page;
    private Integer offset;
    private Boolean startingOnly;
    private String query;
    private String from;
    private String to;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getStartingOnly() {
        return startingOnly;
    }

    public void setStartingOnly(Boolean startingOnly) {
        this.startingOnly = startingOnly;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
