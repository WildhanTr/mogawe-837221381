package com.mogawe.mosurvei.model.network.request.task;

public class GetAllCategoriesRequest {
    private Double lat;
    private Double lng;
    private String uuidCategories;
    private Integer page;
    private Integer offset;
    private Integer limit;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getUuidCategories() {
        return uuidCategories;
    }

    public void setUuidCategories(String uuidCategories) {
        this.uuidCategories = uuidCategories;
    }
}
