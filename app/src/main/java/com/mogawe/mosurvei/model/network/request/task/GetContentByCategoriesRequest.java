package com.mogawe.mosurvei.model.network.request.task;

public class GetContentByCategoriesRequest {
    private String uuidCategories;
    private Integer page;
    private Integer offset;

    public String getUuidCategories() {
        return uuidCategories;
    }

    public void setUuidCategories(String uuidCategories) {
        this.uuidCategories = uuidCategories;
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
}
