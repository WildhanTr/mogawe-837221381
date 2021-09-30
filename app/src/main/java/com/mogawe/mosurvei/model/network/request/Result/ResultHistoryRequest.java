package com.mogawe.mosurvei.model.network.request.Result;

public class ResultHistoryRequest {
    private Integer idMogawers;
    private Integer page;
    private Integer offset;
    private String periode;
    private String status;
    private String startdate;
    private String enddate;
    private String q;

    public ResultHistoryRequest() {
    }

    public ResultHistoryRequest(Integer idMogawers, Integer page, Integer offset) {
        this.idMogawers = idMogawers;
        this.page = page;
        this.offset = offset;
    }

    public ResultHistoryRequest(String q, Integer page, Integer offset, String periode, String status, String startdate, String enddate) {
        this.q = q;
        this.page = page;
        this.offset = offset;
        this.periode = periode;
        this.status = status;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getIdMogawers() {
        return idMogawers;
    }

    public void setIdMogawers(Integer idMogawers) {
        this.idMogawers = idMogawers;
    }

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

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
